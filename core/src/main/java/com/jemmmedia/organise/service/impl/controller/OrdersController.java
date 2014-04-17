/**
 * 
 */
package com.jemmmedia.organise.service.impl.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jemmmedia.organise.mybatis.db.mybatis.model.Groups;
import com.jemmmedia.organise.mybatis.db.mybatis.model.Orders;
import com.jemmmedia.organise.mybatis.db.mybatis.model.UserDetailedBean;
import com.jemmmedia.organise.service.impl.service.GroupsService;
import com.jemmmedia.organise.service.impl.service.OrdersService;
import com.sun.jndi.toolkit.url.UrlUtil;

/**
 * @author harjinder
 *
 */
@Controller
public class OrdersController extends CommonController {
	
	@Resource
	OrdersService ordersService;
	
	@Resource
	GroupsService groupsService;
	
	@Value("${auth.token}")
	private String authToken ;
	
	@RequestMapping(value = "/confirmPayment")
	public ModelAndView showPaymentDetail(Model model,
			@RequestParam("tx") String tx) throws IOException, ParseException {
		UserDetailedBean userLoggedIn = (UserDetailedBean) userService.getUserDetailFromSpringSecurity();
		System.out.println("TransectionId: "+tx);
		
		
		
		String str = "cmd=_notify-synch";

		
		str = str + "&tx=" + tx;
		
		str = str + "&at=" + authToken;


		// post back to PayPal system to validate
		// NOTE: change http: to https: in the following URL to verify using SSL (for increased security).
		
		URL u = new URL("https://www.sandbox.paypal.com/cgi-bin/webscr");
		URLConnection uc = u.openConnection();
		uc.setDoOutput(true);
		uc.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
		PrintWriter pw = new PrintWriter(uc.getOutputStream());
		pw.println(str);
		pw.close();

		BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
		String res = in.readLine();
		String [] temp = null;
		if (res.equals("SUCCESS")){
			
			Groups group = new Groups();
			
			group.setId(userLoggedIn.getGroupId());
			
			
			Orders order = new Orders();	
			order.setTransectionId(tx);
			order.setAuth(authToken);
			order.setGroupId(userLoggedIn.getGroupId());
			
			
			
		while ( (res=in.readLine()) != null ){
		temp = res.split("=");
				if (temp[0].equals("payment_date")) {
					
					

					Date sdate = new SimpleDateFormat("HH:mm:ss MMM dd, yyyy Z").parse(UrlUtil.decode(temp[1]));
					
					DateFormat writeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					
					String scrFDate = writeFormat.format(sdate);
					
					Date subscriptionDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(scrFDate);
					
					

					group.setSubscrDate(subscriptionDate);
					order.setSubscrDate(subscriptionDate);
				}
				if (temp[0].equals("item_number")) {
					group.setPackageId(Long.parseLong(temp[1]));
					order.setItemNumber(temp[1]);
				}
				if (temp[0].equals("item_name")) {
					order.setItemName(UrlUtil.decode(temp[1]));
				}
				if (temp[0].equals("payment_gross")) {
					order.setAmount(temp[1]);
				}
				if (temp[0].equals("item_number")) {
					order.setItemNumber(temp[1]);
				}
				if (temp[0].equals("first_name")) {
					order.setFirstName(UrlUtil.decode(temp[1]));
				}
				if (temp[0].equals("last_name")) {
					order.setLastName(UrlUtil.decode(temp[1]));
					
				}
				if (temp[0].equals("payer_email")) {
					order.setPayerEmail(UrlUtil.decode(temp[1]));
				}
				if (temp[0].equals("payer_id")) {
					order.setPayerId(temp[1]);
				}
				if (temp[0].equals("payer_status")) {
					order.setPayerStatus(temp[1]);
				}
				if (temp[0].equals("subscr_id")) {
					order.setSubscrId(temp[1]);
				}
				if (temp[0].equals("txn_type")) {
					order.setTxnType(temp[1]);
				}
				
		
		}
		
		
		groupsService.updateByPrimaryKeySelective(group);
		ordersService.insertSelective(order);
		
		model.addAttribute("payment", "confirmed");
		model.addAttribute("order", order);
		
		getUsersDetail(model, userLoggedIn);
		

		}
		else if(res.equals("FAIL")){
			model.addAttribute("payment", "cancelled");
	//	out.println("Please check the error log. The transaction has a Fail response");

		}
		in.close();
		
		
		return new ModelAndView("confirmPayment");
	}


}

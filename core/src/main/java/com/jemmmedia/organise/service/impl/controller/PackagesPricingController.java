/**
 * 
 */
package com.jemmmedia.organise.service.impl.controller;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.mail.internet.AddressException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jemmmedia.organise.mybatis.db.mybatis.model.Groups;
import com.jemmmedia.organise.mybatis.db.mybatis.model.PackagePricingBean;
import com.jemmmedia.organise.mybatis.db.mybatis.model.PackagesPricing;
import com.jemmmedia.organise.mybatis.db.mybatis.model.UserDetailedBean;
import com.jemmmedia.organise.service.impl.service.GroupsService;
import com.jemmmedia.organise.service.impl.service.PackagesPricingService;
import com.jemmmedia.organise.service.impl.service.UserService;

/**
 * @author harjinder
 *
 */
@RequestMapping("/")
@Controller
public class PackagesPricingController extends CommonController {
	
	@Resource
	GroupsService groupsService;
	
	@Resource
	PackagesPricingService plPackageService;
	
	@Resource
	UserService userService;

	@RequestMapping("platinumPackageForm")
	public ModelAndView platinumPackageForm(Model model){
		
		PackagePricingBean packagesPricing =  new PackagePricingBean();
		
		return new ModelAndView("packagesPricingForm","packagesPricing", packagesPricing);
		
	}
	
	@RequestMapping("addPackagesPricing")
	public ModelAndView addPlatinumPackage(Model model,@ModelAttribute("packagesPricing")PackagePricingBean packagesPricing,
			@RequestParam("groupId") Long groupId,
			@RequestParam("emailSubject") String emailSubject,
			@RequestParam("emailBody") String emailBody) throws AddressException{
		
		
			Date date = new Date();
			
			packagesPricing.setPackageName("Platinum");
			packagesPricing.setCreatedAt(date);
			packagesPricing.setUpdatedAt(date);
			
			plPackageService.insertSelective(packagesPricing);
			
			String ebody = CreateEmailBody(emailBody,groupId,packagesPricing.getId()); 
			
			String emailIds = userService.selectSuperUersEmail(groupId);
			
			sendmailService.sendMail("no-reply@gmail.com", emailIds, emailSubject, ebody);
			
			//sendmailService.sendMail("no-reply@gmail.com", "hgill@jemmtech.com", emailSubject, ebody);
		
			
		
		//	PackagePricingBean packagesPricing =  new PackagePricingBean();
			
			return new ModelAndView("packagesPricingForm","packagesPricing", packagesPricing);
		
	}
	
	@RequestMapping(value = "upgradePackage", method = RequestMethod.GET)
	public ModelAndView upgradePackage(Model model,
			@ModelAttribute PackagesPricing packagesPricing,
			@RequestParam("groupId") long groupId,
			@RequestParam ("packageId") long packageId){
		UserDetailedBean userLoggedIn = (UserDetailedBean) userService.getUserDetailFromSpringSecurity();
		
		if(userLoggedIn.getPackageType() > 4){
			return new ModelAndView("login");
		}
				
		packagesPricing = plPackageService.selectByPrimaryKey(packageId);
		
		//model.addAttribute("pricingPackage", packagePricingBean);
		
		return new ModelAndView("upgradePackage","packagesPricing", packagesPricing);
		
		
	}
	
	private String CreateEmailBody(String emailBody, long groupId, long packageId){
		
		StringBuilder emailCompleteBody = new StringBuilder(); 
		
		String strCss = "<style>" +
				".button {" +
				"font-family: sans-serif;" +
				"font-weight:bold;" +
				"color: #fff;" +
				"padding:5px 10px 6px 10px;" +
				"-moz-border-radius: 5px;" +
				"-webkit-border-radius: 5px;" +
				"border-radius:5px;" +
				"cursor: pointer;" +
				"}" +
				".green1{" +
				"background:#BFF23E;" +
				"background-image: -moz-linear-gradient(top,#BFF23E,#5B731E);" +
				"background-image: -webkit-gradient(linear,left top, left bottom,from(#BFF23E),to(#5B731E)	);" +
				"}" +
				".green1:hover{background:#5B731E;}" +
				".large{font-size:24px;}" +
				"</style>";
		
		
		String strSignature = "<span style='font-size:13px;font-family: Verdana;'><b>Lisa Rogers - Brand Manager</b></span><br>" +
				"phone: +44 (0) 7403 820 003<br>" +
				"email: lrogers@jemmgroup.com<br>" +
				"skype: rogers_lisa<br>" +
				"website: jemmgroup.com | organise.net<br>";
		
		String urlLink ="https://www.organise.net/app/upgradePackage?groupId="+groupId+"&packageId="+packageId;
		//String urlLink ="http://192.168.249.39:8080/journal2/upgradePackage?groupId="+groupId+"&packageId="+packageId;
		
		
		String strUpgradeButton = "<a href='"+urlLink+"' class='button large green1'>Upgrade Package</a><br><br>";
		
		String directLink = urlLink;
		
		String strOr = "or copy and paste below link in browser";
		
		emailCompleteBody.append("<pre style='font-size:13px;font-family: Verdana;'>");
		emailCompleteBody.append(strCss);
		emailCompleteBody.append(emailBody);
		emailCompleteBody.append("<br><br>");
		emailCompleteBody.append("</pre>");
		//emailCompleteBody.append("<br><br>");
		emailCompleteBody.append(strUpgradeButton);
		emailCompleteBody.append(strOr);
		emailCompleteBody.append("<br><br>");
		emailCompleteBody.append(directLink);
		emailCompleteBody.append("<br><br>");
		emailCompleteBody.append(strSignature);
		
		return emailCompleteBody.toString();
	}
	
	@ModelAttribute("groupsList")
	public Map<Long,String> selectAllGroups() {
		 List<Groups> groupsList = groupsService.selectAllGroups();
		Map<Long,String> allGroups = new LinkedHashMap<Long, String>();
		for (Groups group : groupsList) {
			allGroups.put(group.getId(), group.getName());
		}
		return allGroups;
	}
	
	
	
}

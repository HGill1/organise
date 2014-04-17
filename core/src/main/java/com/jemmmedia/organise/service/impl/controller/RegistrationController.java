/**
 * 
 */
package com.jemmmedia.organise.service.impl.controller;

import java.util.HashMap;

import javax.mail.internet.AddressException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jemmmedia.organise.mybatis.db.mybatis.model.UserDetailedBean;

/**
 * @author harjinder
 *
 */
@RequestMapping("/")
@Controller
public class RegistrationController extends CommonController{
	
	
	@RequestMapping("registrationForm")
	public ModelAndView createUsers(Model model){
		
		UserDetailedBean newSuperUser = new UserDetailedBean();
		
		//getUsersDetail(model);
		
		return new ModelAndView("registrationForm","newSuperUser", newSuperUser);
		
	}
	
	
	@RequestMapping("registerUser")
	public String registerUser(Model model, @ModelAttribute("registerUser") UserDetailedBean registerUser){
		
		if(registerUser.getEmail().isEmpty() || registerUser.getFirstName().isEmpty() || registerUser.getGroupName().isEmpty() || registerUser.getRegionName().isEmpty() || !registerUser.getEmail().matches(EMAIL_PATTERN)){
			try {
				sendmailService.sendMail("no-reply@gmail.com", "hgill@jemmtech.com", "Invalid user", "Invalid User!");
			} catch (AddressException e) {
				e.printStackTrace();
			}
			return "redirect:https://www.organise.net/signup/";
			
		}
		
		saveUserDetail(registerUser, model);
		String emailBody  = notifySalesPeople(registerUser);
		
		try {
			//sendmailService.sendMail("no-reply@gmail.com", "hgill@jemmtech.com", "New Organise User", emailBody);
			sendmailService.sendMail("no-reply@gmail.com", notifierEmails, "New Organise User", emailBody);
		} catch (AddressException e) {
			e.printStackTrace();
		}
		
		
		return "redirect:https://www.organise.net/thank-you/";
		
	}
	
	@RequestMapping("checkUserExists") 
	@ResponseBody public HashMap<String, Boolean> checkUserExists(@RequestParam ("email") String email){
		
		HashMap<String, Boolean> emailId = new HashMap<>();
		
		email = email.replaceAll("'", "");
		email = email.replaceAll("\"", "");
		
		if(userService.checkUserExist(email.trim()) > 0){
			emailId.put("email", true);
		}
		return emailId;
	}
	

}

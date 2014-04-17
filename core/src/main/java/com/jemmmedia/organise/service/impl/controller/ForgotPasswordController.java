/**
 * 
 */
package com.jemmmedia.organise.service.impl.controller;

import javax.annotation.Resource;
import javax.mail.internet.AddressException;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jemmmedia.organise.mybatis.db.mybatis.model.UserDetailedBean;
import com.jemmmedia.organise.service.impl.exception.LoginException;
import com.jemmmedia.organise.service.impl.service.UserService;
import com.jemmmedia.organise.service.impl.utility.StringUtils;

/**
 * @author harjinder
 *
 */
@RequestMapping("/")
@Controller
public class ForgotPasswordController extends  CommonController {
	
	@Resource(name = "userService")
	UserService userService;

	@RequestMapping("forgotPasswordForm")
	public ModelAndView forgotPasswordForm(Model model){
		
		
		return new ModelAndView("forgotPasswordForm");
		
	}
	
	@RequestMapping("sendPassword")
	public ModelAndView sendPassword(Model model,
			@Param("email") String email){
		
		String Subject = "Password details for Organise";
		
		try {
			UserDetailedBean domainUser = userService.getUserByName(email);
			
			domainUser.setUserPassword(StringUtils.generateRandomPassword());
			
			createDefaultPassword(domainUser);
			
			userService.updateByPrimaryKeySelective(domainUser);
			
			String body = emailBodyForgotPassword(siteUrl, email, domainUser.getUserPassword());
			
			try {
				sendmailService.sendMail("no-reply@gmail.com", email, Subject, body);
			} catch (AddressException e) {
				e.printStackTrace();
			}
			
			model.addAttribute("checkEmailMsg", "Please check your email for new password.");
			
			
		} catch (LoginException e) {
			//e.printStackTrace();
			model.addAttribute("msgEmailNotExist", "Email address does not exist!");
			return new ModelAndView("forgotPasswordForm");
		}
		
		return new ModelAndView("login"); 
		
	}
	
		
}

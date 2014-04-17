package com.jemmmedia.organise.service.impl.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jemmmedia.organise.mybatis.db.mybatis.model.FeedEntryBean;
import com.jemmmedia.organise.mybatis.db.mybatis.model.User;
import com.jemmmedia.organise.mybatis.db.mybatis.model.UserDetailedBean;
import com.jemmmedia.organise.service.impl.service.UserService;

@Controller
public class LoginController extends CommonController  implements ApplicationListener<AuthenticationFailureBadCredentialsEvent>{
	
	@Resource(name = "userService") UserService userService; 
	
	static final Logger logger = Logger.getLogger(LoginController.class);
	
	@RequestMapping("/login")
	public ModelAndView login(HttpSession session, HttpServletResponse response, HttpServletRequest request, Model model,
			@ModelAttribute FeedEntryBean feedEntryBean,
			@RequestParam(value = "pageStart", defaultValue = "0")int start,
			@RequestParam(value="error", defaultValue = "0") int error,
			@RequestParam(required = false) String message) {
		ModelAndView mv = new ModelAndView("login");
		//UserDetailedBean userLoggedIn = (UserDetailedBean) userService.getUserDetailFromSpringSecurity();
		logger.info("model View: "+ mv);
		return mv;
		
		//RestTemplate restTemplate = new RestTemplate();
		
		//String result = restTemplate.getForObject("http://maps.googleapis.com/maps/api/directions/json?origin=tw33qg&destination=tw184dr&sensor=false",String.class);
		
		//System.out.println(result);
		/*request.getParameter("error");
		
		try{*/
			
		
			/*if(userLoggedIn != null){
				
				
				//checkJemmUsers(userLoggedIn);
				
				Date dt =  userLoggedIn.getGroupCreationDate();
				
				DateTime groupCreated =  new DateTime(dt).plusDays(trialPeriod);
				DateTime currentDateTime = new DateTime();
				
				if(userLoggedIn.getSubscrDate() !=null){
					Date sdt =  userLoggedIn.getSubscrDate();
					
					DateTime subscrEndDate =  new DateTime(sdt).plusDays(subscriptionPeriod);
					
					if(currentDateTime.compareTo(subscrEndDate) > 0){
						return new ModelAndView("login");
					}
					if(userLoggedIn.getAllowedStorage() != 0){
						if(userService.getGroupFolderSize() > userLoggedIn.getAllowedStorage()){
							return new ModelAndView("login");
						}
					}
					
				}
				
				if(currentDateTime.compareTo(groupCreated) > 0 && userLoggedIn.getPackageType() == 1 && userLoggedIn.getUsersCount() > 2){
					return new ModelAndView("login");
				}
				
				if(feedSourceService.topicsCount(userLoggedIn.getGroupId()) == 0 && userLoggedIn.getRoleId() < 3){
					return new ModelAndView("welcomePage","user",userLoggedIn);
				}
				
				tasksList(model, start, userLoggedIn);
				
				
				return displayJournalPage(model, feedEntryBean, start); 
			}
			else{
				return new ModelAndView("login");
				//throw new BadCredentialsException("Either the username or the password is incorrect. Please try again.");
			}*/
			
		/*}catch(UsernameNotFoundException e){
			model.addAttribute("jemmUsersMessage", e.getMessage());
			return new ModelAndView("login");
		}*/
		
	//	try {
			
			//if(setUserInSession(session) == null){
				/*User user = userService.getUserDetailFromSpringSecurity();
				user.setUserLoggedIn((byte) 1);
				
				userService.updateByPrimaryKeySelective(user);
				*/
				
				
				
				//return "home";
			
		//	}
		/*} catch (LoginException e) {
			logger.error("Session Expired");
		}*/
		
		//return new ModelAndView("login");
			
	}

	
	@RequestMapping("home")
	public String getHomePage(HttpSession session, Model model) {
		User user = userService.getUserDetailFromSpringSecurity();
		model.addAttribute("user", user);
			return "home";
	}

	@RequestMapping("checkUserLoggedin")
	@ResponseBody public String checkUserLoggedin() {
		String name = SecurityContextHolder.getContext()
				.getAuthentication().getName();	
		if (name.equals("anonymousUser")){
			return "sessiontimeout";
		}
		return "sessionok";
	}
	
	
	//@RequestMapping(value = "/loginfailed", method = RequestMethod.POST)
	@RequestMapping("/loginfailed")
	public String loginerror(ModelMap model) {
		String message = "Invalid Login. Please check your login details!";
		model.addAttribute("error", "true");
		model.addAttribute("message", "Invalid Login. Please check your login details!");
		//throw new BadCredentialsException("Either the username or the password is incorrect. Please try again.");
		return "login";

	}

	@RequestMapping(value = "logout")
	public String logoutSuccess() {
		String message = "Logout Success!";
		System.out.println("logoutmessage::"+message);
		return "redirect:/login.htm?message=" + message;
	}
	
	
	@RequestMapping(value = "getPaymentDetail")
	public String getPaymentDetail() {
		String message = "Logout Success!";
		System.out.println("logoutmessage::"+message);
		return "redirect:/login.htm?message=" + message;
	}
	

	@Override
	public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {
		 Object userName = event.getAuthentication().getPrincipal();
	        Object credentials = event.getAuthentication().getCredentials();
	        logger.debug("Failed login using USERNAME [" + userName + "]");
	        logger.debug("Failed login using PASSWORD [" + credentials + "]");
	        throw new BadCredentialsException("Invalid Login. Please check your login details!");
	       
	}
	
}

package com.jemmmedia.organise.service.impl.interceptor;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jemmmedia.organise.mybatis.db.mybatis.model.UserDetailedBean;
import com.jemmmedia.organise.service.impl.service.UserService;

@Component
public class CheckUserValidity extends HandlerInterceptorAdapter {
	
	@Resource(name = "userService")
	UserService userService; 

	static final int TRIAL_PERIOD = 14;  
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		UserDetailedBean userLoggedIn = (UserDetailedBean) userService.getUserDetailFromSpringSecurity();
		
		Date dt =  userLoggedIn.getGroupCreationDate();
		
		DateTime groupCreated =  new DateTime(dt).plusDays(TRIAL_PERIOD);
		
		DateTime currentDateTime = new DateTime();
		
		
		if(currentDateTime.compareTo(groupCreated) > 0 && userLoggedIn.getPackageType() == 0){
			response.sendRedirect("pricing");
			
			return false; 
		}
		return true;
	}
	
}

/**
 * 
 */
package com.jemmmedia.organise.service.impl.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jemmmedia.organise.mybatis.db.mybatis.model.User;
import com.jemmmedia.organise.mybatis.db.mybatis.model.UserDetailedBean;
import com.jemmmedia.organise.service.impl.exception.LoginException;
import com.jemmmedia.organise.service.impl.service.UserService;
import com.jemmmedia.organise.service.impl.service.UsersLoggedInStatusService;

/**
 * @author harjinder
 * 
 */
@RequestMapping("/")
@Controller
public class UserController extends SimpleUrlLogoutSuccessHandler {

	@Resource(name = "userService")
	UserService userService;
	
	@Resource(name = "sessionRegistry")
	private SessionRegistryImpl sessionRegistry;
	
	@Autowired
	@Qualifier("usersLoggedInStatusService")
	UsersLoggedInStatusService usersLoggedInStatusService; 
	
	//List<Long> onlineUsersIds = new CopyOnWriteArrayList<>();
	
	
	@Override
	public void onLogoutSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		if (authentication != null) {
			Object principal = authentication.getPrincipal();
				if(principal instanceof User){
					User user = (User) principal;
					usersLoggedInStatusService.removeLoggedOutUser(user.getId());		
					usersLoggedInStatusService.setIsNewUserLoggededIn(1);
				}
			
			
		}
		setDefaultTargetUrl("/login");
		super.onLogoutSuccess(request, response, authentication);
	}


	
	
	@RequestMapping("updateUserInfo")
	@ResponseBody
	int updateUserInfo(@ModelAttribute UserDetailedBean userInfo) {

		// ImageLoader.fromFile(file);
		
		UserDetailedBean userLoggedIn = (UserDetailedBean) userService.getUserDetailFromSpringSecurity();
		userLoggedIn.setFirstName(userInfo.getFirstName());
		userLoggedIn.setLastName(userInfo.getLastName());
		userLoggedIn.setEmail(userInfo.getEmail());
		userLoggedIn.setJobTitle(userInfo.getJobTitle());
		userLoggedIn.setRegionId(userInfo.getRegionId());
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(userLoggedIn, userLoggedIn.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

		return userService.updateByPrimaryKeySelective(userInfo);
	}

	@RequestMapping(value = "uploadProfileImage", method = RequestMethod.POST)
	public @ResponseBody
	File uploadProfileImage(@RequestParam("file") MultipartFile file) {
		return userService.uploadProfileImageService(file);
	}

	@RequestMapping(value = "getOnlineUsers", method = RequestMethod.POST)
	public @ResponseBody List<User> getOnlineUsers(){
		User userLoggedIn = userService.getUserDetailFromSpringSecurity();
		List<User> onlineUsers = new ArrayList<>();
		//if(usersLoggedInStatusService.getIsNewUserLoggededIn() == 1){
		//	usersLoggedInStatusService.setIsNewUserLoggededIn(0);
			
			for (Object user: sessionRegistry.getAllPrincipals()) {
				User onlineUser = (User) user;
				if(Long.compare(userLoggedIn.getGroupId(), onlineUser.getGroupId())==0){
					onlineUsers.add(onlineUser);
				}
		     }
			
			return onlineUsers;
		//}
		//return null;
	}
	
	/*@RequestMapping(value = "getOfflineUsers", method = RequestMethod.POST)
	public @ResponseBody List<User> getOfflineUsers(){
		//User userLoggedIn = userService.getUserDetailFromSpringSecurity();
		List<User> onlineUsers = new ArrayList<>();
		for (Object user: sessionRegistry.getAllPrincipals()) {
			User onlineUser = (User) user;
			onlineUsers.add(onlineUser);
	     }
		if(onlineUsers.size() <= 0){
			onlineUsers = null;
		}
		
		List<User> allActiveUsers =  CommonController.allActiveUsers;
		allActiveUsers.removeAll(onlineUsers);
		return allActiveUsers;
		//return userService.selectAllUsersDetail(userLoggedIn.getGroupId(), onlineUsersIds);
	}	*/

	@RequestMapping("updateUserPassword")
	@ResponseBody
	int updateUserPassword(@RequestParam("id") int id,
			@RequestParam("oldPassword") String oldPassword,
			@RequestParam("password") String password) throws LoginException {

		return userService.updatePassword(id, oldPassword, password);
	}
	
	@RequestMapping("updateAssignedTaskEmail")
	void updateAssignedTaskEmail(@RequestParam("taskAddEmail") String taskAddEmail) {
		boolean emailNoti = true;
		if(taskAddEmail.equalsIgnoreCase("False")){
			emailNoti = false;
		}
		
		User userLoggedIn = userService.getUserDetailFromSpringSecurity();
		userService.taskAssignedEmail(emailNoti, userLoggedIn.getId());
	}
	
	@RequestMapping("updatePostCommentEmail")
	void updatePostCommentEmail(@RequestParam("taskUpdateEmail") String taskUpdateEmail) {
		boolean emailNoti = true;
		if(taskUpdateEmail.equalsIgnoreCase("False")){
			emailNoti = false;
		}
		User userLoggedIn = userService.getUserDetailFromSpringSecurity();
		userService.postTaskEmail(emailNoti, userLoggedIn.getId());
	}
	
	@RequestMapping(value = "enableDisableUser", method = RequestMethod.POST)
	 public void enableDisableUser(@RequestParam ("userId") int userId, @RequestParam ("action") String action){
		Date updatedDate = new Date();
		if(action.equalsIgnoreCase("disable")){
			 userService.enableDisableUser(userId, null, updatedDate, 0);
		}else{
			 userService.enableDisableUser(userId, null, updatedDate, 3);
		}
		
		
	}
	@RequestMapping(value = "filterUsers", method = RequestMethod.POST)
	@ResponseBody List<UserDetailedBean> filterUser(@RequestParam ("searchStr") String searchStr  ){		
		User userLoggedIn = userService.getUserDetailFromSpringSecurity();
		List<UserDetailedBean> userList = null;	
		userList = userService.filterUsers(userLoggedIn.getGroupId(), searchStr);
		return userList;
	}
	

}

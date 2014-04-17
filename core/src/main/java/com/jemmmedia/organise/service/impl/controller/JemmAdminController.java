/**
 * 
 */
package com.jemmmedia.organise.service.impl.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.jemmmedia.organise.mybatis.db.mybatis.model.User;
import com.jemmmedia.organise.service.impl.service.JemmAdminService;

/**
 * @author harjinder
 *
 */
@RequestMapping("/")
@Controller
public class JemmAdminController extends CommonController{
	
	@Resource
	JemmAdminService jemmAdminService;
	
	@RequestMapping("jemmadmin")
	public ModelAndView createUsers(Model model) {
		
		User user = userService.getUserDetailFromSpringSecurity();
		
		getUsersDetail(model, user);
		
		RestTemplate restTemplate = new RestTemplate();
		
		
		
	   // String url = "http://localhost:8080/journal2/getAllGroups";
	    
		String url = liveUrl + "getAllGroups";
		
			
	    List<User> allgroups;
		try {
			allgroups = restTemplate.getForObject(url,List.class);
		
	 
		model.addAttribute("allGroups", allgroups);
		
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		
		return new ModelAndView("jemmAdmin");
		
	}
	
	@RequestMapping("getAllGroups")
	public @ResponseBody List<User> getAllGroups(Model model){
		
		//User user = userService.getUserDetailFromSpringSecurity();
		
		//getUsersDetail(model, user);
		
		//model.addAttribute("allGroups", jemmAdminService.selectAllGroups());
		
		return jemmAdminService.selectAllGroups();
		
	}
	
	
	@RequestMapping("viewGroup")
	public ModelAndView viewGroupDetail(Model model, @RequestParam ("id") long id){
		
		User user = userService.getUserDetailFromSpringSecurity();
		
		getUsersDetail(model, user);
		
		model.addAttribute("group", groupService.selectByPrimaryKey(id));
		model.addAttribute("usersCount", userService.countByGroup(id));
		model.addAttribute("tasksCount", feedEntryService.commentsCount(id));
		model.addAttribute("tasksCompleted", feedEntryService.tasksCompleted(id));
		
		return new ModelAndView("groupsDetail");
		
		
	}
	
	
}

/**
 * 
 */
package com.jemmmedia.organise.service.impl.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jemmmedia.organise.mybatis.db.mybatis.model.Regions;
import com.jemmmedia.organise.mybatis.db.mybatis.model.UserDetailedBean;
import com.jemmmedia.organise.service.impl.service.RegionsService;
import com.jemmmedia.organise.service.impl.service.UserService;

/**
 * @author harjinder
 *
 */
@RequestMapping("/")
@Controller
public class RegionController extends CommonController{
	
	@Resource
	RegionsService regionsService;
	
	@Resource
	UserService userService;
	
	

	@RequestMapping("addRegion")
	@ResponseBody public long addRegion(Model model, @ModelAttribute Regions region){
		
		UserDetailedBean user = (UserDetailedBean) userService.getUserDetailFromSpringSecurity();

		region.setGroupId(user.getGroupId());
		
		regionsService.insertSelective(region);
		return region.getId();
		
	}
	
	@RequestMapping("editRegion")
	@ResponseBody public int editRegion(Model model, @ModelAttribute Regions region){
		
		return regionsService.updateByPrimaryKeySelective(region);
		
	}
	
	@RequestMapping("deleteRegion")
	@ResponseBody public int deleteRegion(Model model, @RequestParam long id){
		
		if(userService.selectUsersInRegion(id) > 0){
			return 1;
		}else{
			regionsService.deleteByPrimaryKey(id);
			return 0;
		}
		
	}
	
}

/**
 * 
 */
package com.jemmmedia.organise.service.impl.controller;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jemmmedia.organise.mybatis.db.mybatis.model.Dept;
import com.jemmmedia.organise.mybatis.db.mybatis.model.UserDetailedBean;
import com.jemmmedia.organise.service.impl.service.DeptService;
import com.jemmmedia.organise.service.impl.service.UserService;

/**
 * @author harjinder
 *
 */
@RequestMapping("/")
@Controller
public class DeptController extends CommonController {
	
	@Resource
	DeptService deptService;
	
	@Resource
	UserService userService;
	
	@RequestMapping("addDept")
	@ResponseBody public long addDept(@ModelAttribute Dept dept){
		
		Date createdAt = new Date();
		
		UserDetailedBean user = (UserDetailedBean) userService.getUserDetailFromSpringSecurity();

		dept.setGroupId(user.getGroupId());
		dept.setCreatedAt(createdAt);
		dept.setUpdatedAt(createdAt);
		
		deptService.insertSelective(dept);
		return dept.getId();
		
	}
	
	@RequestMapping("editDept")
	@ResponseBody public int editRegion(Model model, @ModelAttribute Dept dept){
		
		return deptService.updateByPrimaryKeySelective(dept);
		
	}
	
	@RequestMapping("deleteDept")
	@ResponseBody public int deleteRegion(Model model, @RequestParam long id){
			deptService.deleteByPrimaryKey(id);
			return 0;
	}

}

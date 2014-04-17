/**
 * 
 */
package com.jemmmedia.organise.service.impl.controller;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jemmmedia.organise.mybatis.db.mybatis.dao.RolesUsersMapper;
import com.jemmmedia.organise.mybatis.db.mybatis.model.RolesUsers;
import com.jemmmedia.organise.service.impl.service.RolesService;

/**
 * @author harjinder
 *
 */
@Controller
@RequestMapping("/")
public class RoleController {
	
	@Resource(name = "rolesService")
	RolesService rolesService;
	
	@Resource
	RolesUsersMapper rolesUsersMapper;
	
	@RequestMapping(value = "updateRole", method = RequestMethod.POST)
	public void updateRole(@ModelAttribute RolesUsers rolesUsers){
		Date updatedDate = new Date();
		rolesUsers.setUpdatedAt(updatedDate);
		rolesService.insertOrUpdateSelective(rolesUsers);
		
		/*if(rolesUsersMapper.getUserRoleId(rolesUsers.getUserId())>0){
			RolesUsersExample example = null;
			rolesUsersMapper.updateByExampleSelective(rolesUsers, example);
		}else{
			rolesUsersMapper.insertSelective(rolesUsers);
		}*/
		
	}
		
}

/**
 * 
 */
package com.jemmmedia.organise.service.impl.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jemmmedia.organise.mybatis.db.mybatis.model.Roles;
import com.jemmmedia.organise.mybatis.db.mybatis.model.RolesUsers;
import com.jemmmedia.organise.service.impl.dao.RolesDao;
import com.jemmmedia.organise.service.impl.service.RolesService;

/**
 * @author harjinder
 *
 */
@Service("rolesService")
public class RolesServiceImpl implements RolesService {

	@Resource(name="rolesDao")
	RolesDao rolesDao;
	
	
	/* (non-Javadoc)
	 * @see com.jemmmedia.organise.service.impl.service.RolesService#getAllRoles()
	 */
	@Override
	public List<Roles> getAllRoles() {
		List<Roles> roles = null;
		try {
			roles = rolesDao.getAllRoles();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return roles;
	}
	
	@Override
	public void insertOrUpdateSelective(RolesUsers rolesUsers) {
		Date date = new Date();
		rolesUsers.setCreatedAt(date);
		rolesUsers.setUpdatedAt(date);
		try {
			rolesDao.insertOrUpdateSelective(rolesUsers);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public int getUserRoleId(int userId) {
		 return rolesDao.getUserRoleId(userId);
	}

}

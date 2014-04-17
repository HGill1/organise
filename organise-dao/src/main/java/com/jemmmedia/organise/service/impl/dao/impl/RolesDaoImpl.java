/**
 * 
 */
package com.jemmmedia.organise.service.impl.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jemmmedia.organise.mybatis.db.mybatis.dao.RolesMapper;
import com.jemmmedia.organise.mybatis.db.mybatis.dao.RolesUsersMapper;
import com.jemmmedia.organise.mybatis.db.mybatis.model.Roles;
import com.jemmmedia.organise.mybatis.db.mybatis.model.RolesUsers;
import com.jemmmedia.organise.service.impl.dao.RolesDao;

/**
 * @author harjinder
 *
 */
@Service("rolesDao")
public class RolesDaoImpl implements RolesDao {

	@Resource
	RolesMapper rolesMapper;
	
	@Resource
	RolesUsersMapper rolesUsersMapper;
	
	/* (non-Javadoc)
	 * @see com.jemmmedia.organise.service.impl.dao.RolesDao#getAllRoles()
	 */
	@Override
	public List<Roles>getAllRoles() {
		return rolesMapper.getAllRoles();
	}
	
	@Override
	public void insertOrUpdateSelective(RolesUsers rolesUsers) {
		 rolesUsersMapper.insertOrUpdateSelective(rolesUsers);
	}
	
	@Override
	public int getUserRoleId(int userId) {
		 return rolesUsersMapper.getUserRoleId(userId);
	}

}

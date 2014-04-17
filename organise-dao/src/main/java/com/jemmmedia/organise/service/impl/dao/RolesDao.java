/**
 * 
 */
package com.jemmmedia.organise.service.impl.dao;

import java.util.List;

import com.jemmmedia.organise.mybatis.db.mybatis.model.Roles;
import com.jemmmedia.organise.mybatis.db.mybatis.model.RolesUsers;

/**
 * @author harjinder
 *
 */
public interface RolesDao {
	
	void insertOrUpdateSelective(RolesUsers rolesUsers);

	int getUserRoleId(int userId);

	List<Roles> getAllRoles();

}

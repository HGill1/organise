/**
 * 
 */
package com.jemmmedia.organise.service.impl.service;

import java.util.List;

import com.jemmmedia.organise.mybatis.db.mybatis.model.Roles;
import com.jemmmedia.organise.mybatis.db.mybatis.model.RolesUsers;

/**
 * @author harjinder
 *
 */
public interface RolesService {

	List<Roles>getAllRoles();

	void insertOrUpdateSelective(RolesUsers rolesUsers);

	int getUserRoleId(int userId);
}

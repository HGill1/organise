/**
 * 
 */
package com.jemmmedia.organise.service.impl.service;

import java.util.List;

import com.jemmmedia.organise.mybatis.db.mybatis.model.User;

/**
 * @author harjinder
 *
 */
public interface JemmAdminService {

	List<User> selectAllGroups();

}

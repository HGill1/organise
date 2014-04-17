/**
 * 
 */
package com.jemmmedia.organise.service.impl.dao;

import java.util.List;

import com.jemmmedia.organise.mybatis.db.mybatis.model.User;

/**
 * @author harjinder
 *
 */
public interface JemmAdminDao {

	List<User> selectAllGroups();

}

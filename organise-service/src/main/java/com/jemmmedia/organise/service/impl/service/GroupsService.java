/**
 * 
 */
package com.jemmmedia.organise.service.impl.service;

import java.util.List;

import com.jemmmedia.organise.mybatis.db.mybatis.model.Groups;


/**
 * @author harjinder
 *
 */
public interface GroupsService {

	void insertSelective(Groups group);

	void updateByPrimaryKeySelective(Groups group);

	String selectGroupPackage(long groupId);

	String selectEmailsForRemiders();

	List<Groups> selectAllGroups();

	Groups selectByPrimaryKey(long groupId);
	
}

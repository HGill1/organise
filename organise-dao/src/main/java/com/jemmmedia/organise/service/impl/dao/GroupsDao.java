/**
 * 
 */
package com.jemmmedia.organise.service.impl.dao;

import java.util.List;

import com.jemmmedia.organise.mybatis.db.mybatis.model.Groups;

/**
 * @author harjinder
 *
 */
public interface GroupsDao {

	int insertSelective(Groups group);

	int updateByPrimaryKeySelective(Groups group);

	String selectGroupPackage(long groupId);

	String selectEmailsForRemiders();

	List<Groups> selectAllGroups();

	Groups selectByPrimaryKey(long groupId);

}

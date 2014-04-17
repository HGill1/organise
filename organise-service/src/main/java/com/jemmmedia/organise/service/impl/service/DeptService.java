/**
 * 
 */
package com.jemmmedia.organise.service.impl.service;

import java.util.List;

import com.jemmmedia.organise.mybatis.db.mybatis.model.Dept;

/**
 * @author harjinder
 *
 */
public interface DeptService {

	int insertSelective(Dept dept);

	int updateByPrimaryKeySelective(Dept dept);

	int deleteByPrimaryKey(long deptId);

	List<Dept> selectAllDepts(long groupId);

	void insertDeptsForUser(List<Long> deptsId, long userId);

	List<String> selectUsersEmailInDept(long deptId);

	List<Dept> selectAllUsersInDepts(long groupId);

}

/**
 * 
 */
package com.jemmmedia.organise.service.impl.dao;

import java.util.List;

import com.jemmmedia.organise.mybatis.db.mybatis.model.Dept;
import com.jemmmedia.organise.mybatis.db.mybatis.model.DeptUsers;

/**
 * @author harjinder
 *
 */
public interface DeptDao {

	int insertSelective(Dept dept);

	int updateByPrimaryKeySelective(Dept dept);

	int deleteByPrimaryKey(long deptId);

	List<Dept> selectAllDepts(long groupId);

	int insertDeptUsers(DeptUsers deptUsers);

	void deleteAllUserDept(long userId);

	List<String> selectUsersEmailInDept(long deptId);

	List<Dept> selectAllUsersInDepts(long groupId);

}

/**
 * 
 */
package com.jemmmedia.organise.service.impl.dao;

import java.util.List;

import com.jemmmedia.organise.mybatis.db.mybatis.model.Regions;
import com.jemmmedia.organise.mybatis.db.mybatis.model.User;
import com.jemmmedia.organise.mybatis.db.mybatis.model.UserDetailedBean;

/**
 * @author harjinder
 *
 */
public interface UserDao {
	UserDetailedBean getUserByName(String username);
	int updateByPrimaryKeySelective(User userInfo);
	User selectByPrimaryKey(long id);
	List<UserDetailedBean> selectAllUsersDetail(long groupId, List<Long> ids);
	int insertSelective(User user);
	List<Regions> selectAllRegions(long groupId);
	List<User> selectCheckedUsers(String usersId);
	List<User> selectUnCheckedUsers(long groupId, List<String> usersId);
	int countByGroup(long groupId);
	int checkUserExist(String email);
	UserDetailedBean getUserPackage(long groupId);
	int deleteByPrimaryKey(long id);
	void taskAssignedEmail(boolean taskAddEmail, long id);
	void postTaskEmail(boolean taskUpdateEmail, long id);
	int selectUsersInRegion(long regionId);
	String selectSuperUersEmail(Long groupId);
	List<User> getAllUsers(long groupId, List<Long> ids);
	List<User> selectUsersInTask(long id, List<Long> ids);
	List<UserDetailedBean> filterUsers(long groupId, String searchStr);
}

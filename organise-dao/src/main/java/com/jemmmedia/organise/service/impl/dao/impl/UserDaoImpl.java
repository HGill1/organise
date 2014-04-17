/**
 * 
 */
package com.jemmmedia.organise.service.impl.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jemmmedia.organise.mybatis.db.mybatis.dao.RegionsMapper;
import com.jemmmedia.organise.mybatis.db.mybatis.dao.UserMapper;
import com.jemmmedia.organise.mybatis.db.mybatis.model.Regions;
import com.jemmmedia.organise.mybatis.db.mybatis.model.User;
import com.jemmmedia.organise.mybatis.db.mybatis.model.UserDetailedBean;
import com.jemmmedia.organise.service.impl.dao.UserDao;

/**
 * @author harjinder
 *
 */
@Service("userDao")
public class UserDaoImpl implements UserDao {

	@Resource
	private UserMapper userMapper;
	
	@Resource
	private RegionsMapper regionsMapper;
	
	/* (non-Javadoc)
	 * @see com.jemmmedia.organise.service.impl.dao.UserDao#getUserByName(java.lang.String)
	 */
	@Override
	public UserDetailedBean getUserByName(String username) {
		return userMapper.getUser(username);
	}
	
	@Override
	public int updateByPrimaryKeySelective(User userInfo) {
		 return userMapper.updateByPrimaryKeySelective(userInfo);
	}

	@Override
	public List<User> getAllUsers(long groupId, List<Long> ids) {
		return userMapper.getAllUsers(groupId,ids);
	}
	
	@Override
	public UserDetailedBean getUserPackage(long groupId) {
		return userMapper.getUserPackage(groupId);
	}
	
	@Override
	public List<UserDetailedBean> selectAllUsersDetail(long groupId, List<Long> ids) {
		return userMapper.selectAllUsersDetail(groupId,ids);
	}
	
	@Override
	public List<UserDetailedBean> filterUsers(long groupId, String searchStr) {
		return userMapper.filterUsers(groupId, searchStr);
	}
	
	@Override
	public User selectByPrimaryKey(long id) {
		return userMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public int insertSelective(User user) {
		return userMapper.insertSelective(user);
	}
	
	@Override
	public List<Regions> selectAllRegions(long groupId) {
		return regionsMapper.selectAllRegions(groupId);
	}
	
	@Override
	public List<User> selectCheckedUsers(String usersId) {
		return userMapper.selectCheckedUsers(usersId);
	}
	
	@Override
	public List<User> selectUnCheckedUsers(long groupId,List<String> usersId) {
		return userMapper.selectUnCheckedUsers(groupId, usersId);
	}
	
	@Override
	public int countByGroup(long groupId) {
		return userMapper.countByGroup(groupId);
	}
	
	@Override
	public int checkUserExist(String email) {
		return userMapper.checkUserExist(email);
	}
	
	@Override
	public int selectUsersInRegion(long regionId) {
		return userMapper.selectUsersInRegion(regionId);
	}
	
	@Override
	public int deleteByPrimaryKey(long id) {
		return userMapper.deleteByPrimaryKey(id);
	}
	
	@Override
	public void taskAssignedEmail(boolean taskAddEmail, long id) {
		 userMapper.taskAssignedEmail(taskAddEmail, id);
	}
	
	@Override
	public void postTaskEmail(boolean taskUpdateEmail, long id) {
		 userMapper.postTaskEmail(taskUpdateEmail, id);
	}
	
	@Override
	public String selectSuperUersEmail(Long groupId) {
		return userMapper.selectSuperUersEmail(groupId);
	}
	
	@Override
	public List<User> selectUsersInTask(long id,List<Long> ids){
		
		return userMapper.selectUsersInTask(id,ids);
		
	}
	
}

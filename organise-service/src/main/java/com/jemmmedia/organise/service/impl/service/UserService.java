package com.jemmmedia.organise.service.impl.service;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.jemmmedia.organise.mybatis.db.mybatis.model.Regions;
import com.jemmmedia.organise.mybatis.db.mybatis.model.User;
import com.jemmmedia.organise.mybatis.db.mybatis.model.UserDetailedBean;
import com.jemmmedia.organise.service.impl.exception.LoginException;

public interface UserService {
	User doLogin(String username, String password)throws LoginException;
	boolean checkPassword(String username, String password);
	UserDetailedBean getUserByName(String name) throws LoginException;
	User getUserDetailFromSpringSecurity();
	int updateByPrimaryKeySelective(User userInfo);
	User selectByPrimaryKey(int id) throws LoginException;
	File uploadProfileImageService(MultipartFile file)
			throws IllegalStateException;
	int updatePassword(int id, String oldPassword, String password)
			throws LoginException;
	List<UserDetailedBean> selectAllUsersDetail(long groupId, List<Long> onlineUsersIds);
	void insertSelective(UserDetailedBean user);
	List<Regions> selectAllRegions(long groupId);
	List<User> selectUnCheckedUsers(long groupId, List<String> usersId);
	List<User> selectCheckedUsers(String usersId);
	int countByGroup(long groupId);
	int checkUserExist(String email);
	UserDetailedBean getUserPackage(long groupId) throws LoginException;
	int deleteByPrimaryKey(long id);
	double getGroupFolderSize();
	public void postTaskEmail(boolean taskUpdateEmail, long id);
	public void taskAssignedEmail(boolean taskAddEmail, long id);
	void enableDisableUser(long userId, Date createdDate, Date updatedDate,
			int roleId);
	int selectUsersInRegion(long regionId);
	String selectSuperUersEmail(Long groupId);
	List<User> getAllUsers(long groupId, List<Long> ids);
	List<User> selectUsersInTask(long id, List<Long> ids);
	List<UserDetailedBean> filterUsers(long groupId, String searchStr);
}

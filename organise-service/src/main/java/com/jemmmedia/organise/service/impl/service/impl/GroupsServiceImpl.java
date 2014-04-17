/**
 * 
 */
package com.jemmmedia.organise.service.impl.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jemmmedia.organise.mybatis.db.mybatis.model.Groups;
import com.jemmmedia.organise.service.impl.dao.GroupsDao;
import com.jemmmedia.organise.service.impl.service.GroupsService;

/**
 * @author harjinder
 *
 */
@Service("groupsService")
public class GroupsServiceImpl implements GroupsService {
	
	@Resource
	GroupsDao groupsDao;


	@Override
	public void insertSelective(Groups group){
		
		try {
			groupsDao.insertSelective(group);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void updateByPrimaryKeySelective(Groups group){
		
		try {
			groupsDao.updateByPrimaryKeySelective(group);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public String selectGroupPackage(long groupId){
		
		String packageName = null;
		
		try {
			packageName = groupsDao.selectGroupPackage(groupId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return packageName;
	}
	
	@Override
	public String selectEmailsForRemiders(){
		
		String usersEmailIds = null;
		
		try {
			usersEmailIds = groupsDao.selectEmailsForRemiders();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usersEmailIds;
	}
	
	@Override
	public List<Groups> selectAllGroups(){
		
		List<Groups> list = null;
		
		try {
			list =  groupsDao.selectAllGroups();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public Groups selectByPrimaryKey(long groupId){
		
		Groups group = null;
		
		try {
			group =  groupsDao.selectByPrimaryKey(groupId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return group;
	}

}

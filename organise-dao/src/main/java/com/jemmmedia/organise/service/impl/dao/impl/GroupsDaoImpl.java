/**
 * 
 */
package com.jemmmedia.organise.service.impl.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jemmmedia.organise.mybatis.db.mybatis.dao.GroupsMapper;
import com.jemmmedia.organise.mybatis.db.mybatis.model.Groups;
import com.jemmmedia.organise.service.impl.dao.GroupsDao;

/**
 * @author harjinder
 *
 */
@Service("groupsDao")
public class GroupsDaoImpl implements GroupsDao {

	@Resource
	GroupsMapper groupsMapper;
	
	@Override
	public int insertSelective(Groups group){
		
		return groupsMapper.insertSelective(group);
		
	}
	
	@Override
	public int updateByPrimaryKeySelective(Groups group){
		
		return groupsMapper.updateByPrimaryKeySelective(group);
		
	}
	
	@Override
	public String selectGroupPackage(long groupId){
		
		return groupsMapper.selectGroupPackage(groupId);
		
	}
	
	@Override
	public String selectEmailsForRemiders(){
		
		return groupsMapper.selectEmailsForRemiders();
		
	}
	
	@Override
	public List<Groups> selectAllGroups(){
		
		return groupsMapper.selectAllGroups();
		
	}
	
	@Override
	public Groups selectByPrimaryKey(long groupId){
		
		return groupsMapper.selectByPrimaryKey(groupId);
		
	}
	
}

/**
 * 
 */
package com.jemmmedia.organise.service.impl.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jemmmedia.organise.mybatis.db.mybatis.dao.UserMapper;
import com.jemmmedia.organise.mybatis.db.mybatis.model.User;
import com.jemmmedia.organise.service.impl.dao.JemmAdminDao;

/**
 * @author harjinder
 *
 */
@Service("jemmAdminDao")
public class JemmAdminDaoImpl implements JemmAdminDao {
	
	@Resource
	private UserMapper userMapper;

	@Override
	public List<User> selectAllGroups(){
		
		return userMapper.selectAllGroups();
		
	}
	
}

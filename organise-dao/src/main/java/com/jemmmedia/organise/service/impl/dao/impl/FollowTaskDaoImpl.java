/**
 * 
 */
package com.jemmmedia.organise.service.impl.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jemmmedia.organise.mybatis.db.mybatis.dao.FollowersMapper;
import com.jemmmedia.organise.mybatis.db.mybatis.model.Followers;
import com.jemmmedia.organise.service.impl.dao.FollowTaskDao;

/**
 * @author harjinder
 *
 */
@Service("followTaskDao")
public class FollowTaskDaoImpl implements FollowTaskDao {
	
	@Resource
	FollowersMapper followersMapper;
	
	@Override
	public void insert(Followers follower){
		
		followersMapper.insert(follower);
		
	}

}

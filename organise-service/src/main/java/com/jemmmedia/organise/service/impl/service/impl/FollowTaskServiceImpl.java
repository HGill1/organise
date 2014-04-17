/**
 * 
 */
package com.jemmmedia.organise.service.impl.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jemmmedia.organise.mybatis.db.mybatis.model.Followers;
import com.jemmmedia.organise.service.impl.dao.FollowTaskDao;
import com.jemmmedia.organise.service.impl.service.FollowTaskService;

/**
 * @author harjinder
 *
 */
@Service("followTaskService")
public class FollowTaskServiceImpl implements FollowTaskService {
	
	@Resource
	FollowTaskDao followTaskDao;
	
	@Override
	public void insert(Followers follower){
		
		followTaskDao.insert(follower);
		
	}
	
	
}

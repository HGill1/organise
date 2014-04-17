/**
 * 
 */
package com.jemmmedia.organise.service.impl.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jemmmedia.organise.mybatis.db.mybatis.dao.FeedsUsersMapper;
import com.jemmmedia.organise.mybatis.db.mybatis.model.FeedsUsersKey;
import com.jemmmedia.organise.mybatis.db.mybatis.model.FeedEntryBean;
import com.jemmmedia.organise.service.impl.dao.FeedsUsersDao;

/**
 * @author harjinder
 *
 */
@Service("feedsUsersDao")
public class FeedsUsersDaoImpl implements FeedsUsersDao {
	
	@Resource
	FeedsUsersMapper feedsUsersMapper;
	
	@Override
	public int insertSelective(FeedsUsersKey feedsUsersKey){
		return feedsUsersMapper.insertSelective(feedsUsersKey);
	}
	
	@Override
	public int deleteByPrimaryKey(FeedsUsersKey feedsUsersKey){
		return feedsUsersMapper.deleteByPrimaryKey(feedsUsersKey);
	}
	
	@Override
	public List<Long> selectFeedSourceIds(int userId){
		 return feedsUsersMapper.selectFeedSourceIds(userId);
	}
	
	@Override
	public List<FeedEntryBean> multiSourceFilterFeeds(int userId, int pageStart) {
		return feedsUsersMapper.multiSourceFilterFeeds(userId,pageStart);
	}
	
	@Override
	public void deleteSourcesUsers(Long id) {
		 feedsUsersMapper.deleteSourcesUsers(id);
	}
}

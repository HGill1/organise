/**
 * 
 */
package com.jemmmedia.organise.service.impl.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jemmmedia.organise.mybatis.db.mybatis.model.FeedsUsersKey;
import com.jemmmedia.organise.mybatis.db.mybatis.model.FeedEntryBean;
import com.jemmmedia.organise.service.impl.dao.FeedsUsersDao;
import com.jemmmedia.organise.service.impl.service.FeedEntryService;
import com.jemmmedia.organise.service.impl.service.FeedsUsersService;

/**
 * @author harjinder
 *
 */
@Service("feedsUsersService")
public class FeedsUsersServiceImpl implements FeedsUsersService {

	@Resource
	FeedsUsersDao feedsUsersDao;
	
	@Resource
	FeedEntryService feedEntryService;
	
	@Override
	public int insertSelective(FeedsUsersKey feedsUsersKey){
		int insertCount = 0;
		try {
			insertCount = feedsUsersDao.insertSelective(feedsUsersKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return insertCount;
	}
	
	@Override
	public int deleteByPrimaryKey(FeedsUsersKey feedsUsersKey){
		int deleteCount = 0;
		try {
			deleteCount = feedsUsersDao.deleteByPrimaryKey(feedsUsersKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return deleteCount;
	}
	
	@Override
	public List<Long> selectFeedSourceIds(int userId){
		List<Long> listCategories =  null;
		try {
			listCategories = feedsUsersDao.selectFeedSourceIds(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listCategories;
	}
	
	@Override
	public List<List<FeedEntryBean>> multiSourceFilterFeeds(int userId, int pageStart) {
		List<List<FeedEntryBean>> allFeedsList = null;
		try {
			List<FeedEntryBean>  listMultiSourceFilterMainFeeds =  feedsUsersDao.multiSourceFilterFeeds(userId,pageStart);
			allFeedsList = feedEntryService.getFeedsWithSubFeeds(listMultiSourceFilterMainFeeds);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return allFeedsList;
	}
	
	@Override
	public void deleteSourcesUsers(Long id) {
		feedsUsersDao.deleteSourcesUsers(id);
	}
	
}

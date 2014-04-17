/**
 * 
 */
package com.jemmmedia.organise.service.impl.dao;

import java.util.List;

import com.jemmmedia.organise.mybatis.db.mybatis.model.FeedsUsersKey;
import com.jemmmedia.organise.mybatis.db.mybatis.model.FeedEntryBean;

/**
 * @author harjinder
 *
 */
public interface FeedsUsersDao {

	int insertSelective(FeedsUsersKey feedsUsersKey);

	int deleteByPrimaryKey(FeedsUsersKey feedsUsersKey);

	List<Long> selectFeedSourceIds(int userId);

	List<FeedEntryBean> multiSourceFilterFeeds(int userId, int pageStart);

	void deleteSourcesUsers(Long id);
	
}

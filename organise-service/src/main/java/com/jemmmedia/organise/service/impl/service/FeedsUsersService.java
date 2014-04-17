/**
 * 
 */
package com.jemmmedia.organise.service.impl.service;

import java.util.List;

import com.jemmmedia.organise.mybatis.db.mybatis.model.FeedsUsersKey;
import com.jemmmedia.organise.mybatis.db.mybatis.model.FeedEntryBean;

/**
 * @author harjinder
 *
 */
public interface FeedsUsersService {

	int deleteByPrimaryKey(FeedsUsersKey feedsUsersKey);

	int insertSelective(FeedsUsersKey feedsUsersKey);

	List<Long> selectFeedSourceIds(int userId);

	List<List<FeedEntryBean>> multiSourceFilterFeeds(int userId, int pageStart);

	void deleteSourcesUsers(Long id);

}

/**
 * 
 */
package com.jemmmedia.organise.service.impl.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jemmmedia.organise.mybatis.db.mybatis.model.FeedEntry;
import com.jemmmedia.organise.mybatis.db.mybatis.model.FeedSource;
import com.jemmmedia.organise.mybatis.db.mybatis.model.FeedSourceExtBean;

/**
 * @author harjinder
 *
 */
public interface FeedSourceService {
	Map<Integer, FeedSource> getAllSources();

	List<FeedSourceExtBean> selectFeedSources();

	void readRssFeeds(FeedSourceExtBean feedSource, FeedEntry feedEntry)
			throws MalformedURLException, IOException;

	List<FeedSource> selectFollowCat(long followerId);

	List<FeedSourceExtBean> selectAllSources(long groupId);

	int insertSelective(FeedSource feedSource);

	void insert(ArrayList<Long> checkedUserId, long feedSourceId);

	FeedSourceExtBean selectFeedSourceForEdit(long id);

	int updateByPrimaryKeySelective(FeedSource feedSource);

	void deleteSourceUserEntry(long feedSourceId);

	List<FeedSourceExtBean> selectUsersFeedSources(long userId);

	void deleteSource(long id);

	void deleteSubSource(long parentId);

	List<FeedSourceExtBean> selectSubTopics(Long parentId);

	int topicsCount(long groupId);

	void insertTopicsForUser(List<Long> list, long userId);
	
}

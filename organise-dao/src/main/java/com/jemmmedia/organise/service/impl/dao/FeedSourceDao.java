/**
 * 
 */
package com.jemmmedia.organise.service.impl.dao;

import java.util.List;
import java.util.Map;

import com.jemmmedia.organise.mybatis.db.mybatis.model.FeedSource;
import com.jemmmedia.organise.mybatis.db.mybatis.model.FeedSources_Users;
import com.jemmmedia.organise.mybatis.db.mybatis.model.FeedSourceExtBean;

/**
 * @author harjinder
 *
 */
public interface FeedSourceDao {
	public Map<Integer, FeedSource> getAllSources();

	List<FeedSourceExtBean> selectFeedSources();

	List<FeedSource> selectFollowCat(long followerId);

	List<FeedSourceExtBean> selectAllSources(long groupId);

	int insertSelective(FeedSource feedSource);

	void insert(FeedSources_Users feedSources_Users);

	FeedSourceExtBean selectFeedSourceForEdit(long id);

	int updateByPrimaryKeySelective(FeedSource feedSource);

	void deleteSourceUserEntry(long feedSourceId);

	List<FeedSourceExtBean> selectUsersFeedSources(long userId);

	void deleteByPrimaryKey(long id);

	void deleteSubSource(long id);

	List<FeedSourceExtBean> selectSubTopics(Long parentId);

	int topicsCount(long groupId);
}

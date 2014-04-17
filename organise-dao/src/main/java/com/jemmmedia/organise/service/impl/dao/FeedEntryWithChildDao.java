/**
 * 
 */
package com.jemmmedia.organise.service.impl.dao;

import java.util.List;

import com.jemmmedia.organise.mybatis.db.mybatis.model.FeedEntryWithChild;


/**
 * @author harjinder
 *
 */
public interface FeedEntryWithChildDao {
	public void insertChild(FeedEntryWithChild feedEntryWithChild);

	void deleteFeedEntryChild(Long id);

	List<FeedEntryWithChild> selectChildFeedIds(Long id);
}

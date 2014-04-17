/**
 * 
 */
package com.jemmmedia.organise.service.impl.dao;

import java.util.Map;

import com.jemmmedia.organise.mybatis.db.mybatis.model.FeedTypes;
import com.jemmmedia.organise.mybatis.db.mybatis.model.Feeds;

/**
 * @author harjinder
 *
 */
public interface FeedsDao {
	
	Map<Integer,FeedTypes> getAllFeedTypes();

	void insertSelective(Feeds feeds);

}

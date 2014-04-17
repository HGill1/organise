package com.jemmmedia.organise.service.impl.service;

import java.util.Map;

import com.jemmmedia.organise.mybatis.db.mybatis.model.FeedTypes;
import com.jemmmedia.organise.mybatis.db.mybatis.model.Feeds;

public interface FeedsService {
	
	public Map<Integer,FeedTypes> getAllFeedTypes();

	void insertSelective(Feeds feeds);

}

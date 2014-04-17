/**
 * 
 */
package com.jemmmedia.organise.service.impl.dao.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jemmmedia.organise.mybatis.db.mybatis.dao.FeedTypesMapper;
import com.jemmmedia.organise.mybatis.db.mybatis.dao.FeedsMapper;
import com.jemmmedia.organise.mybatis.db.mybatis.model.FeedTypes;
import com.jemmmedia.organise.mybatis.db.mybatis.model.Feeds;
import com.jemmmedia.organise.service.impl.dao.FeedsDao;

/**
 * @author harjinder
 *
 */
@Service("feedsDao")
public class FeedsDaoImpl implements FeedsDao {

	/* (non-Javadoc)
	 * @see com.jemmmedia.organise.service.impl.dao.FeedsDao#getAllSources()
	 */
	
	
	@Resource(name="feedTypesMapper")
	FeedTypesMapper feedTypesMapper;
	
	@Resource
	FeedsMapper feedsMapper;
	
	@Override
	public Map<Integer, FeedTypes> getAllFeedTypes() {
		
		
		
		return feedTypesMapper.getAllFeedTypes();		
		
	}
	@Override
	public void insertSelective(Feeds feeds){
		
		feedsMapper.insertSelective(feeds);
		
	}

}

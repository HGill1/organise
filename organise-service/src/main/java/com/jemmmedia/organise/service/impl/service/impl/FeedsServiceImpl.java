/**
 * 
 */
package com.jemmmedia.organise.service.impl.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jemmmedia.organise.mybatis.db.mybatis.model.FeedTypes;
import com.jemmmedia.organise.mybatis.db.mybatis.model.Feeds;
import com.jemmmedia.organise.service.impl.dao.FeedsDao;
import com.jemmmedia.organise.service.impl.service.FeedsService;

/**
 * @author harjinder
 *
 */
@Service("feedsService") 
public class FeedsServiceImpl implements FeedsService {
	
	@Resource(name ="feedsDao")
	FeedsDao feedsDao;
	
	/* (non-Javadoc)
	 * @see com.jemmmedia.organise.service.impl.service.FeedsService#getAllSources()
	 */
	@Override
	public Map<Integer, FeedTypes> getAllFeedTypes() {
		return feedsDao.getAllFeedTypes();
	}
	
	@Override
	public void insertSelective(Feeds feeds){
		feedsDao.insertSelective(feeds);
	}	

}

/**
 * 
 */
package com.jemmmedia.organise.service.impl.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jemmmedia.organise.mybatis.db.mybatis.model.FeedEntryWithChild;
import com.jemmmedia.organise.service.impl.dao.FeedEntryWithChildDao;
import com.jemmmedia.organise.service.impl.service.FeedEntryWithChildService;

/**
 * @author harjinder
 *
 */
@Service("feedEntryWithChildService")
public class FeedEntryWithChildServiceImpl implements FeedEntryWithChildService {

	@Resource(name ="feedEntryWithChildDao")
	FeedEntryWithChildDao feedEntryWithChildDao;
	
	/* (non-Javadoc)
	 * @see com.jemmmedia.organise.service.impl.service.FeedEntryWithChildService#insertChild(com.jemmmedia.organise.service.impl.db.mybatis.model.FeedEntryWithChild)
	 */
	@Override
	public void insertChild(FeedEntryWithChild feedEntryWithChild) {
		feedEntryWithChildDao.insertChild(feedEntryWithChild);

	}
	
	@Override
	public  List<FeedEntryWithChild> selectChildFeedIds(Long id) {		
		return feedEntryWithChildDao.selectChildFeedIds(id);
	}
	
	@Override
	public void deleteFeedEntryChild(Long id) {
		feedEntryWithChildDao.deleteFeedEntryChild(id);

	}

}

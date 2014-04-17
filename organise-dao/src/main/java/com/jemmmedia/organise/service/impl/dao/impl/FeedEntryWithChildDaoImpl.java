/**
 * 
 */
package com.jemmmedia.organise.service.impl.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jemmmedia.organise.mybatis.db.mybatis.dao.FeedEntryWithChildMapper;
import com.jemmmedia.organise.mybatis.db.mybatis.model.FeedEntryWithChild;
import com.jemmmedia.organise.service.impl.dao.FeedEntryWithChildDao;

/**
 * @author harjinder
 *
 */
@Service("feedEntryWithChildDao")
public class FeedEntryWithChildDaoImpl implements FeedEntryWithChildDao {

@Resource
FeedEntryWithChildMapper feedEntryWithChildMapper;
	/* (non-Javadoc)
	 * @see com.jemmmedia.organise.service.impl.dao.FeedEntryWithChildDao#insertChild()
	 */
	@Override
	public void insertChild(FeedEntryWithChild feedEntryWithChild) {
		feedEntryWithChildMapper.insertSelective(feedEntryWithChild);

	}
	
	@Override
	public  List<FeedEntryWithChild> selectChildFeedIds(Long id) {
		
		return feedEntryWithChildMapper.selectChildFeedIds(id);

	}
	
	@Override
	public void deleteFeedEntryChild(Long id) {
		feedEntryWithChildMapper.deleteFeedEntryChild(id);

	}

}

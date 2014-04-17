/**
 * 
 */
package com.jemmmedia.organise.service.impl.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jemmmedia.organise.mybatis.db.mybatis.model.FeedEntriesAttachments;
import com.jemmmedia.organise.service.impl.dao.FeedEntriesAttachmentsDao;
import com.jemmmedia.organise.service.impl.service.FeedEntriesAttachmentsService;

/**
 * @author harjinder
 *
 */
@Service("feedEntriesAttachmentsService")
public class FeedEntriesAttachmentsServiceImpl implements
		FeedEntriesAttachmentsService {
	
	@Resource(name = "feedEntriesAttachmentsDao")
	FeedEntriesAttachmentsDao feedEntriesAttachmentsDao;
	

	/* (non-Javadoc)
	 * @see com.jemmmedia.organise.service.impl.service.FeedEntriesAttachmentsService#insertAttachments(com.jemmmedia.organise.service.impl.db.mybatis.model.FeedEntriesAttachments)
	 */
	@Override
	public void insertAttachments(FeedEntriesAttachments feedEntriesAttachments) {
		feedEntriesAttachmentsDao.insertAttachments(feedEntriesAttachments);
	}


	@Override
	public List<Long> selectById(Long id) {
		return feedEntriesAttachmentsDao.selectById(id);
	}
	
	@Override
	public void deleteById(Long id) {
		 feedEntriesAttachmentsDao.selectById(id);
	}
	
	

}

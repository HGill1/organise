/**
 * 
 */
package com.jemmmedia.organise.service.impl.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jemmmedia.organise.mybatis.db.mybatis.dao.FeedEntriesAttachmentsMapper;
import com.jemmmedia.organise.mybatis.db.mybatis.model.FeedEntriesAttachments;
import com.jemmmedia.organise.service.impl.dao.FeedEntriesAttachmentsDao;

/**
 * @author harjinder
 *
 */
@Service("feedEntriesAttachmentsDao")
public class FeedEntriesAttachmentsDaoImpl implements FeedEntriesAttachmentsDao {
	
	@Resource
	FeedEntriesAttachmentsMapper feedEntriesAttachmentsMapper;

	/* (non-Javadoc)
	 * @see com.jemmmedia.organise.service.impl.dao.FeedEntriesAttachmentsDao#insertAttachments(com.jemmmedia.organise.service.impl.db.mybatis.model.FeedEntriesAttachments)
	 */
	@Override
	public void insertAttachments(FeedEntriesAttachments feedEntriesAttachments) {
		
		feedEntriesAttachmentsMapper.insertSelective(feedEntriesAttachments);
		
	}
	@Override
	public List<Long> selectById(Long id) {
		
		return feedEntriesAttachmentsMapper.selectById(id);
		
	}
	
	@Override
	public void deleteById(Long id) {
		
		feedEntriesAttachmentsMapper.selectById(id);
		
	}

}

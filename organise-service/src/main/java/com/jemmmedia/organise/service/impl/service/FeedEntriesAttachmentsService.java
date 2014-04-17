/**
 * 
 */
package com.jemmmedia.organise.service.impl.service;

import java.util.List;

import com.jemmmedia.organise.mybatis.db.mybatis.model.FeedEntriesAttachments;

/**
 * @author harjinder
 *
 */
public interface FeedEntriesAttachmentsService {
	
	public void insertAttachments(FeedEntriesAttachments feedEntriesAttachments);

	List<Long> selectById(
			Long id);

	void deleteById(Long id);

}

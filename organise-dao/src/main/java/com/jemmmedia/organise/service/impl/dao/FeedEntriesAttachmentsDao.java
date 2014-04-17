/**
 * 
 */
package com.jemmmedia.organise.service.impl.dao;

import java.util.List;

import com.jemmmedia.organise.mybatis.db.mybatis.model.FeedEntriesAttachments;

/**
 * @author harjinder
 *
 */
public interface FeedEntriesAttachmentsDao {
 public void insertAttachments(FeedEntriesAttachments feedEntriesAttachments);

 public List<Long> selectById(Long id);

void deleteById(Long id);
}

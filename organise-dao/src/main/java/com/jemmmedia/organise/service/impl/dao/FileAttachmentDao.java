/**
 * 
 */
package com.jemmmedia.organise.service.impl.dao;

import java.util.List;

import com.jemmmedia.organise.mybatis.db.mybatis.model.FileAttachment;

/**
 * @author harjinder
 * 
 */
public interface FileAttachmentDao {

	public long insertFileAttachment(FileAttachment fileAttachment);

	void deleteByPrimaryKey(Long id);

	FileAttachment selectByPrimaryKey(Long id);

	List<FileAttachment> getFeedAttachments(Long id);

}

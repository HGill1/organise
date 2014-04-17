/**
 * 
 */
package com.jemmmedia.organise.service.impl.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.jemmmedia.organise.mybatis.db.mybatis.model.FileAttachment;

/**
 * @author harjinder
 *
 */
public interface FileAttachmentService {
	long insertAttachment(FileAttachment fileAttachment);

	void deleteByPrimaryKey(Long id);

	FileAttachment selectByPrimaryKey(Long id);

	List<FileAttachment> getFeedAttachments(Long id);

	List<FileAttachment> uploadAttachment(MultipartFile file)
			throws IllegalStateException;

	boolean deleteAttachment(Long id);
}

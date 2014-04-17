/**
 * 
 */
package com.jemmmedia.organise.service.impl.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jemmmedia.organise.mybatis.db.mybatis.dao.FileAttachmentMapper;
import com.jemmmedia.organise.mybatis.db.mybatis.model.FileAttachment;
import com.jemmmedia.organise.service.impl.dao.FileAttachmentDAO;

/**
 * @author harjinder
 * 
 */
@Service("fileAttachmentDao")
public class FileAttachmentDaoImpl implements FileAttachmentDAO {

	@Resource
	private FileAttachmentMapper fileAttachmentMapper;
	
	@Override
	public long insertFileAttachment(FileAttachment fileAttachment) {

		fileAttachmentMapper.insertSelective(fileAttachment);

		return fileAttachment.getId();

	}
	@Override
	public void deleteByPrimaryKey(Long id){
		fileAttachmentMapper.deleteByPrimaryKey(id);
	}
	
	@Override
	public FileAttachment selectByPrimaryKey(Long id){
		return fileAttachmentMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public List<FileAttachment> getFeedAttachments(Long id){
		return fileAttachmentMapper.getFeedAttachments(id);
	}

}

/**
 * 
 */
package com.jemmmedia.organise.service.impl.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jemmmedia.organise.mybatis.db.mybatis.model.FileAttachment;
import com.jemmmedia.organise.mybatis.db.mybatis.model.UserDetailedBean;
import com.jemmmedia.organise.service.impl.dao.FileAttachmentDAO;
import com.jemmmedia.organise.service.impl.service.FileAttachmentService;
import com.jemmmedia.organise.service.impl.service.UserService;

/**
 * @author harjinder
 *
 */
@Service("fileAttachmentService")
public class FileAttachmentServiceImpl extends CommonService implements FileAttachmentService {
	
	static final Logger logger = Logger
			.getLogger(FileAttachmentServiceImpl.class);
	
	//private final static String FILEPATH = "/home/journal/data/journal/attachments/";
	//private final static String FILEPATH = "/home/onit/data/onit/attachments/";
	
	
	private Date date = new Date();
	
	@Resource(name="fileAttachmentDao")
	FileAttachmentDAO fileAttachmentDao;
	
	@Resource 
	UserService userService;

	/* (non-Javadoc)
	 * @see com.jemmmedia.organise.service.impl.service.AttachmentService#insertAttachment(com.jemmmedia.organise.service.impl.db.mybatis.model.FileAttachment)
	 */
	@Override
	public long insertAttachment(FileAttachment fileAttachment) {
		return fileAttachmentDao.insertFileAttachment(fileAttachment);
	}
	
	@Override
	public void deleteByPrimaryKey(Long id){
		fileAttachmentDao.deleteByPrimaryKey(id);
	}
	
	@Override
	public FileAttachment selectByPrimaryKey(Long id){
		return fileAttachmentDao.selectByPrimaryKey(id);
	}
	
	@Override
	public List<FileAttachment> getFeedAttachments(Long id){
		return fileAttachmentDao.getFeedAttachments(id);
	}
	
	
	/**
	 * @param file
	 * @return
	 * @throws IllegalStateException
	 */
	@Override
	public List<FileAttachment> uploadAttachment(MultipartFile file)
			throws IllegalStateException {		
		UserDetailedBean userInfo = (UserDetailedBean) userService.getUserDetailFromSpringSecurity();
		
		String fileName;
		String fileAttPath = filesPath + userInfo.getGroupId() +"/attachments/";
		FileAttachment fileAttachment = new FileAttachment(
				file.getOriginalFilename(), Long.valueOf(file.getSize())
						.intValue(), fileAttPath,
				file.getContentType());

		fileAttachment.setCreatedAt(date);
		fileAttachment.setUpdatedAt(date);

		try {
			String orignalFileName = file.getOriginalFilename();
			 int mid= orignalFileName.lastIndexOf(".");
			 String ext="."+orignalFileName.substring(mid+1,orignalFileName.length());
			 fileAttachment.setFileExtension(ext);
			 long id = insertAttachment(fileAttachment);
			
			fileName = fileAttPath + id+ext;
			File dest = new File(fileName);
			file.transferTo(dest);
			
		} catch (IOException e) {
			logger.error("File Attachment failed", e);
			e.printStackTrace();
		}

		List<FileAttachment> uploadedFiles = new ArrayList<FileAttachment>();
		uploadedFiles.add(fileAttachment);
		return uploadedFiles;
	}
	
	/**
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteAttachment(Long id) {
		String fileName = null;
		UserDetailedBean userInfo = (UserDetailedBean) userService.getUserDetailFromSpringSecurity();
		
		FileAttachment fileAttachment =  selectByPrimaryKey(id);
		String fileAttPath = filesPath + userInfo.getGroupId() +"/attachments/";
		
		
		String ext=fileAttachment.getFileExtension();
		
		fileName = fileAttPath + id+ext;
		
		//fileName = filePath + orignalFileName;
		File dest = new File(fileName);
		if(!dest.exists())
			return false;
		
		deleteByPrimaryKey(id);
		dest.delete();
		
		return true;
	}

}

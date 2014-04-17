/**
 * 
 */
package com.jemmmedia.organise.service.impl.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.jemmmedia.organise.mybatis.db.mybatis.dao.FileAttachmentMapper;
import com.jemmmedia.organise.mybatis.db.mybatis.model.FileAttachment;
import com.jemmmedia.organise.service.impl.service.FileAttachmentService;

/**
 * @author harjinder
 * 
 */
@Controller
@RequestMapping("/")
public class FileAttachmentController  implements HandlerExceptionResolver{

	static final Logger logger = Logger
			.getLogger(FileAttachmentController.class);

	
	
	@Resource
	private FileAttachmentMapper fileAttachmentMapper;

	@Resource(name = "fileAttachmentService")
	FileAttachmentService fileAttachmentService;
	

	@RequestMapping(value = "attachFile", method = RequestMethod.POST)
	public @ResponseBody
	List<FileAttachment> upload(@RequestParam("file") MultipartFile file) {
		return fileAttachmentService.uploadAttachment(file);
	}

	
	
	@RequestMapping(value = "deleteFile", method = RequestMethod.POST)
	public @ResponseBody boolean deleteFile(@RequestParam("fileId") Long id){
		
		return fileAttachmentService.deleteAttachment(id);
	}

	

	@Override
	public @ResponseBody ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception exception) {
			
			Map<Object, Object> model = new HashMap();
		
			if(exception instanceof MaxUploadSizeExceededException){
				model.put("errors", exception.getMessage());
			}else{
				model.put("errors", "Unexpected error: " + exception.getMessage());
			}
			return new ModelAndView("attachFile",(Map) model);
			//return null;
		
		
	}
	
	
}

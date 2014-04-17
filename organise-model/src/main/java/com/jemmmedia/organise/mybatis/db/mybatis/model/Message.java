/**
 * 
 */
package com.jemmmedia.organise.mybatis.db.mybatis.model;

import java.io.Serializable;
import java.util.List;

import com.jemmmedia.organise.mybatis.db.mybatis.model.FileAttachment;

/**
 * @author harjinder
 *
 */
public class Message implements Serializable {

	private static final long serialVersionUID = -3167583783476745020L;
	private List<FileAttachment> filename;
	
	public Message() {
		super();
	}

	public Message(List<FileAttachment> filename) {
		super();		
		this.setFilename(filename);
	}

	public List<FileAttachment> getFilename() {
		return filename;
	}

	public void setFilename(List<FileAttachment> filename) {
		this.filename = filename;
	}

	@Override
	public String toString() {
		return "Message [filename=" + filename + "]";
	}

	
	
}

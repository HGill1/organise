/**
 * 
 */
package com.jemmmedia.organise.mybatis.db.mybatis.model;

import java.util.List;

/**
 * @author harjinder
 *
 */
public class AnnouncementExt extends Announcements {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5313188002750164354L;
	
	private List<Long> regionId;

	/**
	 * @return the regionId
	 */
	public List<Long> getRegionId() {
		return regionId;
	}

	/**
	 * @param regionId the regionId to set
	 */
	public void setRegionId(List<Long> regionId) {
		this.regionId = regionId;
	}

}

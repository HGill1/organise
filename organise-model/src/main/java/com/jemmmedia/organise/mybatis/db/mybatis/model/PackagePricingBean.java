/**
 * 
 */
package com.jemmmedia.organise.mybatis.db.mybatis.model;

import com.jemmmedia.organise.mybatis.db.mybatis.model.PackagesPricing;

/**
 * @author harjinder
 *
 */
public class PackagePricingBean extends PackagesPricing {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5007302366075858420L;
	
	private long groupId;

	/**
	 * @return the groupId
	 */
	public long getGroupId() {
		return groupId;
	}

	/**
	 * @param groupId the groupId to set
	 */
	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}
	

}

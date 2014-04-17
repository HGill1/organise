/**
 * 
 */
package com.jemmmedia.organise.mybatis.db.mybatis.model;

import java.util.List;

import com.jemmmedia.organise.mybatis.db.mybatis.model.User;

/**
 * @author harjinder
 *
 */
public class GroupsList {
	
	private List<User> groupList;
	
	public GroupsList(){}

	/**
	 * @return the groupList
	 */
	public List<User> getGroupList() {
		return groupList;
	}

	/**
	 * @param groupList the groupList to set
	 */
	public void setGroupList(List<User> groupList) {
		this.groupList = groupList;
	}

}

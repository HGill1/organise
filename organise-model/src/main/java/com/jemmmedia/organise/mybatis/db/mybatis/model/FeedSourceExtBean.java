/**
 * 
 */
package com.jemmmedia.organise.mybatis.db.mybatis.model;

import java.util.List;

import com.jemmmedia.organise.mybatis.db.mybatis.model.FeedSource;

/**
 * @author harjinder
 *
 */
public class FeedSourceExtBean extends FeedSource {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4473070986220706560L;
	
	private String usersId;
	
	private long checkedUserId;
	
	private long rssUserId;
	
	private String users;
	
	private List<SubtopicsBean> subTopics;
	
	

	/**
	 * @return the checkedUserId
	 */
	public long getCheckedUserId() {
		return checkedUserId;
	}

	/**
	 * @param checkedUserId the checkedUserId to set
	 */
	public void setCheckedUserId(long checkedUserId) {
		this.checkedUserId = checkedUserId;
	}

	/**
	 * @return the users
	 */
	public String getUsers() {
		return users;
	}

	/**
	 * @param users the users to set
	 */
	public void setUsers(String users) {
		this.users = users;
	}

	/**
	 * @return the usersId
	 */
	public String getUsersId() {
		return usersId;
	}

	/**
	 * @param usersId the usersId to set
	 */
	public void setUsersId(String usersId) {
		this.usersId = usersId;
	}

	/**
	 * @return the rssUserId
	 */
	public long getRssUserId() {
		return rssUserId;
	}

	/**
	 * @param rssUserId the rssUserId to set
	 */
	public void setRssUserId(long rssUserId) {
		this.rssUserId = rssUserId;
	}

	/**
	 * @return the subTopics
	 */
	public List<SubtopicsBean> getSubTopics() {
		return subTopics;
	}

	/**
	 * @param subTopics the subTopics to set
	 */
	public void setSubTopics(List<SubtopicsBean> subTopics) {
		this.subTopics = subTopics;
	}
 
}

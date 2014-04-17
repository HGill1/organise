/**
 * 
 */
package com.jemmmedia.organise.mybatis.db.mybatis.model;

import java.util.List;

/**
 * @author harjinder
 *
 */
public class FeedEntryBean extends FeedEntry {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3228610664434136546L;
	
	private String firstName;
	private String lastName;
	private Long replyId;
	private String regionName;
	private String feedSourceName;
	private String statusName;
	private String assignToName;
	private String assignerName;
	private String assignToEmail;
	private List<FileAttachment> fileAttachment;
	private int attachmentCount;
	private int pageStart = 0;
	private String jobTitle;
	private String colour;
	private String profileImage;
	private long loggedInUserId;
	private int numberOfTasks;
	private List<Integer> selectedTopicsList;
	private int orderBy = 0;
	private String taskCreatorEmail;
	private String taskUpdateEmailNotify;
	private String taskTitle; 
	private List<ReplyFeedEntryBean> replyTasks;
	private long deptId;
	private String deptName;
	
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setUsername(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the replyId
	 */
	public Long getReplyId() {
		return replyId;
	}

	/**
	 * @param replyId the replyId to set
	 */
	public void setReplyId(Long replyId) {
		this.replyId = replyId;
	}

	/**
	 * @return the regionName
	 */
	public String getRegionName() {
		return regionName;
	}

	/**
	 * @param regionName the regionName to set
	 */
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	/**
	 * @return the feedSourceName
	 */
	public String getFeedSourceName() {
		return feedSourceName;
	}

	/**
	 * @param feedSourceName the feedSourceName to set
	 */
	public void setFeedSourceName(String feedSourceName) {
		this.feedSourceName = feedSourceName;
	}

	/**
	 * @return the statusName
	 */
	public String getStatusName() {
		return statusName;
	}

	/**
	 * @param statusName the statusName to set
	 */
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	

	/**
	 * @return the assignToName
	 */
	public String getAssignToName() {
		return assignToName;
	}

	/**
	 * @param assignToName the assignToName to set
	 */
	public void setAssignToName(String assignToName) {
		this.assignToName = assignToName;
	}

	/**
	 * @return the fileAttachment
	 */
	public List<FileAttachment> getFileAttachment() {
		return fileAttachment;
	}

	/**
	 * @param fileAttachment the fileAttachment to set
	 */
	public void setFileAttachment(List<FileAttachment> fileAttachment) {
		this.fileAttachment = fileAttachment;
	}

	/**
	 * @return the attachmentCount
	 */
	public int getAttachmentCount() {
		return attachmentCount;
	}

	/**
	 * @param attachmentCount the attachmentCount to set
	 */
	public void setAttachmentCount(int attachmentCount) {
		this.attachmentCount = attachmentCount;
	}

	/**
	 * @return the pageStart
	 */
	public int getPageStart() {
		return pageStart;
	}

	/**
	 * @param pageStart the pageStart to set
	 */
	public void setPageStart(int pageStart) {
		this.pageStart = pageStart;
	}

	/**
	 * @return the jobTitle
	 */
	public String getJobTitle() {
		return jobTitle;
	}

	/**
	 * @param jobTitle the jobTitle to set
	 */
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	/**
	 * @return the colour
	 */
	public String getColour() {
		return colour;
	}

	/**
	 * @param colour the colour to set
	 */
	public void setColour(String colour) {
		this.colour = colour;
	}

	/**
	 * @return the profileImage
	 */
	public String getProfileImage() {
		return profileImage;
	}

	/**
	 * @param profileImage the profileImage to set
	 */
	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	/**
	 * @return the loggedInUserId
	 */
	public long getLoggedInUserId() {
		return loggedInUserId;
	}

	/**
	 * @param loggedInUserId the loggedInUserId to set
	 */
	public void setLoggedInUserId(long loggedInUserId) {
		this.loggedInUserId = loggedInUserId;
	}

	/**
	 * @return the numberOfTasks
	 */
	public int getNumberOfTasks() {
		return numberOfTasks;
	}

	/**
	 * @param numberOfTasks the numberOfTasks to set
	 */
	public void setNumberOfTasks(int numberOfTasks) {
		this.numberOfTasks = numberOfTasks;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the selectedTopicsList
	 */
	public List<Integer> getSelectedTopicsList() {
		return selectedTopicsList;
	}

	/**
	 * @param selectedTopicsList the selectedTopicsList to set
	 */
	public void setSelectedTopicsList(List<Integer> selectedTopicsList) {
		this.selectedTopicsList = selectedTopicsList;
	}

	/**
	 * @return the orderBy
	 */
	public int getOrderBy() {
		return orderBy;
	}

	/**
	 * @param orderBy the orderBy to set
	 */
	public void setOrderBy(int orderBy) {
		this.orderBy = orderBy;
	}

	/**
	 * @return the assignToEmail
	 */
	public String getAssignToEmail() {
		return assignToEmail;
	}

	/**
	 * @param assignToEmail the assignToEmail to set
	 */
	public void setAssignToEmail(String assignToEmail) {
		this.assignToEmail = assignToEmail;
	}

	/**
	 * @return the taskCreatorEmail
	 */
	public String getTaskCreatorEmail() {
		return taskCreatorEmail;
	}

	/**
	 * @param taskCreatorEmail the taskCreatorEmail to set
	 */
	public void setTaskCreatorEmail(String taskCreatorEmail) {
		this.taskCreatorEmail = taskCreatorEmail;
	}

	/**
	 * @return the taskUpdateEmailNotify
	 */
	public String getTaskUpdateEmailNotify() {
		return taskUpdateEmailNotify;
	}

	/**
	 * @param taskUpdateEmailNotify the taskUpdateEmailNotify to set
	 */
	public void setTaskUpdateEmailNotify(String taskUpdateEmailNotify) {
		this.taskUpdateEmailNotify = taskUpdateEmailNotify;
	}

	/**
	 * @return the replyTasks
	 */
	public List<ReplyFeedEntryBean> getReplyTasks() {
		return replyTasks;
	}

	/**
	 * @param replyTasks the replyTasks to set
	 */
	public void setReplyTasks(List<ReplyFeedEntryBean> replyTasks) {
		this.replyTasks = replyTasks;
	}

	/**
	 * @return the assignerName
	 */
	public String getAssignerName() {
		return assignerName;
	}

	/**
	 * @param assignerName the assignerName to set
	 */
	public void setAssignerName(String assignerName) {
		this.assignerName = assignerName;
	}

	/**
	 * @return the taskTitle
	 */
	public String getTaskTitle() {
		return taskTitle;
	}

	/**
	 * @param taskTitle the taskTitle to set
	 */
	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}

	/**
	 * @return the deptId
	 */
	public long getDeptId() {
		return deptId;
	}

	/**
	 * @param deptId the deptId to set
	 */
	public void setDeptId(long deptId) {
		this.deptId = deptId;
	}

	/**
	 * @return the deptName
	 */
	public String getDeptName() {
		return deptName;
	}

	/**
	 * @param deptName the deptName to set
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	
}

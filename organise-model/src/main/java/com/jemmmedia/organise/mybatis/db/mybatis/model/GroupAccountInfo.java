/**
 * 
 */
package com.jemmmedia.organise.mybatis.db.mybatis.model;

import org.joda.time.DateTime;

/**
 * @author hgill
 *
 */
public class GroupAccountInfo {

	private DateTime packageEndDate;
	private String packageName;
	private int tasksCompleted;
	private int tasksopen;
	private int tasksPending;
	private DateTime groupCreationDate;
	private int regionsCount;
	private int usersCount;
	private int topicsCount;
	private int commentsCount;
	private String folderSizeInMB;
	private String spacePercentage;
	public DateTime getPackageEndDate() {
		return packageEndDate;
	}
	public void setPackageEndDate(DateTime packageEndDate) {
		this.packageEndDate = packageEndDate;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public int getTasksCompleted() {
		return tasksCompleted;
	}
	public void setTasksCompleted(int tasksCompleted) {
		this.tasksCompleted = tasksCompleted;
	}
	public int getTasksopen() {
		return tasksopen;
	}
	public void setTasksopen(int tasksopen) {
		this.tasksopen = tasksopen;
	}
	public int getTasksPending() {
		return tasksPending;
	}
	public void setTasksPending(int tasksPending) {
		this.tasksPending = tasksPending;
	}
	public DateTime getGroupCreationDate() {
		return groupCreationDate;
	}
	public void setGroupCreationDate(DateTime groupCreationDate2) {
		this.groupCreationDate = groupCreationDate2;
	}
	public int getRegionsCount() {
		return regionsCount;
	}
	public void setRegionsCount(int regionsCount) {
		this.regionsCount = regionsCount;
	}
	public int getUsersCount() {
		return usersCount;
	}
	public void setUsersCount(int usersCount) {
		this.usersCount = usersCount;
	}
	public int getTopicsCount() {
		return topicsCount;
	}
	public void setTopicsCount(int topicsCount) {
		this.topicsCount = topicsCount;
	}
	public int getCommentsCount() {
		return commentsCount;
	}
	public void setCommentsCount(int commentsCount) {
		this.commentsCount = commentsCount;
	}
	public String getFolderSizeInMB() {
		return folderSizeInMB;
	}
	public void setFolderSizeInMB(String folderSizeInMB) {
		this.folderSizeInMB = folderSizeInMB;
	}
	public String getSpacePercentage() {
		return spacePercentage;
	}
	public void setSpacePercentage(String spacePercentage) {
		this.spacePercentage = spacePercentage;
	}
	
	
	
}

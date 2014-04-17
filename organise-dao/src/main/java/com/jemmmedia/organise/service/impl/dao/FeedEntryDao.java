/**
 * 
 */
package com.jemmmedia.organise.service.impl.dao;

import java.util.Date;
import java.util.List;

import com.jemmmedia.organise.mybatis.db.mybatis.model.Dept;
import com.jemmmedia.organise.mybatis.db.mybatis.model.FeedEntry;
import com.jemmmedia.organise.mybatis.db.mybatis.model.FeedEntryBean;

/**
 * @author harjinder
 *
 */
public interface FeedEntryDao {
	
	public void insertFeedEntry(FeedEntry feedEntry);	
	List<FeedEntryBean> selectChildFeeds(long id);
	List<FeedEntryBean> filterFeeds(FeedEntryBean feedEntryBean);
	List<FeedEntryBean> selectReplyDetail(long id);
	List<FeedEntryBean> selectFeeds(int start, long userId);
	byte checkUpdates(long userId);
	void deleteByPrimaryKey(Long id);
	void updateByPrimaryKey(FeedEntry feedEntry);
	int updateRemaining(Long feedSourceId,Long assignTo);
	int updateFollow(int followerId, long id);
	List<FeedEntryBean> selectFollowingTasks(int start, long followerId,
			long feedSourceId);
	int updateRecentlyViewed(long viewerId, long id);
	List<FeedEntryBean> selectlastViewedDate(long userId);
	List<FeedEntryBean> selectRecentlyViewed(long viewerId, long id);
	List<FeedEntryBean> searchFeeds(int start, String searchText, long userId);
	List<FeedEntryBean> selectTaskCreatedByUsers(int value, long groupId);
	List<FeedEntryBean> selectTaskCompletedByUsers(int value, long groupId);
	List<FeedEntryBean> selectTaskAssignedToUsers(int value, long groupId);
	int todaysTasksPending(long userId);
	int urgentTasks(long userId);
	int oldTasksPending(long userId);
	int todaysDueTasks(long userId);
	List<FeedEntryBean> displayTodaysTasksPending(int start,long userId);
	List<FeedEntryBean> displayOldTasksPending(int start,long userId);
	List<FeedEntryBean> displayUrgentTasks(int start,long userId);
	List<FeedEntryBean> displayTodaysDueTasks(int start,long userId);
	List<String> selectTasksCount(long userId);
	int tasksCompleted(long groupId);
	int tasksOpen(long groupId);
	int tasksPending(long groupId);
	int commentsCount(long groupId);
	List<FeedEntryBean> selectAssignedTask(long assignTo, long id);
	void updateMainStatus(int status, long parentId, Date updatedAt,
			long updatedBy, boolean urgentStatus, long assignTo,
			long assignToDept);
	List<FeedEntryBean> displayAllMyTasks(int start, long userId, int orderBy,
			long feedSourceId, List<Dept> deptIds);
}

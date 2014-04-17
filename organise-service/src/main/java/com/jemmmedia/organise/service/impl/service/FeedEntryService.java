package com.jemmmedia.organise.service.impl.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.jemmmedia.organise.mybatis.db.mybatis.model.Dept;
import com.jemmmedia.organise.mybatis.db.mybatis.model.FeedEntry;
import com.jemmmedia.organise.mybatis.db.mybatis.model.FeedEntryBean;

public interface FeedEntryService {
		public long insertFeedEntry(FeedEntry feedEntry,
				String fileIds, HttpSession session);
		public List<List<FeedEntryBean>> filterFeeds(FeedEntryBean feedEntryBean);
		public List<FeedEntryBean> selectReplyDetail(Long id);
		public List<List<FeedEntryBean>> getFeedList(int start, long userId);
		byte checkUpdates(long userId);
		void updateByPrimaryKey(FeedEntry feedEntry);
		void deleteByPrimaryKey(Long id);
		void insertRssFeeds(FeedEntry feedEntry);
		int updateRemaining(Long feedSourceId, Long assignTo);
		List<List<FeedEntryBean>> getFeedsWithSubFeeds(
				List<FeedEntryBean> feedEntryList);
		int updateFollow(int followerId, long id);
		List<List<FeedEntryBean>> selectFollowingTasks(int start,
				long followerId, long feedSourceId);
		int updateRecentlyViewed(long viewerId, long id);
		List<FeedEntryBean> selectlastViewedDate(long userId);
		List<List<FeedEntryBean>> selectRecentlyViewed(long viewerId, long id);
		List<List<FeedEntryBean>> searchFeeds(int start, String searchText,
				long userId);
		List<FeedEntryBean> selectTaskCreatedByUsers(int value, long groupId);
		List<FeedEntryBean> selectTaskAssignedToUsers(int value, long groupId);
		List<FeedEntryBean> selectTaskCompletedByUsers(int value, long groupId);
		int todaysTasksPending(long userId);
		int urgentTasks(long userId);
		int oldTasksPending(long userId);
		int todaysDueTasks(long userId);
		List<List<FeedEntryBean>> displayTodaysTasksPending(int start,long userId);
		List<List<FeedEntryBean>> displayOldTasksPending(int start,long userId);
		List<List<FeedEntryBean>> displayUrgentTasks(int start,long userId);
		List<List<FeedEntryBean>> displayTodaysDueTasks(int start,long userId);
		List<String> selectTasksCount(long userId);
		int tasksCompleted(long groupId);
		int tasksOpen(long groupId);
		int tasksPending(long groupId);
		int commentsCount(long groupId);
		List<List<FeedEntryBean>> selectAssignedTask(long assignTo, long id);
		List<List<FeedEntryBean>> getAllTasksList(int start, long userId);
		void updateMainStatus(int status, long parentId, boolean urgentStatus,
				long assignTo, long assignToDept);
		List<List<FeedEntryBean>> displayAllMyTasks(int start, long userId,
				int orderBy, long feedSourceId, List<Dept> deptIds);
}

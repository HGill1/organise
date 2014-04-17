package com.jemmmedia.organise.service.impl.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jemmmedia.organise.mybatis.db.mybatis.model.Dept;
import com.jemmmedia.organise.mybatis.db.mybatis.model.FeedEntriesAttachments;
import com.jemmmedia.organise.mybatis.db.mybatis.model.FeedEntry;
import com.jemmmedia.organise.mybatis.db.mybatis.model.FileAttachment;
import com.jemmmedia.organise.mybatis.db.mybatis.model.FeedEntryBean;
import com.jemmmedia.organise.service.impl.dao.FeedEntryDao;
import com.jemmmedia.organise.service.impl.service.FeedEntriesAttachmentsService;
import com.jemmmedia.organise.service.impl.service.FeedEntryService;
import com.jemmmedia.organise.service.impl.service.FileAttachmentService;
import com.jemmmedia.organise.service.impl.service.UserService;
//import org.jtrc.trace.JavaTrace;
@Service("feedEntryService")
public class FeedEntryServiceImpl implements FeedEntryService {

	@Resource(name="FeedEntryDao")
	private FeedEntryDao feedEntryDao;
	
	@Resource(name = "feedEntriesAttachmentsService")
	private FeedEntriesAttachmentsService feedEntriesAttachmentsService;
	
	@Resource(name = "userService")
	private UserService userService;
	
	@Resource(name="fileAttachmentService")
	private FileAttachmentService fileAttachmentService;
	
	
	
	/*@Override
	public void insertFeedEntry(FeedEntry feedEntry) {
		feedEntryDao.addFeedEntry(feedEntry);
		
	}*/
	@Override
	public List<List<FeedEntryBean>> getFeedList(int start, long userId) {
		List<FeedEntryBean> feedEntryList = null;
		try {
			feedEntryList = feedEntryDao.selectFeeds(start, userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return getFeedsWithSubFeeds(feedEntryList);
	}
	
	@Override 
	public List<List<FeedEntryBean>> filterFeeds(FeedEntryBean feedEntryBean) {
			
		long timeStart, timeEnd;
		List<List<FeedEntryBean>> allFeedsList = null;
		
		timeStart = System.currentTimeMillis();
			
		    List<FeedEntryBean> filteredMainList = feedEntryDao.filterFeeds(feedEntryBean);
					
			allFeedsList = getFeedsWithSubFeeds(filteredMainList);
			
			timeEnd = System.currentTimeMillis();
			
		//	JavaTrace.addTrace("Time Filter List:"+(timeEnd - timeStart) );

			return allFeedsList;
	}
	
	@Override
	public List<List<FeedEntryBean>> selectFollowingTasks(int start, long followerId, long feedSourceId) {
		
		List<List<FeedEntryBean>> allFeedsList = null;
		
		 List<FeedEntryBean> selectFollowingTaskList = feedEntryDao.selectFollowingTasks(start, followerId, feedSourceId);
		 allFeedsList = getFeedsWithSubFeeds(selectFollowingTaskList);
		
		return allFeedsList;
	}
	
	@Override
	public List<List<FeedEntryBean>> selectRecentlyViewed(long viewerId, long id) {
		
		List<List<FeedEntryBean>> allFeedsList = null;
		
		 List<FeedEntryBean> selectRecentlyViewedList = feedEntryDao.selectRecentlyViewed(viewerId, id);
		 allFeedsList = getFeedsWithSubFeeds(selectRecentlyViewedList);
		
		return allFeedsList;
	}
	
	@Override
	public List<List<FeedEntryBean>> selectAssignedTask(long assignTo, long id) {
		
		List<List<FeedEntryBean>> allFeedsList = null;
		
		 List<FeedEntryBean> selectRecentlyViewedList = null;
		try {
			selectRecentlyViewedList = feedEntryDao.selectAssignedTask(assignTo, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		 allFeedsList = getFeedsWithSubFeeds(selectRecentlyViewedList);
		
		return allFeedsList;
	}
	

	@Override 
	public List<FeedEntryBean> selectReplyDetail(Long id) {
			return feedEntryDao.selectReplyDetail(id);
	}
	
	@Override 
	public List<List<FeedEntryBean>> searchFeeds(int start, String searchText,long userId) {
		
		long timeStart, timeEnd;
		
		timeStart = System.currentTimeMillis();
		
		List<FeedEntryBean> feedEntryList = feedEntryDao.searchFeeds(start, searchText,userId);
			
		List<List<FeedEntryBean>> getSearchedList = getFeedsWithSubFeeds(feedEntryList);
				
		timeEnd = System.currentTimeMillis();
		
		//JavaTrace.addTrace("Time Search :"+(timeEnd - timeStart) );

		return getSearchedList;
	}

	
	
	/**
	 * @param feedEntryList
	 * @return
	 */
	@Override 
	public List<List<FeedEntryBean>> getFeedsWithSubFeeds(
			List<FeedEntryBean> feedEntryList) {
		List<List<FeedEntryBean>> allFeeds = new ArrayList<>(1);
		List<FileAttachment> attachedFiles = null;
		
		long timeStart, timeEnd;
		
		timeStart = System.currentTimeMillis();
		
		try {
			for(FeedEntryBean feedEntry: feedEntryList){
				
				List<FeedEntryBean> subFeeds = new ArrayList<>();
				
				feedEntry.setReplyId(feedEntry.getId());
				
				if(feedEntry.getAttachmentCount()>0){
					attachedFiles = fileAttachmentService.getFeedAttachments(feedEntry.getId());
				}
				feedEntry.setFileAttachment(attachedFiles);
				
				subFeeds.add(feedEntry);
				List<FeedEntryBean> childfeedEntryList =  new ArrayList<>(1);
				childfeedEntryList = feedEntryDao.selectChildFeeds(feedEntry.getId());
				
				
				subFeeds.addAll(childfeedEntryList);
				
				allFeeds.add(subFeeds);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//pagedListHolder = new PagedListHolder<FeedEntry>(allFeeds);
		
		
		//pagedListHolder.setPageSize(25);
		
	//	pagedListHolder.getPageList();
		
		timeEnd = System.currentTimeMillis();
		
		//JavaTrace.addTrace("Getting all sub feeds time:"+(timeEnd - timeStart) );
		return allFeeds;
	}
	
	
	 
	 
	 
	 /**
		 * @param feedEntry
		 * @param fileIds
		 * @param session
		 * @return
		 */
	@Override
	@Transactional
	public long insertFeedEntry(FeedEntry feedEntry,
				String fileIds, HttpSession session) {
			
		//	User user = (User) session.getAttribute("userBean");
		long timeStart, timeEnd;
		
		timeStart = System.currentTimeMillis();
			
			
			Date date = new Date();
			feedEntry.setCreatedAt(date);
			feedEntry.setUpdatedAt(date);
			feedEntry.setUserId(userService.getUserDetailFromSpringSecurity().getId());
			// here insert feeds
			feedEntryDao.insertFeedEntry(feedEntry);
			long feedEntryId = feedEntry.getId();
			// here insert attachment detail
			insertAttachments(feedEntry, fileIds, date, feedEntryId);
			//updateMainStatus(feedEntry);
			
			//timeEnd = System.currentTimeMillis();
			
			//JavaTrace.addTrace("Insert/Reply Time:"+(timeEnd - timeStart) );
			
			return feedEntryId;
		}
		
	@Override
	 public void updateMainStatus(int status, long parentId, boolean urgentStatus,long assignTo,long assignToDept) {
		
		//long timeStart, timeEnd;
		
		//timeStart = System.currentTimeMillis();
		Date updatedAt = new Date();
		
		try {
			feedEntryDao.updateMainStatus(status, parentId, updatedAt,userService.getUserDetailFromSpringSecurity().getId(), urgentStatus,assignTo, assignToDept);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//timeEnd = System.currentTimeMillis();
		
		//JavaTrace.addTrace("Time to update main status:"+(timeEnd - timeStart) );
		
	}
		/**
		 * @param feedEntry
		 * @param fileIds
		 * @param date
		 * @return
		 */
		private void insertAttachments(FeedEntry feedEntry, String fileIds,
				Date date,long feedEntryId) {
			
			FeedEntriesAttachments feedEntriesAttachments = new FeedEntriesAttachments();
			
			if(!fileIds.equals("")){
				String[] attachmentIds =   fileIds.split(",");
				
				for(String id : attachmentIds){
					feedEntriesAttachments.setAttachmentId(Long.parseLong(id));
					feedEntriesAttachments.setFeedEntryId(feedEntryId);	
					feedEntriesAttachments.setCreatedAt(date);
					feedEntriesAttachments.setUpdatedAt(date);
					feedEntriesAttachmentsService.insertAttachments(feedEntriesAttachments);
				}
			}
			
		}
	
		@Override
		public byte checkUpdates(long userId){
			
		//	long timeStart, timeEnd;
			
		//	timeStart = System.currentTimeMillis();
			
			byte valueUpdate =  feedEntryDao.checkUpdates(userId);
			
		//	timeEnd = System.currentTimeMillis();
			
		//	JavaTrace.addTrace("Time check updates:"+(timeEnd - timeStart) );
			
			return valueUpdate;
			
			
		}
		
		@Override
		public int updateRemaining(Long feedSourceId, Long assignTo){
			
			long timeStart, timeEnd;
			
			timeStart = System.currentTimeMillis();
			
			int remainingCount = feedEntryDao.updateRemaining(feedSourceId, assignTo);
			
			timeEnd = System.currentTimeMillis();
			
			//JavaTrace.addTrace("Time check remaining count:"+(timeEnd - timeStart) );
			
			return remainingCount ;
			
			
			
		}
		
		@Override
		public void deleteByPrimaryKey(Long id){
			feedEntryDao.deleteByPrimaryKey(id);
		}
		
		@Override
		public void updateByPrimaryKey(FeedEntry feedEntry){
			feedEntryDao.updateByPrimaryKey(feedEntry);
		}
		
		@Override
		public void insertRssFeeds(FeedEntry feedEntry){
			long timeStart, timeEnd;
			
			timeStart = System.currentTimeMillis();
			
			feedEntryDao.insertFeedEntry(feedEntry);
			
			timeEnd = System.currentTimeMillis();
			
			//JavaTrace.addTrace("Time insert RSS feeds:"+(timeEnd - timeStart) );
		}
		
		@Override
		public int updateFollow(int followerId, long id){
			return feedEntryDao.updateFollow(followerId, id);
		}
		
		@Override
		public int updateRecentlyViewed(long viewerId, long id){
			int countUpdate = 0;
			try {
				countUpdate =  feedEntryDao.updateRecentlyViewed(viewerId,id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return countUpdate;
		}
		
		@Override
		public List<FeedEntryBean> selectlastViewedDate(long userId){
			List<FeedEntryBean> recentlyViewedList = null;
			try {
				recentlyViewedList = feedEntryDao.selectlastViewedDate(userId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return recentlyViewedList;
			
		}
		
		@Override
		public List<FeedEntryBean> selectTaskCreatedByUsers(int value, long groupId){
			List<FeedEntryBean> taskList = null;
			try {
				taskList = feedEntryDao.selectTaskCreatedByUsers(value, groupId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return taskList;
			
		}
		
		@Override
		public List<FeedEntryBean> selectTaskCompletedByUsers(int value, long groupId){
			List<FeedEntryBean> taskList = null;
			try {
				taskList = feedEntryDao.selectTaskCompletedByUsers(value, groupId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return taskList;
			
		}
		
		@Override
		public List<FeedEntryBean> selectTaskAssignedToUsers(int value, long groupId){
			List<FeedEntryBean> taskList = null;
			try {
				taskList = feedEntryDao.selectTaskAssignedToUsers(value, groupId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return taskList;
			
		}
		
		@Override
		public int todaysTasksPending(long userId){
			int count = 0;
			try {
				count =  feedEntryDao.todaysTasksPending(userId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return count;
		}
		
		@Override
		public int urgentTasks(long userId){
			int count = 0;
			try {
				count =  feedEntryDao.urgentTasks(userId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return count;
		}
		
		@Override
		public int oldTasksPending(long userId){
			int count = 0;
			try {
				count =  feedEntryDao.oldTasksPending(userId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return count;
		}
		
		@Override
		public int todaysDueTasks(long userId){
			int count = 0;
			try {
				count =  feedEntryDao.todaysDueTasks(userId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return count;
		}
		
		@Override
		public int tasksCompleted(long groupId){
			int count = 0;
			try {
				count =  feedEntryDao.tasksCompleted(groupId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return count;
		}
		
		@Override
		public int tasksOpen(long groupId){
			int count = 0;
			try {
				count =  feedEntryDao.tasksOpen(groupId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return count;
		}
		
		@Override
		public int tasksPending(long groupId){
			int count = 0;
			try {
				count =  feedEntryDao.tasksPending(groupId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return count;
		}
		
		@Override
		public int commentsCount(long groupId){
			int count = 0;
			try {
				count =  feedEntryDao.commentsCount(groupId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return count;
		}
		
		@Override
		public List<List<FeedEntryBean>> displayTodaysTasksPending(int start,long userId){
			
			List<List<FeedEntryBean>> allFeedsList = null;
			
			List<FeedEntryBean> list = null;
			try {
				list = feedEntryDao.displayTodaysTasksPending(start,userId);
				allFeedsList = getFeedsWithSubFeeds(list);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return allFeedsList;
			
		}
		
		@Override
		public List<List<FeedEntryBean>> displayOldTasksPending(int start,long userId){
			
			List<List<FeedEntryBean>> allFeedsList = null;
			
			List<FeedEntryBean> list = null;
			try {
				list = feedEntryDao.displayOldTasksPending(start,userId);
				allFeedsList = getFeedsWithSubFeeds(list);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return allFeedsList;
			
		}
		
		@Override
		public List<List<FeedEntryBean>> displayUrgentTasks(int start,long userId){
			
			List<List<FeedEntryBean>> allFeedsList = null;
			
			List<FeedEntryBean> list = null;
			try {
				list = feedEntryDao.displayUrgentTasks(start,userId);
				allFeedsList = getFeedsWithSubFeeds(list);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return allFeedsList;
			
		}
		
		@Override
		public List<List<FeedEntryBean>> displayTodaysDueTasks(int start,long userId){
			
			List<List<FeedEntryBean>> allFeedsList = null;
			
			List<FeedEntryBean> list = null;
			try {
				list = feedEntryDao.displayTodaysDueTasks(start,userId);
				allFeedsList = getFeedsWithSubFeeds(list);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return allFeedsList;
			
		}
		
		@Override
		public List<List<FeedEntryBean>> displayAllMyTasks(int start,long userId, int orderBy,long feedSourceId,List<Dept> deptIds){
			
			List<List<FeedEntryBean>> allFeedsList = null;
			
			List<FeedEntryBean> list = null;
			try {
				list = feedEntryDao.displayAllMyTasks(start, userId, orderBy,feedSourceId,deptIds);
				allFeedsList = getFeedsWithSubFeeds(list);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return allFeedsList;
			
		}
		
		@Override
		public List<String> selectTasksCount(long userId){
			
			return feedEntryDao.selectTasksCount(userId);
			
		}
		
		/**
		 * @param start
		 * @param userLoggedIn
		 * @return 
		 */
		@Override
		public List<List<FeedEntryBean>> getAllTasksList(int start, long userId) {
			return getFeedList(start, userId);
		}
		
}

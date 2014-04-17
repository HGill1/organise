/**
 * 
 */
package com.jemmmedia.organise.service.impl.dao.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jemmmedia.organise.mybatis.db.mybatis.dao.FeedEntryMapper;
import com.jemmmedia.organise.mybatis.db.mybatis.model.Dept;
import com.jemmmedia.organise.mybatis.db.mybatis.model.FeedEntry;
import com.jemmmedia.organise.mybatis.db.mybatis.model.FeedEntryBean;
import com.jemmmedia.organise.service.impl.dao.FeedEntryDao;

/**
 * @author harjinder
 *
 */
@Service("FeedEntryDao")
public class FeedEntryDaoImpl implements FeedEntryDao {

	@Resource
	private FeedEntryMapper feedEntryMapper;
	
	
	/* (non-Javadoc)
	 * @see com.jemmmedia.organise.service.impl.dao.FeedEntryDao#FeedEntryList()
	 */
	
	
	@Override
	public List<FeedEntryBean> selectFeeds(int start,long userId) {
		return feedEntryMapper.selectFeeds(start, userId);
	}
	
	@Override
	public List<FeedEntryBean> filterFeeds(FeedEntryBean feedEntryBean) {
		return feedEntryMapper.filterFeeds(feedEntryBean);
	}
	
	@Override
	public List<FeedEntryBean> selectFollowingTasks(int start, long followerId, long feedSourceId) {
		return feedEntryMapper.selectFollowingTasks(start, followerId, feedSourceId);
	}
	
	@Override
	public List<FeedEntryBean> selectRecentlyViewed(long viewerId, long id) {
		return feedEntryMapper.selectRecentlyViewed(viewerId, id);
	}
	
	@Override
	public List<FeedEntryBean> selectAssignedTask(long assignTo, long id) {
		return feedEntryMapper.selectAssignedTask(assignTo, id);
	}
	
	@Override
	@Transactional
	public void insertFeedEntry(FeedEntry feedEntry) {
		feedEntryMapper.insertSelective(feedEntry);
		
	}

	@Override
	public List<FeedEntryBean> selectChildFeeds(long id) {
		return feedEntryMapper.selectChildFeeds(id);
	}
	
	@Override
	public List<FeedEntryBean> selectReplyDetail(long id) {
		return feedEntryMapper.selectReplyDetail(id);
	}

	
	@Override
	public void updateMainStatus(int status, long parentId, Date updatedAt, long updatedBy, boolean urgentStatus,long assignTo, long assignToDept) {
		feedEntryMapper.updateMainStatus(status, parentId, updatedAt,updatedBy,urgentStatus,assignTo, assignToDept);
		
	}
	@Override
	public byte checkUpdates(long userId){
		return feedEntryMapper.checkUpdates(userId);
	}
	
	@Override
	public int updateRemaining(Long feedSourceId, Long assignTo){
		return feedEntryMapper.updateRemaining(feedSourceId, assignTo);
	}
	
	@Override
	public void deleteByPrimaryKey(Long id){
		feedEntryMapper.deleteByPrimaryKey(id);
	}
	
	@Override
	public void updateByPrimaryKey(FeedEntry feedEntry){
		feedEntryMapper.updateByPrimaryKey(feedEntry);
	}
	
	@Override
	public List<FeedEntryBean> searchFeeds(int start, String searchText,long userId){
		return feedEntryMapper.searchFeeds(start, searchText,userId);
	}
	
	@Override
	public int updateFollow(int followerId, long id){
		return feedEntryMapper.updateFollow(followerId, id);
	}
	
	@Override
	public int updateRecentlyViewed(long viewerId, long id){
		
		return feedEntryMapper.updateRecentlyViewed(viewerId,id);
		
	}
	
	@Override
	public List<FeedEntryBean> selectlastViewedDate(long userId){
		
		return feedEntryMapper.selectlastViewedDate(userId);
		
	}
	
	@Override
	public List<FeedEntryBean> selectTaskCreatedByUsers(int value, long groupId){
		
		return feedEntryMapper.selectTaskCreatedByUsers(value,groupId);
		
	}
	
	@Override
	public List<FeedEntryBean> selectTaskCompletedByUsers(int value, long groupId){
		
		return feedEntryMapper.selectTaskCompletedByUsers(value,groupId);
		
	}
	
	@Override
	public List<FeedEntryBean> selectTaskAssignedToUsers(int value, long groupId){
		
		return feedEntryMapper.selectTaskAssignedToUsers(value,groupId);
		
	}
	
	@Override
	public int todaysTasksPending(long userId){
		
			return feedEntryMapper.todaysTasksPending(userId);
	}
	
	@Override
	public int urgentTasks(long userId){
		
			return feedEntryMapper.urgentTasks(userId);
	}
	
	@Override
	public int oldTasksPending(long userId){
		
			return feedEntryMapper.oldTasksPending(userId);
	}
	
	@Override
	public int todaysDueTasks(long userId){
		
			return feedEntryMapper.todaysDueTasks(userId);
	}
	
	
	@Override
	public int tasksCompleted(long groupId){
		
			return feedEntryMapper.tasksCompleted(groupId);
	}
	
	@Override
	public int tasksOpen(long groupId){
		
		return feedEntryMapper.tasksOpen(groupId);
		
	}
	
	@Override
	public int tasksPending(long groupId){
		
		return feedEntryMapper.tasksPending(groupId);
		
	}
	
	@Override
	public int commentsCount(long groupId){
		
		return feedEntryMapper.commentsCount(groupId);
		
	}
	
	@Override
	public List<FeedEntryBean> displayTodaysTasksPending(int start,long userId){
		
		return feedEntryMapper.displayTodaysTasksPending(start,userId);
		
	}
	
	@Override
	public List<FeedEntryBean> displayOldTasksPending(int start,long userId){
		
		return feedEntryMapper.displayOldTasksPending(start,userId);
		
	}
	
	@Override
	public List<FeedEntryBean> displayUrgentTasks(int start,long userId){
		
		return feedEntryMapper.displayUrgentTasks(start,userId);
		
	}
	
	@Override
	public List<FeedEntryBean> displayTodaysDueTasks(int start,long userId){
		
		return feedEntryMapper.displayTodaysDueTasks(start,userId);
		
	}
	
	@Override
	public List<FeedEntryBean> displayAllMyTasks(int start,long userId, int orderBy, long feedSourceId,List<Dept> deptIds){
		
		return feedEntryMapper.displayAllMyTasks(start, userId, orderBy, feedSourceId, deptIds);
		
	}
	
	@Override
	public List<String> selectTasksCount(long userId){
		
		return feedEntryMapper.selectTasksCount(userId);
		
	}
	
}

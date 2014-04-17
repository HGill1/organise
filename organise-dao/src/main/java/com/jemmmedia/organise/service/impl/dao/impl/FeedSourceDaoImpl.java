/**
 * 
 */
package com.jemmmedia.organise.service.impl.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jemmmedia.organise.mybatis.db.mybatis.dao.FeedSourceMapper;
import com.jemmmedia.organise.mybatis.db.mybatis.dao.FeedSources_UsersMapper;
import com.jemmmedia.organise.mybatis.db.mybatis.model.FeedSource;
import com.jemmmedia.organise.mybatis.db.mybatis.model.FeedSources_Users;
import com.jemmmedia.organise.mybatis.db.mybatis.model.FeedSourceExtBean;
import com.jemmmedia.organise.service.impl.dao.FeedSourceDao;

/**
 * @author harjinder
 *
 */
@Service("feedSourceDao")
public class FeedSourceDaoImpl implements FeedSourceDao {

	/* (non-Javadoc)
	 * @see com.jemmmedia.organise.service.impl.dao.FeedSourceDao#selectAllSources()
	 */
	
	@Resource
	FeedSourceMapper feedSourceMapper;
	
	@Resource
	FeedSources_UsersMapper feedSources_UsersMapper;
	
	@Override
	public Map<Integer, FeedSource> getAllSources() {
		return feedSourceMapper.getAllSources();
	}
	
	@Override
	public List<FeedSourceExtBean> selectFeedSources(){
		
		return feedSourceMapper.selectFeedSources();
		
	}
	
	@Override
	public List<FeedSourceExtBean> selectAllSources(long groupId){
		
		return feedSourceMapper.selectAllSources(groupId);
		
	}
	
	@Override
	public List<FeedSource> selectFollowCat(long followerId){
		return feedSourceMapper.selectFollowCat(followerId);
	}
	
	@Override
	public int insertSelective(FeedSource feedSource){
		return feedSourceMapper.insertSelective(feedSource);
	}
	
	@Override
	public int updateByPrimaryKeySelective(FeedSource feedSource){
		return feedSourceMapper.updateByPrimaryKeySelective(feedSource);
	}
	
	@Override
	public void insert(FeedSources_Users feedSources_Users){
		feedSources_UsersMapper.insert(feedSources_Users);
	}
	
	@Override
	public void deleteSourceUserEntry(long feedSourceId){
		feedSources_UsersMapper.deleteSourceUserEntry(feedSourceId);
	}
	
	@Override
	public FeedSourceExtBean selectFeedSourceForEdit(long id){
		 
		return feedSourceMapper.selectFeedSourceForEdit(id);
		
	}
	
	@Override
	public List<FeedSourceExtBean> selectUsersFeedSources(long userId){
		 
		return feedSourceMapper.selectUsersFeedSources(userId);
		
	}
	
	@Override
	public void deleteByPrimaryKey(long id){
		 
		 feedSourceMapper.deleteByPrimaryKey(id);
		
	}
	@Override
	public void deleteSubSource(long id){
		 
		 feedSourceMapper.deleteSubSource(id);
		
	}
	@Override
	public List<FeedSourceExtBean> selectSubTopics(Long parentId) {
		return feedSourceMapper.selectSubTopics(parentId);
	}
	
	@Override
	public int topicsCount(long groupId){
		
		return feedSourceMapper.topicsCount(groupId);
		
	}

}

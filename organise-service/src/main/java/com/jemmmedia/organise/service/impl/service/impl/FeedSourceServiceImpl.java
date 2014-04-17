/**
 * 
 */
package com.jemmmedia.organise.service.impl.service.impl;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jemmmedia.organise.mybatis.db.mybatis.model.FeedEntry;
import com.jemmmedia.organise.mybatis.db.mybatis.model.FeedSource;
import com.jemmmedia.organise.mybatis.db.mybatis.model.FeedSources_Users;
import com.jemmmedia.organise.mybatis.db.mybatis.model.FeedSourceExtBean;
import com.jemmmedia.organise.mybatis.db.mybatis.model.UserDetailedBean;
import com.jemmmedia.organise.service.impl.dao.FeedSourceDao;
import com.jemmmedia.organise.service.impl.service.FeedEntryService;
import com.jemmmedia.organise.service.impl.service.FeedEntryWithChildService;
import com.jemmmedia.organise.service.impl.service.FeedSourceService;
import com.jemmmedia.organise.service.impl.service.FeedsUsersService;
import com.jemmmedia.organise.service.impl.service.UserService;
import com.jemmmedia.organise.service.impl.utility.RSSFeedsUpdateDate;
import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

/**
 * @author harjinder
 *
 */
@Service("feedSourceService") 
public class FeedSourceServiceImpl implements FeedSourceService {
	
	@Resource(name="feedSourceDao")
	FeedSourceDao feedSourceDao;
	
	@Resource(name = "feedEntryService")
	FeedEntryService feedEntryService;
	
	@Resource(name = "rSSFeedsUpdateDate")
	RSSFeedsUpdateDate rSSFeedsUpdateDate;
	
	@Resource(name = "userService")
	UserService userService;
	
	@Resource(name = "feedsUsersService")
	FeedsUsersService feedsUsersService;
	
	@Resource(name = "feedEntryWithChildService")
	FeedEntryWithChildService feedEntryWithChildService;
	
	/* (non-Javadoc)
	 * @see com.jemmmedia.organise.service.impl.service.FeedSourceService#SelectAllSources()
	 */	
	@Override
	public Map<Integer, FeedSource> getAllSources() {
		
		Map<Integer, FeedSource> allSources = feedSourceDao.getAllSources();
		
		return allSources;
	}
	
	@Override
	public List<FeedSourceExtBean> selectFeedSources(){
		List<FeedSourceExtBean> feedSourcesList = null;
		 try {
			 feedSourcesList = feedSourceDao.selectFeedSources();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return feedSourcesList;
	}
	
	/**
	 * @param feedSource
	 * @param feedEntry
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	@Override
	public void readRssFeeds(FeedSourceExtBean feedSource, FeedEntry feedEntry)
			throws MalformedURLException, IOException {
		int count = 0;
		
		URL url =  new URL(feedSource.getUrl());
		//URL url =  new URL("https://ced4177793bea85a08a1d094ee49a903:bla@jemm.highrisehq.com/recordings.atom");
		//URL url =  new URL("http://rss.echosign.com/rss?v=PR1:dT05MDg0MjMm:-CmUzUqRUZpSJXbECOydQcqA_jVBoI13gNUz2kf_4WE&feedType=rss_2.0");
		
		
		
		HttpURLConnection httpcon = (HttpURLConnection)url.openConnection();
		
		if(feedSource.getToken() != null){
		
				byte[] strToken = (feedSource.getToken()+":").getBytes();
				
				String encoding = new sun.misc.BASE64Encoder().encode(strToken);
				
				httpcon.setRequestProperty  ("Authorization", "Basic " + encoding);
		}
		try {
		SyndFeedInput input = new SyndFeedInput();		
		
			SyndFeed feed = input.build(new XmlReader(httpcon));
			
			for(Object entries: feed.getEntries()){
				
				SyndEntry entry = (SyndEntry) entries;
				
				if(checkIfUpdatesAvailable(feedSource.getId(), entry.getPublishedDate())){
							
					Date date = new Date();
					
							
							feedEntry.setFeedSourceId(feedSource.getId());
							feedEntry.setParentFeed(true);
							feedEntry.setName(entry.getTitle());
							feedEntry.setPublishedAt(entry.getPublishedDate());
							feedEntry.setUrl(entry.getLink());
							feedEntry.setAuthor(entry.getAuthor());
							feedEntry.setGuid(entry.getUri());
							feedEntry.setCreatedAt(date);
							feedEntry.setUpdatedAt(entry.getPublishedDate());
							feedEntry.setUserId(feedSource.getRssUserId());
							feedEntry.setFeedType(feedSource.getSourceType());
							
							
							if(feedSource.getToken() != null && !feedSource.getToken().isEmpty()){
								SyndContentImpl entry2 =  (SyndContentImpl) entry.getContents().get(0);
								feedEntry.setSummary(entry2.getValue());
								
							}else{
								feedEntry.setSummary(entry.getDescription().getValue());
							}
							
							if(count == 0)				
								updateRssFeedHashMap(feedSource.getId(),entry.getPublishedDate());
							
							feedEntryService.insertRssFeeds(feedEntry);
							count++;
				}else{
					break;
				}
					
			}
			
			
		} catch (IllegalArgumentException | FeedException | IOException  e) {
			e.printStackTrace();
		}catch (Exception ex){
			ex.printStackTrace();
		}
	}
	
	/*public void createRSSFeed(){
		
		SyndFeed feed = new SyndFeedImpl();
		
		feed.setFeedType("rss_1.0");
		feed.setTitle("Announcements");
		feed.setLink("http://journal.optimise.net");
		
	}*/
	
	private boolean checkIfUpdatesAvailable(Long id, Date publishedDate) {
		if(rSSFeedsUpdateDate.RSSFeedDate.containsKey(id)){
			if(publishedDate.getTime() > rSSFeedsUpdateDate.RSSFeedDate.get(id).getTime()){
				return true;
			}else{
				return false;
			}				
		}
		return true;
		
	}


	private void updateRssFeedHashMap(Long id, Date publishedDate) {
		
		if(rSSFeedsUpdateDate.RSSFeedDate.containsKey(id))
			rSSFeedsUpdateDate.RSSFeedDate.remove(id);
		
		rSSFeedsUpdateDate.RSSFeedDate.put(id, publishedDate);
	}
	
	@Override
	public List<FeedSource> selectFollowCat(long followerId){
		return feedSourceDao.selectFollowCat(followerId);
	}
	
	@Override
	public List<FeedSourceExtBean> selectAllSources(long groupId){
		return feedSourceDao.selectAllSources(groupId);
	}
	
	@Override
	public int insertSelective(FeedSource feedSource){
		int sourceId = 0;
		try {
			sourceId = feedSourceDao.insertSelective(feedSource);
			if(feedSource.getSourceType() != 0){
				UserDetailedBean user = new UserDetailedBean();
				String sourceName = feedSource.getName();
				user.setUsername(sourceName);
				user.setEmail(sourceName);
				user.setUserType(feedSource.getId());
				user.setCryptedPassword(sourceName);
				user.setPasswordSalt(sourceName);
				user.setPersistenceToken(sourceName);
				user.setRegionId((long)1);
				userService.insertSelective(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sourceId;
	}
	
	@Override
	public int updateByPrimaryKeySelective(FeedSource feedSource){
		int sourceId = 0;
		try {
			sourceId = feedSourceDao.updateByPrimaryKeySelective(feedSource);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sourceId;
	}
	
	@Override
	public FeedSourceExtBean selectFeedSourceForEdit(long id){
		FeedSourceExtBean feedSourceExtBean = null;
		try {
			feedSourceExtBean = feedSourceDao.selectFeedSourceForEdit(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return feedSourceExtBean;
	}
	
	@Override
	public void insert(ArrayList<Long> checkedUserId, long feedSourceId){
		
		FeedSources_Users feedSources_Users =  new FeedSources_Users();
		
		Date date = new Date();
		
		for (Long userId : checkedUserId) {
			
			feedSources_Users.setFeedSourceId(feedSourceId);
			feedSources_Users.setUserId(userId);
			feedSources_Users.setCreatedAt(date);
			feedSources_Users.setUpdatedAt(date);
			
			try {
				feedSourceDao.insert(feedSources_Users);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
	@Override
	public void insertTopicsForUser(List<Long> topicsId, long userId){
		
		FeedSources_Users feedSources_Users =  new FeedSources_Users();
		
		Date date = new Date();
		
		for (Long topicId : topicsId) {
			
			feedSources_Users.setFeedSourceId(topicId);
			feedSources_Users.setUserId(userId);
			feedSources_Users.setCreatedAt(date);
			feedSources_Users.setUpdatedAt(date);
			
			try {
				feedSourceDao.insert(feedSources_Users);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
	@Override
	public List<FeedSourceExtBean> selectUsersFeedSources(long userId){
		 List<FeedSourceExtBean> listFeedSource = null;
		try {
			listFeedSource = feedSourceDao.selectUsersFeedSources(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listFeedSource;
	}
	
	@Override
	public void deleteSourceUserEntry(long feedSourceId){
		
			try {
				feedSourceDao.deleteSourceUserEntry(feedSourceId);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	@Override
	public void deleteSubSource(long parentId){
		
			try {
				feedSourceDao.deleteSubSource(parentId);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	@Override
	@Transactional
	public void deleteSource(long id){
		try {
			feedsUsersService.deleteSourcesUsers(id);
			//feedEntryWithChildService.deleteFeedEntryChild(id);
			//feedEntryService.deleteByPrimaryKey(id);
			deleteSubSource(id);
			feedSourceDao.deleteByPrimaryKey(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<FeedSourceExtBean> selectSubTopics(Long parentId) {
		List<FeedSourceExtBean> listSubFeedSource = null;
		try {
			listSubFeedSource = feedSourceDao.selectSubTopics(parentId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listSubFeedSource;
	}
	
	@Override
	public int topicsCount(long groupId){
		int count = 0;
		try {
			count =  feedSourceDao.topicsCount(groupId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}


}

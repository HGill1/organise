/**
 * 
 */
package com.jemmmedia.organise.service.impl.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jemmmedia.organise.mybatis.db.mybatis.model.FeedSourceExtBean;
import com.jemmmedia.organise.mybatis.db.mybatis.model.FeedsUsersKey;
import com.jemmmedia.organise.service.impl.service.FeedSourceService;
import com.jemmmedia.organise.service.impl.service.FeedsUsersService;

/**
 * @author harjinder
 *
 */
@Controller
@RequestMapping("/")
public class FeedsUsersController {

	@Resource
	FeedsUsersService feedsUsersService;
	
	@Resource(name = "feedSourceService")
	FeedSourceService feedSourceService;
	
	@RequestMapping("showHideTopic")
	private @ResponseBody int showHideTopic(@ModelAttribute FeedsUsersKey feedsUsersKey, 
											 @RequestParam("action") String action ,
											 @RequestParam("userId") int userId,
											 HttpSession session){
		int count;
		if(action.equals("show") )
			count = feedsUsersService.insertSelective(feedsUsersKey);
		else
			count =  feedsUsersService.deleteByPrimaryKey(feedsUsersKey);
		
		List<Long> topicsList = getSelectedTopicsList(userId);
		
		setSessionForSelectedTopics(session, topicsList);
		
		return count;
	}
	
	@RequestMapping("selectEnabledFeedSource")
	@ResponseBody private List<Long> selectFeedSourceIds(@RequestParam("userId") int userId, HttpSession session){
		
		List<Long> topicsList = getSelectedTopicsList(userId);
		
		setSessionForSelectedTopics(session, topicsList);
		
		return topicsList;
	}

	/**
	 * @param userId
	 * @return
	 */
	private List<Long> getSelectedTopicsList(int userId) {
		List<Long> topicsList = feedsUsersService.selectFeedSourceIds(userId);
		return topicsList;
	}

	/**
	 * @param session
	 * @param topicsList
	 */
	private void setSessionForSelectedTopics(HttpSession session,
			List<Long> topicsList) {
		session.setAttribute("topicsSelected", topicsList);
	}

	
	
	@RequestMapping("getAllSources")
	@ResponseBody private List<FeedSourceExtBean> getAllSources(@RequestParam("userId") long userId){
		return feedSourceService.selectUsersFeedSources(userId);	
	}
	
}

/**
 * 
 */
package com.jemmmedia.organise.service.impl.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jemmmedia.organise.mybatis.db.mybatis.model.FeedEntry;
import com.jemmmedia.organise.mybatis.db.mybatis.model.FeedSource;
import com.jemmmedia.organise.mybatis.db.mybatis.model.FeedSourceExtBean;
import com.jemmmedia.organise.service.impl.service.FeedSourceService;

/**
 * @author harjinder
 *
 */
@Controller
@RequestMapping("/")
public class FeedSourceController {

	@Resource(name = "feedSourceService")
	FeedSourceService feedSourceService;
	
	
	@RequestMapping("reedRssFeeds")
	@ResponseBody public String readRssFeeds() {
		
		List<FeedSourceExtBean> rssFeeds =  feedSourceService.selectFeedSources();
		
		if (!rssFeeds.isEmpty()) {

			FeedEntry feedEntry = new FeedEntry();

			for (FeedSourceExtBean feedSource : rssFeeds) {

				try {
					readAndInsertFeeds(feedSource, feedEntry);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return "done";
		
	}


	private void readAndInsertFeeds(FeedSourceExtBean feedSource, FeedEntry feedEntry) throws IOException {
		
		feedSourceService.readRssFeeds(feedSource, feedEntry);
		
	}
	
	
	
	@RequestMapping(value = "reloadFollowing", method = RequestMethod.POST)
	@ResponseBody public List<FeedSource> selectFollowCat(@RequestParam("followerId") int followerId){
			return  feedSourceService.selectFollowCat(followerId);
	}
	
}

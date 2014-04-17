/**
 * 
 */
package com.jemmmedia.organise.service.impl.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.jemmmedia.organise.mybatis.db.mybatis.model.Feeds;
import com.jemmmedia.organise.service.impl.service.FeedsService;

/**
 * @author harjinder
 *
 */
@Controller
@RequestMapping("/")
public class FeedsController {
	
	@Resource(name="feedsService")
	FeedsService feedsService;
	
	
	@RequestMapping("addFeeds")
	public ModelAndView addFeeds(Model model, @ModelAttribute Feeds feeds){
		
		model.addAttribute("allFeedTypes", feedsService.getAllFeedTypes());
		return new ModelAndView("addFeeds","feeds", feeds);
	}
	
	@RequestMapping(value = "insertFeed" , method = RequestMethod.POST)
	public ModelAndView insertFeeds(@ModelAttribute Feeds feeds){
		
		feedsService.insertSelective(feeds);		
		return new ModelAndView("addFeeds","feeds", feeds);
	}

}

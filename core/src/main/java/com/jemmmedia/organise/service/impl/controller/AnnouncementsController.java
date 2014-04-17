/**
 * 
 */
package com.jemmmedia.organise.service.impl.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jemmmedia.organise.mybatis.db.mybatis.model.AnnouncementExt;
import com.jemmmedia.organise.mybatis.db.mybatis.model.Announcements;
import com.jemmmedia.organise.mybatis.db.mybatis.model.RegionsAnnouncementsKey;
import com.jemmmedia.organise.service.impl.service.AnnouncementService;
import com.jemmmedia.organise.service.impl.service.UserService;

/**
 * @author harjinder
 *
 */
@RequestMapping("/")
@Controller
public class AnnouncementsController extends CommonController {
	
	@Resource(name = "userService")
	UserService userService;
	
	@Resource(name = "announcementService")
	private AnnouncementService announcementService;

	@RequestMapping("announcements")
	public ModelAndView displyAnnouncementsForm(Model model){
		
		Announcements announcement = new Announcements();
		
		getLoginUserDetail(model);
		return new ModelAndView("announcements","announcement",announcement);
		
	}
	
	@Transactional
	@RequestMapping("createAnnouncement")
	public String createAnnouncement(@ModelAttribute ("announcement") AnnouncementExt announcement, Model model){
		
		getLoginUserDetail(model);
		
		announcementService.insertSelective(announcement);
		
		if(!(announcement.getRegionId().get(0) == 0)){
			
			RegionsAnnouncementsKey key = new RegionsAnnouncementsKey();
			
			for(long regionId: announcement.getRegionId()){
				
				key.setRegionId(regionId);
				key.setAnnouncementId(announcement.getId());
				
				announcementService.insertOrUpdateSelective(key);
			}
					
			
		}
		model.addAttribute("announcementSuccessMsg", "Announcement Added successfully");

		return "redirect:preferences?tabSelected=9";
		
	}
	
}

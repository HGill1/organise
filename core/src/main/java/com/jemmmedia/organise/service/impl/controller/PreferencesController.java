/**
 * 
 */
package com.jemmmedia.organise.service.impl.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jemmmedia.organise.mybatis.db.mybatis.model.Dept;
import com.jemmmedia.organise.mybatis.db.mybatis.model.GroupAccountInfo;
import com.jemmmedia.organise.mybatis.db.mybatis.model.Regions;
import com.jemmmedia.organise.mybatis.db.mybatis.model.User;
import com.jemmmedia.organise.mybatis.db.mybatis.model.UserDetailedBean;
import com.jemmmedia.organise.service.impl.exception.LoginException;
import com.jemmmedia.organise.service.impl.service.RegionsService;

/**
 * @author harjinder
 *
 */
@Controller
public class PreferencesController extends CommonController {
	
	@Resource
	RegionsService regionsService;
	
	@RequestMapping("getLoggedInUser")
	@ResponseBody List<Object> getLoggedInUser(){
		List<Object> listObj = new ArrayList<>();
		User user = userService.getUserDetailFromSpringSecurity();
		listObj.add(user);
		listObj.add(deptService.selectAllDepts(user.getGroupId()));
		listObj.add(rolesService.getAllRoles());
		return listObj;
	}
	
	@RequestMapping("groupAccountInfo")
	@ResponseBody GroupAccountInfo groupAccountInfo(){
		
		UserDetailedBean user = (UserDetailedBean) userService.getUserDetailFromSpringSecurity();
		//getUsersDetail(model, user);

		String folderSizeInMB = "0";
		String spacePercentage = "0";
		GroupAccountInfo groupAccountInfo =  new GroupAccountInfo();
		
		try {
			long groupId = user.getGroupId();
			
			String packageName = "Free";
			if(user.getPackageType() > 1){
				packageName = groupService.selectGroupPackage(groupId);
				double folderSize = userService.getGroupFolderSize();
				double folderSizePercent = (folderSize*100)/user.getAllowedStorage();
				DecimalFormat df = new DecimalFormat("###.##");
				folderSizeInMB = df.format(folderSize * 1000);
				spacePercentage = df.format(folderSizePercent);
			}			
		
		DateTime packageEndDate = null;
		Date dt = user.getGroupCreationDate();
		UserDetailedBean userPackageDetail = userService
				.getUserPackage(groupId);

		DateTime groupCreationDate = new DateTime(dt);

		DateTime trialEndDate = new DateTime(dt).plusDays(trialPeriod);
		
		DateTime currentDateTime = new DateTime();
		
		if (currentDateTime.compareTo(trialEndDate) > 0
				&& userPackageDetail.getUsersCount() < 3) {
			packageEndDate = null;
		}
		
		if (user.getSubscrDate() != null) {
			Date dt1 = user.getSubscrDate();
			DateTime subscriptionDate = new DateTime(dt1);
			packageEndDate = subscriptionDate.plusDays(subscriptionPeriod);
		}
		if (currentDateTime.compareTo(trialEndDate) < 0) {
			packageName = "Trial Period";
			packageEndDate = trialEndDate;
		}
		
		
		//List<FeedSourceExtBean> feedSources =  feedSourceService.selectAllSources(user.getGroupId());
		
		
		groupAccountInfo.setPackageEndDate(packageEndDate);
		
		groupAccountInfo.setPackageName(packageName);
		groupAccountInfo.setTasksCompleted(feedEntryService.tasksCompleted(groupId));
		groupAccountInfo.setTasksopen(feedEntryService.tasksOpen(groupId));
		groupAccountInfo.setTasksPending(feedEntryService.tasksPending(groupId));
		groupAccountInfo.setGroupCreationDate(groupCreationDate);
		groupAccountInfo.setRegionsCount(regionsService.regionsCount(groupId));
		groupAccountInfo.setUsersCount(userPackageDetail.getUsersCount());
		groupAccountInfo.setTopicsCount(feedSourceService.topicsCount(groupId));
		groupAccountInfo.setCommentsCount(feedEntryService.commentsCount(groupId));
		groupAccountInfo.setFolderSizeInMB(folderSizeInMB);
		groupAccountInfo.setSpacePercentage(spacePercentage);
		
		
		} catch (LoginException e) {
			e.printStackTrace();
		}
		
		return groupAccountInfo;
	}
	
	@RequestMapping("displayRegions")
	@ResponseBody List<Regions> displayRegions(){
		User user = userService.getUserDetailFromSpringSecurity();
		return userService.selectAllRegions(user.getGroupId());
	}
	@RequestMapping("displayDepts")
	@ResponseBody List<Dept> displayDepts(){
		User user = userService.getUserDetailFromSpringSecurity();
		return deptService.selectAllDepts(user.getGroupId());
	}
	@RequestMapping("displayUsers")
	@ResponseBody List<UserDetailedBean> displayUsers(){
		User user = userService.getUserDetailFromSpringSecurity();
		return userService.selectAllUsersDetail(user.getGroupId(), null);
	}
	
}

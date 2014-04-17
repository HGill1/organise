/**
 * 
 */
package com.jemmmedia.organise.service.impl.controller;

import javax.annotation.Resource;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.jemmmedia.organise.service.impl.service.GroupsService;
import com.jemmmedia.organise.service.impl.utility.ReminderNotificationUtl;


/**
 * @author harjinder
 *
 */
public class ReminderNotificationController extends QuartzJobBean {
	
	private ReminderNotificationUtl reminderNotificationUtl;
	
	@Resource
	GroupsService groupService;

	public void setRunMeTask(ReminderNotificationUtl reminderNotificationUtl) {
		this.reminderNotificationUtl = reminderNotificationUtl;
	}

	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		
	//	String emailIds = groupService.selectEmailsForRemiders();
		
		reminderNotificationUtl.printMe();
		
	}

}

package com.jemmmedia.organise.service.impl.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.Resource;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gdata.util.ServiceException;
import com.jemmmedia.organise.mybatis.db.mybatis.model.FeedEntryBean;
import com.jemmmedia.organise.mybatis.db.mybatis.model.FeedEntryWithChild;
import com.jemmmedia.organise.mybatis.db.mybatis.model.FeedSourceExtBean;
import com.jemmmedia.organise.mybatis.db.mybatis.model.User;
import com.jemmmedia.organise.mybatis.db.mybatis.model.UserDetailedBean;
import com.jemmmedia.organise.mybatis.db.mybatis.model.ViewType;
import com.jemmmedia.organise.service.impl.service.FeedsUsersService;
//import org.joda.time.DateTime;
//import org.springframework.web.client.RestTemplate;
/*import com.jemmmedia.organise.service.impl.service.ViewTypeService;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import com.google.common.net.MediaType;
import com.google.gdata.client.*;
import com.google.gdata.client.calendar.*;
import com.google.gdata.data.*;
import com.google.gdata.data.acl.*;
import com.google.gdata.data.calendar.*;
import com.google.gdata.data.extensions.*;*/
/*import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;*/



@Controller
public class JournalController extends CommonController {
	static final Logger logger = Logger.getLogger(JournalController.class);
	
	private static final String BASE64_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
			+ "abcdefghijklmnopqrstuvwxyz" + "0123456789+/";

	
	//private org.joda.time.DateTime jodaDateTime;
	//private com.google.gdata.data.DateTime gDateTime;
	
	@Resource(name = "feedsUsersService")
	private FeedsUsersService feedsUsersService;
	
	
	@RequestMapping("index")
	public ModelAndView getJournal(Model model, @ModelAttribute FeedEntryBean feedEntryBean,
			@RequestParam(value = "pageStart", defaultValue = "0")int start, HttpSession session) throws IllegalArgumentException{
		
		UserDetailedBean userLoggedIn = (UserDetailedBean) userService.getUserDetailFromSpringSecurity();
		
			
		
		Date dt =  userLoggedIn.getGroupCreationDate();
		
		//jodaDateTime.plusDays(trialPeriod);
		
		org.joda.time.DateTime groupCreated =  new org.joda.time.DateTime(dt).plusDays(trialPeriod);
		
		org.joda.time.DateTime currentDateTime = new org.joda.time.DateTime();
		try{
		
			if (userLoggedIn.getSubscrDate() != null) {

				//checkJemmUsers(userLoggedIn);

				Date sdt = userLoggedIn.getSubscrDate();

				org.joda.time.DateTime subscrEndDate = new org.joda.time.DateTime(sdt)
						.plusDays(subscriptionPeriod);

				if (currentDateTime.compareTo(subscrEndDate) > 0) {
					return new ModelAndView("pricing", "user", userLoggedIn);
				}
				if (userLoggedIn.getAllowedStorage() != 0) {
					if (userService.getGroupFolderSize() > userLoggedIn
							.getAllowedStorage()) {
						return new ModelAndView("pricing", "user", userLoggedIn);
					}
				}

			}

			// System.out.println("groupCreated --"+groupCreated
			// +": package Type --"+userLoggedIn.getPackageType()
			// +":  comapre should be 1 " +
			// currentDateTime.compareTo(groupCreated));
			if (currentDateTime.compareTo(groupCreated) > 0
					&& userLoggedIn.getPackageType() == 1
					&& userLoggedIn.getUsersCount() > 2
					&& userLoggedIn.getRoleId() < 3) {
				return new ModelAndView("pricing", "user", userLoggedIn);
			}
			if (currentDateTime.compareTo(groupCreated) > 0
					&& userLoggedIn.getPackageType() == 1
					&& userLoggedIn.getUsersCount() > 2
					&& userLoggedIn.getRoleId() > 2) {
				throw new UsernameNotFoundException("Your account has been expired. Please contact your administrator.");
			}
		
		}catch(UsernameNotFoundException e){
			model.addAttribute("jemmUsersMessage", e.getMessage());
			return new ModelAndView("login");
		}
		
		Calendar currentDate = new GregorianCalendar();
		currentDate.set(Calendar.HOUR_OF_DAY, 0);
		currentDate.set(Calendar.MINUTE, 0);
		currentDate.set(Calendar.SECOND, 0);
		currentDate.set(Calendar.MILLISECOND, 0);
		
		session.setAttribute("topicsSelected",null);
		
		//DateTime currentDate1 = CURRENT_DATE_TIME.toDateMidnight().toDateTime();
		model.addAttribute("currentDateWithZeroTime", currentDateTime.toDateMidnight().toDateTime().toLocalTime());
		
		if(feedSourceService.topicsCount(userLoggedIn.getGroupId()) == 0 && userLoggedIn.getRoleId() < 3){
			return new ModelAndView("welcomePage","user",userLoggedIn);
		}
		
		tasksList(model, start, userLoggedIn);	
		
		return displayJournalPage(model, feedEntryBean, start); 
		
	}
	
	@RequestMapping("createDefaultTopic")	
	public ModelAndView createDefaultTopic(Model model, @ModelAttribute("feedSource") FeedSourceExtBean feedSourceExtBean,
														@ModelAttribute FeedEntryBean feedEntryBean){
		
		int start = 0;
		ArrayList<Long> usersId = new ArrayList<>(); 
		
		UserDetailedBean user = (UserDetailedBean) userService.getUserDetailFromSpringSecurity();
		
		usersId.add(user.getId());
		
		feedSourceExtBean.setName("Default");
		feedSourceExtBean.setSourceType(0);
		feedSourceExtBean.setColour("#A65200");
		feedSourceExtBean.setGroupId(user.getGroupId());
		
		Date dt = new Date();
		feedSourceExtBean.setCreatedAt(dt);
		feedSourceExtBean.setUpdatedAt(dt);
		
		feedSourceService.insertSelective(feedSourceExtBean);
		
		feedSourceService.insert(usersId, feedSourceExtBean.getId());
		
		return displayJournalPage(model, feedEntryBean, start); 		
		
	}
	
	@RequestMapping("welcomePage")
	public ModelAndView displaywelecomePage(@ModelAttribute User user){
		
		return new ModelAndView("welcomePage");
		
	}

	@RequestMapping(value = "showAssignedTask", method = RequestMethod.GET)
	 public ModelAndView showAssignedTask(Model model,@ModelAttribute FeedEntryBean feedEntryBean,@RequestParam("assignto") long assignto,
																		@RequestParam ("taskId") long taskId){
		
		List<List<FeedEntryBean>> pagedListHolder = null;
		UserDetailedBean userLoggedIn = (UserDetailedBean) userService.getUserDetailFromSpringSecurity();
		
		//checkJemmUsers(userLoggedIn);
		
		getUsersDetail(model, userLoggedIn);
		try {
			pagedListHolder = feedEntryService.selectAssignedTask(assignto, taskId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("pagedListHolder", pagedListHolder);	
		return displayJournalPage(model, feedEntryBean, 0); 
	}
	
	@RequestMapping("pricing")
	public ModelAndView displayPricing(@ModelAttribute User user){
		
		return new ModelAndView("pricing");
		
	}
	
	@Transactional
	@RequestMapping(value = "addTask", method = RequestMethod.POST)
	@ResponseBody public FeedEntryBean addTask(@ModelAttribute FeedEntryBean feedEntryBean,
											   @RequestParam ("fileIds") String fileIds,
											   @RequestParam ("assignEmailAndId") String assignEmailAndId,
											   @RequestParam(value = "pageStart", defaultValue = "0")int start,
											   HttpSession session) throws IOException, ServiceException {
		
				
		List<List<FeedEntryBean>> pagedListHolder = null;
		
		UserDetailedBean user = (UserDetailedBean) userService.getUserDetailFromSpringSecurity();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		String taskEmailSent  = "";
		String emailIds = "";
		String assignToName = "";
		String deptName = "";
		
		try {
			if(!assignEmailAndId.isEmpty()){			
				if(assignEmailAndId.indexOf("d") == 0){
					long assignToDeptId = Long.parseLong(assignEmailAndId.replaceAll("d", ""));
					feedEntryBean.setAssignToDept(assignToDeptId);
					feedEntryBean.setAssignTo((long)0);
					assignToName = "User";
					deptName = feedEntryBean.getAssignToName();
					
					emailIds = getDeptUsersEmails(assignToDeptId);
					
				}else{
					String[] strAssignToEmailAndId =  assignEmailAndId.split("#");
					long AssignToId =  Long.parseLong(strAssignToEmailAndId[0]);
					feedEntryBean.setAssignTo(AssignToId);
					feedEntryBean.setAssignToDept((long)0);
					emailIds = strAssignToEmailAndId[1];
					taskEmailSent = strAssignToEmailAndId[2];
					assignToName = feedEntryBean.getAssignToName();
				}
			}
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
		}
		
		
		String d1 = sdf.format(feedEntryBean.getDeadline());
    	
		if(d1.contains("1970")){
			feedEntryBean.setDeadline(null);
		}
		//by default status is pending		
		feedEntryBean.setStatus(3);
		feedEntryBean.setParentFeed(true);
		feedEntryService.insertFeedEntry(feedEntryBean,fileIds,session);
		
		
		/*CalendarService myService =  new CalendarService("Jemm organise");
		try {
			//myService.setUserCredentials("hgill@jemmtech.com", "July@2012");
			// Create the calendar
			CalendarEntry calendar = new CalendarEntry();
			calendar.setTitle(new PlainTextConstruct("Jemm organise"));
			calendar.setSummary(new PlainTextConstruct("All Jemm organise tasks."));
			calendar.setTimeZone(new TimeZoneProperty("UK/London"));
			calendar.setHidden(HiddenProperty.FALSE);
			calendar.setColor(new ColorProperty("#2952A3"));
			calendar.addLocation(new Where("","","London"));

			// Insert the calendar
			URL postUrl = new URL("https://www.google.com/calendar/feeds/default/owncalendars/full");
			CalendarEntry returnedCalendar = myService.insert(postUrl, calendar);
			System.out.println("Calender created :"+returnedCalendar);
			
			//CalendarEntry calendar = new CalendarEntry();
			//calendar.setId("jemmgroup.com_ruti6seceh4436kkcaim3knaqc@group.calendar.google.com");
			
			URL postUrl =
					 new URL("https://www.google.com/calendar/feeds/jemmgroup.com_ruti6seceh4436kkcaim3knaqc@group.calendar.google.com/private/full");
					CalendarEventEntry myEntry = new CalendarEventEntry();
					
					EventWho participant = new EventWho();  
					participant.setEmail(strAssignToEmailAndId[1]);
					//participant.
					
					//ArrayList<EventAttendee> attendees = new ArrayList<EventAttendee>();
					
					

					myEntry.setTitle(new PlainTextConstruct(feedEntryBean.getName()));
					myEntry.setContent(new PlainTextConstruct(feedEntryBean.getSummary()));

					//DateTime startTime = DateTime.parseDateTime(sdf.format(feedEntryBean.getCreatedAt().toString()));
					//DateTime endTime = DateTime.parseDateTime(d1.toString());
					When eventTimes = new When();
					eventTimes.setStartTime(DateTime.parseDate(sdf.format(feedEntryBean.getCreatedAt())));
					eventTimes.setEndTime(DateTime.parseDate(d1));
					myEntry.addTime(eventTimes);
					//myEntry.setSendEventNotifications(true);
					myEntry.addParticipant(participant);
					// Send the request and receive the response:
					CalendarEventEntry insertedEntry = myService.insert(postUrl, myEntry);
					
					AclEntry entry = new AclEntry();
					entry.setScope(new AclScope(AclScope.Type.USER, "harji.gill@gmail.com"));
					entry.setRole(CalendarAclRole.RESPOND);

					//URL aclUrl =
					//  new URL("https://www.google.com/calendar/feeds/jemmgroup.com_ruti6seceh4436kkcaim3knaqc@group.calendar.google.com/acl/full");

					AclEntry insertedAclEntry = myService.insert(aclUrl, entry);			
			
			
			
		} catch (AuthenticationException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		
		
		String taskurl = siteUrl + "showAssignedTask?assignto="+ feedEntryBean.getAssignTo() + "&taskId="+feedEntryBean.getId(); 
		try{
			if(feedEntryBean.getAssignToDept() != 0){
				String groupEmailBody = taskAssignedToGroup (taskurl, assignToName, user.getFirstName()+" "+user.getLastName(), deptName);
				
				//sendmailService.sendMail("no-reply@gmail.com", emailIds, "New task has been assigned to you", groupEmailBody);
				sendmailService.sendMail("no-reply@gmail.com", "harji.gill@gmail.com", "New task has been assigned to you", groupEmailBody);
			}else{
				if(taskEmailSent.equalsIgnoreCase("true")){
					String body  = taskAssignedEmailBody(taskurl, assignToName, user.getFirstName()+" "+user.getLastName());
					//sendmailService.sendMail("no-reply@gmail.com", emailIds, "New task has been assigned to you", body);
					sendmailService.sendMail("no-reply@gmail.com", "harji.gill@gmail.com", "New task has been assigned to you", body);
				}
			}
		} catch (AddressException e) {
			e.printStackTrace();
		}
				
		//pagedListHolder = feedEntryService.getFeedList(start,user.getId());		
		return feedEntryBean;
	}
	
	@RequestMapping(value = "reply", method = RequestMethod.POST)
	@ResponseBody public List<List<FeedEntryBean>> reply(@ModelAttribute FeedEntryBean feedEntryBean,
											   @RequestParam ("fileIds") String fileIds,
											   @RequestParam ("parentId") long parentId,
											   @RequestParam ("statusUrgent") boolean statusUrgent,
											   @RequestParam ("reassignerUser") String reassignerUser,
											   @RequestParam ("reassigneeName") String reassigneeName,
											   @RequestParam ("taskCreatorId") long taskCreatorId,
											   @RequestParam(value = "pageStart", defaultValue = "0")int start,
											   HttpSession session) {
		
		List<List<FeedEntryBean>> pagedListHolder = null;
		UserDetailedBean user = (UserDetailedBean) userService.getUserDetailFromSpringSecurity();
		
		
		String reassignerEmail = null;
		String reassignerTaskUpdateEmail = null;
		String fetchedReassigneeName =  "";
		long orignalAssignee = feedEntryBean.getAssignTo();
		long assignedToDept = 0;
		
		if(feedEntryBean.getAssignToDept() != 0){
			assignedToDept = feedEntryBean.getAssignToDept();
		}
		
		
		if(!reassignerUser.trim().isEmpty() && !reassigneeName.isEmpty()){
			
			long reassignerId;
			
			if(reassignerUser.indexOf("d") == 0){
				
				reassignerId = Long.parseLong(reassignerUser.replaceAll("d", ""));
				
				reassignerEmail = getDeptUsersEmails(reassignerId);
				
				fetchedReassigneeName =  "User";
				
				feedEntryBean.setAssignToDept(reassignerId);
				feedEntryBean.setAssignTo((long)0);
				
				
			}else{			
				String [] reassignerDetail = reassignerUser.split("#");
				reassignerId = Long.parseLong(reassignerDetail[0]);
				reassignerEmail = reassignerDetail[1];
				reassignerTaskUpdateEmail = reassignerDetail[2];
				fetchedReassigneeName = reassignerDetail[3];
				feedEntryBean.setAssignTo(reassignerId);
				feedEntryBean.setAssignToDept((long)0);
			}
			
			
		}
		
		feedEntryBean.setUpdatedBy(user.getId());
		
		// this method insert feeds as well as attachment details and then return feed entryId
		long feedEntryId = feedEntryService.insertFeedEntry(feedEntryBean,
			fileIds,session);
		
		feedEntryService.updateMainStatus(feedEntryBean.getStatus(), parentId,statusUrgent,feedEntryBean.getAssignTo(), assignedToDept);
		FeedEntryWithChild feedEntryWithChild = new FeedEntryWithChild();	
		
		feedEntryWithChild.setParentId(parentId);
		feedEntryWithChild.setFeedId(feedEntryId);
		
		feedEntryWithChildService.insertChild(feedEntryWithChild);
		
		//pagedListHolder = feedEntryService.getFeedList(start,user.getId());
		
		pagedListHolder = feedEntryService.selectAssignedTask(feedEntryBean.getAssignTo(), parentId);
		
		String taskurl = siteUrl + "showAssignedTask?assignto="+ feedEntryBean.getAssignTo() + "&taskId="+parentId; 
		
		
		
		String taskAssignTo = feedEntryBean.getAssignToName();
		String taskTitle = feedEntryBean.getTaskTitle();
		String taskCreator =  feedEntryBean.getAssignerName();
		String taskSummary = feedEntryBean.getSummary();
		String taskCreatorEmail = feedEntryBean.getTaskCreatorEmail();
		
		sendMailToUsersInTask(feedEntryBean, parentId, user, taskurl, taskTitle, taskSummary, taskCreatorId, orignalAssignee);
		
		
		if(!reassigneeName.isEmpty() && reassignerEmail != null ){
		
				sendReassignEmail(fetchedReassigneeName, user, reassignerTaskUpdateEmail,reassignerEmail,
				taskurl, taskTitle, taskSummary,taskCreator, taskAssignTo, taskCreatorEmail,reassigneeName);
		}else if(feedEntryBean.getTaskUpdateEmailNotify().equalsIgnoreCase("1")){
			
					sendTaskCompletedAndUpdateEmail(feedEntryBean, user, taskurl,
							taskAssignTo, taskTitle, taskCreator, taskSummary,
							taskCreatorEmail);
		}
		
		
		return pagedListHolder;
	}

	/**
	 * @param reassignerId
	 * @return
	 */
	private String getDeptUsersEmails(long reassignerId) {
		String reassignerEmail;
		List<String> emailIds = deptService.selectUsersEmailInDept(reassignerId);
		
		reassignerEmail = StringUtils.collectionToCommaDelimitedString(emailIds);
		return reassignerEmail;
	}

	/**
	 * @param feedEntryBean
	 * @param parentId
	 * @param user
	 */
	private void sendMailToUsersInTask(FeedEntryBean feedEntryBean,
			long parentId, UserDetailedBean user, String taskurl,
			String taskTitle, String lastComments, long taskCreatorId, long orignalAssignee) {
		List<Long> usersNotToSendMail = new ArrayList<>(2);
		usersNotToSendMail.add(taskCreatorId);
		usersNotToSendMail.add(user.getId());
		
		String emailIds =  "";
		
		
		if(feedEntryBean.getAssignToDept() != 0){
			emailIds = getDeptUsersEmails(feedEntryBean.getAssignToDept()).replaceAll(user.getEmail()+",", "");
			
		}else{
			emailIds = feedEntryBean.getAssignToEmail();
		}

		List<User> usersInvolveInTask = userService.selectUsersInTask(parentId,
				usersNotToSendMail);
		String currentUser = user.getFirstName() + " " + user.getLastName();
		try {
			
			if(user.getId() != orignalAssignee){
					sendMailUserInvolved(feedEntryBean.getStatus(),taskurl, taskTitle, lastComments,
						currentUser, feedEntryBean.getAssignToName(), emailIds);
			}

			if (!usersInvolveInTask.isEmpty()) {

				for (User usr : usersInvolveInTask) {

					String userInvolved = usr.getFirstName() + " "
							+ usr.getLastName();
					
					if(!usr.getEmail().trim().equalsIgnoreCase(feedEntryBean.getAssignToEmail())){
						sendMailUserInvolved(feedEntryBean.getStatus(),taskurl, taskTitle, lastComments,
								currentUser, userInvolved,usr.getEmail());
					}

					
				}

			}
		} catch (AddressException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @param taskurl
	 * @param taskTitle
	 * @param lastComments
	 * @param currentUser
	 * @param userInvolved
	 * @throws AddressException
	 */
	private void sendMailUserInvolved(int taskStatus, String taskurl,
			String taskTitle, String lastComments, String currentUser,
			String userInvolved, String userEmail) throws AddressException {
		
		String emailBody = null;
		String emailSubject = null;

		if (taskStatus == 4) {
			 emailBody  = userInTaskCompletedBody(taskurl,
					userInvolved, currentUser, taskTitle, lastComments);
			 emailSubject =  "Task has been completed";
		} else {

			 emailBody = userInTaskBody(taskurl,
					userInvolved, currentUser, taskTitle, lastComments);
			 emailSubject =  "Task has been updated";
		}
		
		 //sendmailService.sendMail("no-reply@gmail.com", userEmail, emailSubject, emailBody);
		sendmailService.sendMail("no-reply@gmail.com", "harji.gill@gmail.com", emailSubject, emailBody);
	}

	/**
	 * @param feedEntryBean
	 * @param user
	 * @param taskurl
	 * @param taskAssignTo
	 * @param taskTitle
	 * @param taskCreator
	 * @param taskSummary
	 * @param taskCreatorEmail
	 */
	private void sendTaskCompletedAndUpdateEmail(FeedEntryBean feedEntryBean,
			UserDetailedBean user, String taskurl, String taskAssignTo,
			String taskTitle, String taskCreator, String taskSummary,
			String taskCreatorEmail) {
		try {
			
			
			if(!taskCreatorEmail.trim().equalsIgnoreCase(user.getEmail())){
				String taskSubject = null;
				String taskBody = null;
			
				if(feedEntryBean.getStatus() == 4){
					
					taskBody = taskCompletedEmailBody(taskurl, taskAssignTo, taskTitle, taskCreator, taskSummary); 
					taskSubject = "Your task has been completed";
				}else{
					taskBody  = taskUpdatedEmailBody(taskurl, taskAssignTo, taskTitle, taskCreator);
					taskSubject = "Your task has been updated";					
				}
				//sendmailService.sendMail("no-reply@gmail.com", taskCreatorEmail, taskSubject, taskBody);
				sendmailService.sendMail("no-reply@gmail.com", "harji.gill@gmail.com", taskSubject, taskBody);
			}
		} catch (AddressException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param reassignerName
	 * @param user
	 * @param reassignerTaskUpdateEmail
	 * @param taskurl
	 * @param taskTitle
	 * @param taskSummary
	 */
	private void sendReassignEmail(String reassignerName,
			UserDetailedBean user, String reassignerTaskUpdateEmail,String reassignerEmail,
			String taskurl, String taskTitle, String taskSummary, String taskCreator, String taskAssignTo, String taskCreatorEmail,String reassigneeName) {
		
			String bodyTaskReassigned  = taskReassignedEmailBody(taskurl, user.getFirstName()+" "+user.getLastName(), taskTitle, reassignerName, taskSummary);
			String bodyReassignNotification = taskReassignedNotificationEmailBody(taskurl,taskCreator, taskAssignTo, reassigneeName, taskTitle,  taskSummary);
			try {
				
					//sendmailService.sendMail("no-reply@gmail.com", reassignerEmail, "New task has been assigned to you", bodyTaskReassigned);
					sendmailService.sendMail("no-reply@gmail.com", "harji.gill@gmail.com", "New task has been assigned to you", bodyTaskReassigned);
					//sendmailService.sendMail("no-reply@gmail.com", taskCreatorEmail, "Your task has been reassigned", bodyReassignNotification);
					sendmailService.sendMail("no-reply@gmail.com", "harji.gill@gmail.com", "Your task has been reassigned", bodyReassignNotification);
				
			} catch (AddressException e) {
				e.printStackTrace();
			}
		
	}
	
	
	@RequestMapping(value = "displayReply", method = RequestMethod.POST)
	@ResponseBody public List<FeedEntryBean> selectReplyDetail(@RequestParam ("id") Long id){
		
		return feedEntryService.selectReplyDetail(id);
	}
	
	@RequestMapping("checkUpdates")
	@ResponseBody public List<List<FeedEntryBean>> checkUpdates(@ModelAttribute FeedEntryBean feedEntryBean,
			@RequestParam ("deleted") Boolean deleted,
			@RequestParam ("edited") Boolean edited){
		
		if(feedEntryService.checkUpdates(feedEntryBean.getLoggedInUserId()) > 0 || deleted == true || edited == true ){
			return feedEntryService.filterFeeds(feedEntryBean);
		}
	
		return null;
	}
	
	
	@RequestMapping(value = "filter", method = RequestMethod.POST)
	@ResponseBody public List<List<FeedEntryBean>> filterFeeds(@ModelAttribute FeedEntryBean feedEntryBean, HttpSession session){
		List<List<FeedEntryBean>> pagedListHolder = null;
		if(feedEntryBean.getFeedSourceId()>0){
			List<Long> topicsSelected = new ArrayList<>();
			topicsSelected.add(feedEntryBean.getFeedSourceId());
			session.setAttribute("topicsSelected", topicsSelected);
		}

		try {
			List<Integer> topicsSelected =  (List<Integer>) session.getAttribute("topicsSelected");
			feedEntryBean.setLoggedInUserId(userService.getUserDetailFromSpringSecurity().getId());
			feedEntryBean.setSelectedTopicsList(topicsSelected);
			pagedListHolder = feedEntryService.filterFeeds(feedEntryBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return pagedListHolder;
	}
	
	@RequestMapping(value = "multiSourceFilterFeeds", method = RequestMethod.POST)
	@ResponseBody public List<List<FeedEntryBean>> multiSourceFilterFeeds(@RequestParam("userId") int userId,
																  @RequestParam("pageStart") int pageStart){

		List<List<FeedEntryBean>> pagedListHolder = null;
		try {
			pagedListHolder = feedsUsersService.multiSourceFilterFeeds(userId, pageStart);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return pagedListHolder;
	}
		
	
	@RequestMapping(value = "deleteFeed", method = RequestMethod.POST)
	@ResponseBody public boolean deleteFeed(@RequestParam ("id") Long id){
		 try {
			
			List<FeedEntryWithChild>childfeedRefernces = feedEntryWithChildService.selectChildFeedIds(id);
			
			for(FeedEntryWithChild childfeedId : childfeedRefernces){
				feedEntryService.deleteByPrimaryKey(childfeedId.getFeedId());
			}			 
			
			List<Long> attachmentsMapping = feedEntriesAttachmentsService.selectById(id);
			
			for(Long attachmentId : attachmentsMapping){
				fileAttachmentService.deleteAttachment(attachmentId);
			}
			feedEntryService.deleteByPrimaryKey(id);
			//feedEntriesAttachmentsService.deleteById(id);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return true;
	}
	
	
	@RequestMapping("updateRemaining")
	@ResponseBody public int updateRemaining(@RequestParam ("feedSourceId") Long feedSourceId,
															@RequestParam ("assignTo") Long assignTo){

		int updateRemainingCount = 0;		
		try {
			updateRemainingCount =  feedEntryService.updateRemaining(feedSourceId,assignTo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return updateRemainingCount;
	}
	
	
	@Transactional
	@RequestMapping(value = "editCampaign", method = RequestMethod.POST)
	@ResponseBody public List<List<FeedEntryBean>> editCampaign(@ModelAttribute FeedEntryBean feedEntryBean,
											   @RequestParam ("fileIds") String fileIds,
											   @RequestParam(value = "pageStart", defaultValue = "0")int start) {
		
			//feedEntryBean.setMainStatus(mainStatus);			
		
		// this method insert feeds as well as attachment details 
		List<List<FeedEntryBean>> pagedListHolder = null;
				feedEntryService.updateByPrimaryKey(feedEntryBean);
				
				pagedListHolder = feedEntryService.getFeedList(start, userService.getUserDetailFromSpringSecurity().getId());
				
				return pagedListHolder;
	}
	
	@RequestMapping("search")
	@ResponseBody public List<List<FeedEntryBean>> searchFeeds(@RequestParam ("searchText") String searchText,
															   @RequestParam(value = "pageStart", defaultValue = "0")int start){		
		List<List<FeedEntryBean>> searchedResult = null;
		try {
			searchedResult =  feedEntryService.searchFeeds(start, searchText,userService.getUserDetailFromSpringSecurity().getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return searchedResult;
	}
	
	
	@RequestMapping(value = "followThread", method = RequestMethod.POST)
	@ResponseBody public int followThread(@RequestParam ("followerId") int followerId, @RequestParam ("id") Long id){
		return feedEntryService.updateFollow(followerId, id);
	}
	
	@RequestMapping(value = "threadsFollowedInCat", method = RequestMethod.POST)
	@ResponseBody public List<List<FeedEntryBean>> selectFollowingTasks(@RequestParam ("pageStart") int pageStart,
																		@RequestParam ("followerId") long followerId,
																		@RequestParam ("feedSourceId") long feedSourceId){
		List<List<FeedEntryBean>> pagedListHolder = null;
		try {
			pagedListHolder = feedEntryService.selectFollowingTasks(pageStart, followerId, feedSourceId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return pagedListHolder;
	}
	
	@RequestMapping(value = "threadRecentlyViewed", method = RequestMethod.POST)
	@ResponseBody public List<List<FeedEntryBean>> selectRecentlyViewed(@RequestParam ("viewerId") long viewerId,
																		@RequestParam ("id") long id){
		List<List<FeedEntryBean>> pagedListHolder = null;
		try {
			pagedListHolder = feedEntryService.selectRecentlyViewed(viewerId, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return pagedListHolder;
	}
	
	
	@RequestMapping("download")
	public void downloadAttachment(){
		//Filedownload.save()
	}
	
	@RequestMapping(value = "updateRecentlyViewed", method = RequestMethod.POST)
	@ResponseBody public int updateRecentlyViewed(@RequestParam("viewerId") long viewerId, 
													@RequestParam("id") long id){
		return feedEntryService.updateRecentlyViewed(viewerId, id);
	}
	
	@RequestMapping("updateTaskMenu")
	@ResponseBody public List<String> updateTaskMenu(@RequestParam("userId") long userId){
		
		return feedEntryService.selectTasksCount(userId);
		
		
	}
	
	
	@RequestMapping(value = "selectlastViewed", method = RequestMethod.POST)
	public @ResponseBody List<FeedEntryBean> selectlastViewed(@RequestParam("userId") long userId){
		return feedEntryService.selectlastViewedDate(userId);
	}
	
	@RequestMapping(value = "displayTodaysTasksPending", method = RequestMethod.GET)
	public ModelAndView displayTodaysTasksPending(Model model,
			@ModelAttribute FeedEntryBean feedEntryBean,
			@RequestParam (value = "pageStart", defaultValue="0") int pageStart){
		List<List<FeedEntryBean>> pagedListHolder = null;
		UserDetailedBean userLoggedIn = (UserDetailedBean) userService.getUserDetailFromSpringSecurity();
		getUsersDetail(model, userLoggedIn);
		try {
			pagedListHolder = feedEntryService.displayTodaysTasksPending(pageStart,userLoggedIn.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("pagedListHolder", pagedListHolder);	
		return displayJournalPage(model, feedEntryBean, 0);
		//return feedEntryService.displayTodaysTasksPending(pageStart,userId);
	}
	
	@RequestMapping(value = "displayOldTasksPending", method = RequestMethod.GET)
	public ModelAndView displayOldTasksPending(Model model,
			@ModelAttribute FeedEntryBean feedEntryBean,
			@RequestParam (value = "pageStart", defaultValue="0") int pageStart){
		
		List<List<FeedEntryBean>> pagedListHolder = null;
		UserDetailedBean userLoggedIn = (UserDetailedBean) userService.getUserDetailFromSpringSecurity();
		getUsersDetail(model, userLoggedIn);
		try {
			pagedListHolder = feedEntryService.displayOldTasksPending(pageStart,userLoggedIn.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("pagedListHolder", pagedListHolder);	
		return displayJournalPage(model, feedEntryBean, 0);
		
	}
	
	@RequestMapping(value = "displayUrgentTasks", method = RequestMethod.GET)
	public ModelAndView displayUrgentTasks(Model model,
			@ModelAttribute FeedEntryBean feedEntryBean,
			@RequestParam (value = "pageStart", defaultValue="0") int pageStart){
		
		List<List<FeedEntryBean>> pagedListHolder = null;
		UserDetailedBean userLoggedIn = (UserDetailedBean) userService.getUserDetailFromSpringSecurity();
		getUsersDetail(model, userLoggedIn);
		try {
			pagedListHolder = feedEntryService.displayUrgentTasks(pageStart,userLoggedIn.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("pagedListHolder", pagedListHolder);	
		return displayJournalPage(model, feedEntryBean, 0);
		//return feedEntryService.displayUrgentTasks(pageStart,userId);
	}
	
	@RequestMapping(value = "displayTodaysDueTasks", method = RequestMethod.GET)
	public ModelAndView displayTodaysDueTasks(Model model,
			@ModelAttribute FeedEntryBean feedEntryBean,
			@RequestParam (value = "pageStart", defaultValue="0") int pageStart){
		List<List<FeedEntryBean>> pagedListHolder = null;
		UserDetailedBean userLoggedIn = (UserDetailedBean) userService.getUserDetailFromSpringSecurity();
		getUsersDetail(model, userLoggedIn);
		try {
			pagedListHolder = feedEntryService.displayTodaysDueTasks(pageStart,userLoggedIn.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("pagedListHolder", pagedListHolder);	
		return displayJournalPage(model, feedEntryBean, 0);
		//return feedEntryService.displayTodaysDueTasks(pageStart,userId);
	}
	
	@RequestMapping(value = "displayAllMyTasks", method = RequestMethod.GET)
	public ModelAndView displayAllMyTasks(Model model,
			@ModelAttribute FeedEntryBean feedEntryBean,
			@RequestParam (value = "feedSourceId", defaultValue="0") long feedSourceId,
			@RequestParam (value = "pageStart", defaultValue="0") int pageStart){
		List<List<FeedEntryBean>> pagedListHolder = null;
		UserDetailedBean userLoggedIn = (UserDetailedBean) userService.getUserDetailFromSpringSecurity();
		getUsersDetail(model, userLoggedIn);
		try {
			pagedListHolder = feedEntryService.displayAllMyTasks(pageStart,userLoggedIn.getId(), feedEntryBean.getOrderBy(),feedSourceId,userLoggedIn.getDept());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("pagedListHolder", pagedListHolder);	
		return displayJournalPage(model, feedEntryBean, 0);
	}
	
	@RequestMapping(value = "displayTodaysTasksPending", method = RequestMethod.POST)
	public @ResponseBody List<List<FeedEntryBean>> displayTodaysTasksPending(@RequestParam ("pageStart") int pageStart,@RequestParam("userId") long userId){
		return feedEntryService.displayTodaysTasksPending(pageStart,userId);
	}
	
	@RequestMapping("displayOldTasksPending")
	public @ResponseBody List<List<FeedEntryBean>> displayOldTasksPending(@RequestParam ("pageStart") int pageStart,@RequestParam("userId") long userId){
		return feedEntryService.displayOldTasksPending(pageStart,userId);
	}
	
	@RequestMapping(value = "displayUrgentTasks", method = RequestMethod.POST)
	public @ResponseBody List<List<FeedEntryBean>> displayUrgentTasks(@RequestParam ("pageStart") int pageStart,@RequestParam("userId") long userId){
		return feedEntryService.displayUrgentTasks(pageStart,userId);
	}
	
	@RequestMapping(value = "displayTodaysDueTasks", method = RequestMethod.POST)
	public @ResponseBody List<List<FeedEntryBean>> displayTodaysDueTasks(@RequestParam ("pageStart") int pageStart,@RequestParam("userId") long userId){
		return feedEntryService.displayTodaysDueTasks(pageStart,userId);
	}
	
	@RequestMapping(value = "displayAllMyTasks", method = RequestMethod.POST)
	public @ResponseBody List<List<FeedEntryBean>> displayAllMyTasks(@RequestParam ("pageStart") int pageStart,
			@RequestParam("userId") long userId,
			@RequestParam (value = "feedSourceId", defaultValue="0") long feedSourceId,
			@RequestParam ("orderBy") int orderBy){
		UserDetailedBean userLoggedIn = (UserDetailedBean) userService.getUserDetailFromSpringSecurity();
		return feedEntryService.displayAllMyTasks(pageStart,userId, orderBy,feedSourceId,userLoggedIn.getDept());
	}
	
	@RequestMapping("updateViewType")
	public @ResponseBody int updateViewType(@ModelAttribute ViewType viewType){
		
		Date created_at =  new Date();
		viewType.setCreatedAt(created_at);		
		
		viewTypeService.insert(viewType);
		
		return 1;
		
	}
	
	private static String encodeCredentialsBasic(String username, String password) {
		String encode = username + ":" + password;
		int paddingCount = (3 - (encode.length() % 3)) % 3;
		encode += "\0\0".substring(0, paddingCount);
		StringBuilder encoded = new StringBuilder();
		
		for (int i = 0; i < encode.length(); i += 3) {
			int j = (encode.charAt(i) << 16) + (encode.charAt(i + 1) << 8)
					+ encode.charAt(i + 2);
			encoded.append(BASE64_CHARS.charAt((j >> 18) & 0x3f));
			encoded.append(BASE64_CHARS.charAt((j >> 12) & 0x3f));
			encoded.append(BASE64_CHARS.charAt((j >> 6) & 0x3f));
			encoded.append(BASE64_CHARS.charAt(j & 0x3f));
		}
		return encoded.toString();
	}
		
}

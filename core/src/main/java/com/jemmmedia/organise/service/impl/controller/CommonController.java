package com.jemmmedia.organise.service.impl.controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.jemmmedia.organise.mybatis.db.mybatis.model.Dept;
import com.jemmmedia.organise.mybatis.db.mybatis.model.FeedEntryBean;
import com.jemmmedia.organise.mybatis.db.mybatis.model.Regions;
import com.jemmmedia.organise.mybatis.db.mybatis.model.User;
import com.jemmmedia.organise.mybatis.db.mybatis.model.UserDetailedBean;
import com.jemmmedia.organise.service.impl.exception.LoginException;
import com.jemmmedia.organise.service.impl.service.AnnouncementService;
import com.jemmmedia.organise.service.impl.service.DeptService;
import com.jemmmedia.organise.service.impl.service.FeedEntriesAttachmentsService;
import com.jemmmedia.organise.service.impl.service.FeedEntryService;
import com.jemmmedia.organise.service.impl.service.FeedEntryWithChildService;
import com.jemmmedia.organise.service.impl.service.FeedSourceService;
import com.jemmmedia.organise.service.impl.service.FileAttachmentService;
import com.jemmmedia.organise.service.impl.service.GroupsService;
import com.jemmmedia.organise.service.impl.service.RolesService;
import com.jemmmedia.organise.service.impl.service.SendmailService;
import com.jemmmedia.organise.service.impl.service.StatusService;
import com.jemmmedia.organise.service.impl.service.UserService;
import com.jemmmedia.organise.service.impl.service.ViewTypeService;
import com.jemmmedia.organise.service.impl.utility.CryptoUtils;
//@RequestMapping("/")
@Controller
public class CommonController implements HttpSessionBindingListener{
	
	
	public CommonController() {
		super();
	}
	static final Logger logger = Logger.getLogger(CommonController.class);
	
	
	//static final int TRIAL_PERIOD = 14;  
	
	@Value("${trial.period}")
	protected int trialPeriod;
	
	@Value("${subscription.period}")
	protected int subscriptionPeriod;
	
	@Value("${site.url}")
	protected String siteUrl;
	
	@Value("${live.url}")
	protected String liveUrl;
	
	@Value("${notifier.emails}")
	protected String notifierEmails;
	
	@Resource
	DeptService deptService;
	
	//static final String siteUrl = "http://localhost:8080/journal2/";
	
	//static final String siteUrl = "http://getonit.jemmgroup.com/";
	//static final String siteUrl = "http://getonit-demo.jemmgroup.com/";
	//static final String siteUrl = "http://funbox.jemmgroup.com/";
	//static final String siteUrl = "http://ciesco.jemmgroup.com/";
	//static final String siteUrl = createUser.getUsername();
	
	@Resource(name = "userService")
	protected UserService userService; 
	
	@Resource(name = "feedEntryService")
	protected FeedEntryService feedEntryService; 
	
	@Resource(name = "sendmailService")
	protected SendmailService sendmailService;
	
	@Resource(name = "feedSourceService")
	protected FeedSourceService feedSourceService;
	
	@Resource(name = "statusService")
	protected StatusService statusService;
	
	@Resource(name = "feedEntryWithChildService")
	protected FeedEntryWithChildService feedEntryWithChildService;
	
	@Resource(name = "fileAttachmentService")
	protected FileAttachmentService fileAttachmentService;
	
	@Resource(name = "feedEntriesAttachmentsService")
	protected FeedEntriesAttachmentsService feedEntriesAttachmentsService;
	
	@Resource(name = "announcementService")
	private AnnouncementService announcementService;
	
	@Resource(name = "sessionRegistry")
	private SessionRegistryImpl sessionRegistry;
	
	@Resource(name="rolesService")
	protected RolesService rolesService;
	
	@Resource
	protected GroupsService groupService;
	
	@Resource
	protected ViewTypeService viewTypeService;
	
	//protected static List<User> allActiveUsers;
	
	
	//protected List<List<FeedEntryBean>> pagedListHolder;
	
	
	//private static final User user = getLoggedInUser();
	
	
	protected static final String EMAIL_PATTERN = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	
	/**
	 * @param userLoggedIn
	 * @throws UsernameNotFoundException
	 */
	protected void checkJemmUsers(UserDetailedBean userLoggedIn)
			throws UsernameNotFoundException {
		if(userLoggedIn.getGroupId() == 1 && userLoggedIn.getRoleId() >1){
			throw new UsernameNotFoundException("URL has been changed for all Jemm Organise Users. Please use <b> https://jemm.organise.net/ </b>for organise login");
		}
	}
	

	/**
	 * @param model
	 * @param feedEntryBean
	 * @param start
	 * @return
	 */
	protected ModelAndView displayJournalPage(Model model,
			FeedEntryBean feedEntryBean, int start) {
		long feedSourceId  =  0;
		long assignTo  =  0;
		List<Long> onlineUsersIds = new ArrayList<>();
		List<User> onlineUsers = new ArrayList<>();
		
		
		
	//	long timeStart, timeEnd;
		
		int pageNumber =  0;
		
		User userLoggedIn = userService.getUserDetailFromSpringSecurity();
		
		//System.out.println("session"+ sessionRegistry.getAllPrincipals().size());
		
		//sessionRegistry.getSessionInformation(null);
		
		for (Object user: sessionRegistry.getAllPrincipals()) {
				User onlineUser = (User) user;
				if(Long.compare(userLoggedIn.getGroupId(), onlineUser.getGroupId())==0){
					onlineUsersIds.add(onlineUser.getId());
					onlineUsers.add(onlineUser);
				}
		     }
		if(onlineUsersIds.size() <= 0){
			onlineUsersIds = null;
		}
		
		List<User> allActiveUsers = userService.getAllUsers(userLoggedIn.getGroupId(), onlineUsersIds);
		
		if(feedEntryBean.getFeedSourceId() != null){
			
			feedSourceId = feedEntryBean.getFeedSourceId();
			
		}	
		
		
		if(feedEntryBean.getAssignTo() != null){
			assignTo = feedEntryBean.getAssignTo();
		}
		
		getLoginUserDetail(model);
		
		List<Dept> allDeptsWithUsers =  deptService.selectAllUsersInDepts(userLoggedIn.getGroupId());
		
		model.addAttribute("userFeedSources", feedSourceService.selectUsersFeedSources(userLoggedIn.getId()));
		
		model.addAttribute("pageNumber", pageNumber);
		
		model.addAttribute("status", statusService.getAllStatus());
		
		model.addAttribute("users", userService.getAllUsers(userLoggedIn.getGroupId(), null));
		
		model.addAttribute("usersOnline", onlineUsers);
		
		model.addAttribute("usersOffline", allActiveUsers);
		
		model.addAttribute("recentlyViewed", feedEntryService.selectlastViewedDate(userLoggedIn.getId()));
		
		model.addAttribute("following", feedSourceService.selectFollowCat(userLoggedIn.getId()));
		
		model.addAttribute("remainingTasks", feedEntryService.updateRemaining(feedSourceId, assignTo));

		model.addAttribute("selectTasksCount", feedEntryService.selectTasksCount(userLoggedIn.getId()));
		
		model.addAttribute("allDeptsWithUsers", allDeptsWithUsers);
		
		if(viewTypeService.selectByPrimaryKey(userLoggedIn.getId()) != null){
		
			model.addAttribute("viewTypeSelected", "selected");			
		}
		
		return new ModelAndView("index","feedEntry", feedEntryBean);
	}
	
	
	
	/**
	 * @param model
	 * @param start
	 * @param userLoggedIn
	 */
	protected void tasksList(Model model, int start, UserDetailedBean userLoggedIn) {
		List<List<FeedEntryBean>> pagedListHolder;
		pagedListHolder = feedEntryService.getAllTasksList(start, userLoggedIn.getId());
		model.addAttribute("pagedListHolder", pagedListHolder);
	}

	
	
	/**
	 * @param session
	 * @throws LoginException
	 */
	String setUserInSession(HttpSession session) throws LoginException {
		//User user = null;
		String name = SecurityContextHolder.getContext()
				.getAuthentication().getName();	
		if (name.equals("anonymousUser")){
			return "login";
		}
		return null;
		
	}
	
	/**
	 * @param model
	 */
	protected void getLoginUserDetail(Model model) {
		User user = userService.getUserDetailFromSpringSecurity();
		model.addAttribute("user", user);
		model.addAttribute("announcements", announcementService.selectAnnouncementsBetweenDates(user.getGroupId(), user.getRegionId()));
		
	}
	

	/**
	 * @param model
	 * @param user TODO
	 * @param user
	 */
	protected void getUsersDetail(Model model, User user) {
		model.addAttribute("roles", rolesService.getAllRoles());
		List<Regions> allRegions =  userService.selectAllRegions(user.getGroupId());
		List<Dept> allDepts =  deptService.selectAllDepts(user.getGroupId());
		int regionsCount = allRegions.size();
		model.addAttribute("regions",allRegions);
		model.addAttribute("regionsCount",regionsCount);
		model.addAttribute("user", user);
		model.addAttribute("selectTasksCount", feedEntryService.selectTasksCount(user.getId()));
		model.addAttribute("allDepts", allDepts);
		model.addAttribute("users", userService.selectAllUsersDetail(user.getGroupId(), null));
		model.addAttribute("activeUsers", userService.getAllUsers(user.getGroupId(),null));
	}



	@Override
	public void valueBound(HttpSessionBindingEvent arg0) {
		System.out.println("HttpSessionBindingEvent"+arg0.getSession().getAttributeNames());
		
	}



	@Override
	public void valueUnbound(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	/**
	 * @param createUser
	 * @param model
	 * @param Subject
	 * @param url
	 * @return 
	 * @return
	 */
	protected void saveUserDetail(UserDetailedBean createUser, Model model) {
		String Subject = "Organise Login Detail"; 
				String email = createUser.getEmail();
				
				createDefaultPassword(createUser);
				
				userService.insertSelective(createUser);
			
				sendEmail(createUser, Subject, siteUrl, email);
				
				model.addAttribute("usersCount", userService.countByGroup(createUser.getGroupId()));
				
				model.addAttribute("msgUser", "User created successfully.");
				
			}



	/**
	 * @param createUser
	 * @param Subject
	 * @param url
	 * @param email
	 */
	protected void sendEmail(UserDetailedBean createUser, String Subject,
			String url, String email) {
		String body = emailBody(url, email, createUser.getUserPassword(), StringUtils.capitalize(createUser.getFirstName()+" "+ createUser.getLastName()));
		
		try {
			sendmailService.sendMail("no-reply@gmail.com", email, Subject, body);
			//sendmailService.sendMail("no-reply@gmail.com", "harji.gill@gmail.com", Subject, body);
		} catch (AddressException e) {
			e.printStackTrace();
		}
	}



	/**
	 * @param createUser
	 * @param password
	 */
	 protected synchronized void createDefaultPassword(UserDetailedBean createUser) {
		String salt = CryptoUtils.friendlyToken();
		try {
			createUser.setPasswordSalt(salt);
			createUser.setCryptedPassword(CryptoUtils.encrypt(createUser.getUserPassword() + salt));
			createUser.setPersistenceToken(CryptoUtils.hexToken());
		} catch (NoSuchAlgorithmException | NoSuchProviderException
				| UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}



	/**
	 * @param url
	 * @param email
	 * @param password
	 * @param userName TODO
	 * @return
	 */
	String emailBody(String url, String email, String password, String userName) {
		String body = "Dear "+userName+",<br/><br>" +
				"You are invited to login into Organise. Please find your login details<br/><br/>" +
				"URL: " +url+"<br/>" +
				"Username: "+email+"<br/>" +
				"Password: "+password+"<br/>" +
				"<br/>It is advisable to change this password once you are logged in to Organise.<br/>" +
				"<br/>" +
				"Regards,<br/>" +
				"Organise Team";
		return body;
	}
	
	String notifySalesPeople(UserDetailedBean registerUser) {
		String body = "Dear User,<br/><br>" +
				"A New User has signed up for Organise.<br/><br/>" +
				"Full Name:" +registerUser.getFirstName()+" "+registerUser.getLastName()+"<br/>" +
				"Email Address: "+registerUser.getEmail()+"<br/>" +
				"Company: "+registerUser.getGroupName()+"<br/>" +
				"Country: "+registerUser.getRegionName()+"<br/>" +
				"<br/>" +
				"<br/>" +
				"Regards,<br/>" +
				"Organise Team";
		return body;
	}
	
	
	/**
	 * @param url
	 * @return
	 */
	String taskAssignedEmailBody(String url,String assignToName, String assigner) {
		String body = "Dear "+assignToName+",<br/><br>" +
				"" +
				"A new task has been assigned to you in Organise by <b>" +assigner+"</b>. Please click on link below to view your task<br/><br/>" +
				"URL: " +url+"<br/><br/>" +
				"Regards,<br/>" +
				"Organise Team";
		return body;
	}
	
	String taskAssignedToGroup(String url,String assignToName, String assigner, String GroupName) {
		String body = "Dear "+assignToName+",<br/><br>" +
				"" +
				"A new task has been assigned to <b>"+GroupName+"</b> group in Organise by <b>" +assigner+"</b>. Please click on link below to view task<br/><br/>" +
				"URL: " +url+"<br/><br/>" +
				"Regards,<br/>" +
				"Organise Team";
		return body;
	}
	
	String taskReassignedEmailBody(String url, String taskUpdater, String taskTitle, String newTaskAssignee, String lastComments) {
		String body = "Dear "+newTaskAssignee+",<br/><br>" +
				"" +
				"You have been reassigned a task from <b>" +taskUpdater+"</b>. Please click on link below to view your task<br/><br/>" +
				"URL: " +url+"<br/>" +
				"Task Title: " +taskTitle+"<br/>" +
				"Comments: " +lastComments+"<br/><br/>" +
				"Regards,<br/>" +
				"Organise Team";
		return body;
	}
	
	String userInTaskBody(String url, String userName,String currentUser, String taskTitle, String lastComments) {
		String body = "Dear "+userName+",<br/><br>" +
				"" +
				currentUser+ " has commented on task you are involved. Please click on link below to view your task<br/><br/>" +
				"URL: " +url+"<br/>" +
				"Task Title: " +taskTitle+"<br/>" +
				"Comments: " +lastComments+"<br/><br/>" +
				"Regards,<br/>" +
				"Organise Team";
		return body;
	}
	
	
	String userInTaskCompletedBody(String url, String userName,String currentUser, String taskTitle, String lastComments) {
		String body = "Dear "+userName+",<br/><br>" +
				"" +
				currentUser+ " has completed the task you are involved. Please click on link below to view your task<br/><br/>" +
				"URL: " +url+"<br/>" +
				"Task Title: " +taskTitle+"<br/>" +
				"Comments: " +lastComments+"<br/><br/>" +
				"Regards,<br/>" +
				"Organise Team";
		return body;
	}
	
	
	String taskReassignedNotificationEmailBody(String url,String taskCreator, String originalAssignee, String reassignedTo, String taskTitle,  String lastComments) {
		String body = "Dear "+taskCreator+",<br/><br>" +
				"" +
				"One of the tasks you have created has been reassigned to <b>" +reassignedTo+"</b> for completion. Please click on link below to view your task<br/><br/>" +
				"URL: " +url+"<br/>" +
				"Task Title: " +taskTitle+"<br/>" +
				"Previous Assignee: " +originalAssignee+"<br/>" +
				"Comments: " +lastComments+"<br/><br/>" +
				"Regards,<br/>" +
				"Organise Team";
		return body;
	}
	
	/**
	 * @param url
	 * @return
	 */
	String taskUpdatedEmailBody(String url, String taskUpdater, String taskTitle, String taskAssigner) {
		String body = "Dear "+ taskAssigner +",<br/><br>" +
				"You have received a comment on a task you have assigned to "+taskUpdater+". Please click on the link below to view the task <br/><br/>" +
				"URL: " +url+"<br/>" +
				"Task Title: " + taskTitle +"<br/><br/>" +
				"Regards,<br/>" +
				"Organise Team";
		return body;
	}
	
	/**
	 * @param url
	 * @return
	 */
	String taskCompletedEmailBody(String url, String taskUpdater, String taskTitle, String taskAssigner, String lastComments) {
		String body = "Dear "+taskAssigner+",<br/><br>" +
				"The task you assigned to "+taskUpdater+" has been completed <br/><br/>" +
				"Task Title:"+ taskTitle +"<br/>" +
				"Comments:"+ lastComments +"<br/>" +
				"View done task:" +url+"<br/><br/>" +
				"Regards,<br/>" +
				"Organise Team";
		return body;
	}
	
	

	/**
	 * @param url
	 * @param email
	 * @param password
	 * @return
	 */
	String emailBodyForgotPassword(String url, String email, String password) {
		String body = "Dear User,<br/><br>" +
				"You have requested of your Organise password.<br/>" +
				"Your password to organise is shown below. Please login to Organise and change your password to something memorable.<br/><br/>" +
				"URL: " +url+"<br/>" +
				"Username: "+email+"<br/>" +
				"Password: "+password+"<br/>" +
				"<br/>Please note that all passwords are case-sensitive.<br/>" +
				"If you have any further questions, please contact your administrator.<br/>" +
				"<br/>" +
				"Regards,<br/>" +
				"Organise Team";
		return body;
	}


}
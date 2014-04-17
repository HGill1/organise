package com.jemmmedia.organise.service.impl.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jemmmedia.organise.mybatis.db.mybatis.model.AnnouncementExt;
import com.jemmmedia.organise.mybatis.db.mybatis.model.FeedEntryBean;
import com.jemmmedia.organise.mybatis.db.mybatis.model.FeedSourceExtBean;
import com.jemmmedia.organise.mybatis.db.mybatis.model.FeedTypes;
import com.jemmmedia.organise.mybatis.db.mybatis.model.Regions;
import com.jemmmedia.organise.mybatis.db.mybatis.model.User;
import com.jemmmedia.organise.mybatis.db.mybatis.model.UserDetailedBean;
import com.jemmmedia.organise.service.impl.exception.LoginException;
import com.jemmmedia.organise.service.impl.service.FeedTypesService;
import com.jemmmedia.organise.service.impl.service.RolesService;
import com.jemmmedia.organise.service.impl.utility.StringUtils;


//@RequestMapping("/")
@Controller
public class AdminController extends CommonController{

	@Resource(name="rolesService")
	RolesService rolesService;
	
	@Resource(name="feedTypesService")
	FeedTypesService feedTypesService;
	
	
	@RequestMapping("admin")
	public ModelAndView getAdminPage(Model model){	
		
		getLoginUserDetail(model);
		return new ModelAndView("admin");
	}
	
	
	
	@RequestMapping("manageSources")
	public ModelAndView manageSources(Model model){
		
		User user = userService.getUserDetailFromSpringSecurity();
		
		getUsersDetail(model, user);
		
		
		List<FeedSourceExtBean> feedSources =  feedSourceService.selectAllSources(user.getGroupId());
		
		return new ModelAndView("manageSources","feedSources", feedSources);
		
	}
	
	@RequestMapping("addSource")
	public ModelAndView addSource(Model model, @ModelAttribute("feedSource") FeedSourceExtBean feedSourceExtBean,
												@RequestParam("subTopicsName") String subTopicsName,
												@RequestParam(value = "checkedUserId", required = false) ArrayList<Long> checkedUserId){
		if(feedSourceExtBean.getName()!=null && !feedSourceExtBean.getName().trim().isEmpty()){
			
			Date dt = new Date();
			feedSourceExtBean.setCreatedAt(dt);
			feedSourceExtBean.setUpdatedAt(dt);
			feedSourceService.insertSelective(feedSourceExtBean);
			addcheckedUsers(feedSourceExtBean, checkedUserId);
			if (!subTopicsName.isEmpty() && subTopicsName != null) {
				addSubtopics(feedSourceExtBean, subTopicsName, checkedUserId);
			}
			
		}
		
		
		return manageSources(model);
	}


	/**
	 * @param feedSourceExtBean
	 * @param subTopicsName
	 * @param checkedUserId
	 */
	private void addSubtopics(FeedSourceExtBean feedSourceExtBean,
			String subTopicsName, ArrayList<Long> checkedUserId) {
		String[] subTopics = subTopicsName.split(",");
		long parentId = feedSourceExtBean.getId();

		for (String subTopic : subTopics) {
			feedSourceExtBean.setParentId(parentId);
			feedSourceExtBean.setName(subTopic);
			feedSourceService.insertSelective(feedSourceExtBean);
			addcheckedUsers(feedSourceExtBean, checkedUserId);
		}
	}



	/**
	 * @param feedSourceExtBean
	 * @param checkedUserId
	 */
	private void addcheckedUsers(FeedSourceExtBean feedSourceExtBean,
			ArrayList<Long> checkedUserId) {
		if(checkedUserId != null && !checkedUserId.isEmpty()){
			feedSourceService.insert(checkedUserId, feedSourceExtBean.getId());
		}
	}
	
	@RequestMapping("editSource")
	public ModelAndView editSource(Model model, @ModelAttribute("feedSource") FeedSourceExtBean feedSourceExtBean,
												@RequestParam("subTopicsName") String subTopicsName,
												@RequestParam(value = "checkedUserId", required = false) ArrayList<Long> checkedUserId){
		Date dt = new Date();
		feedSourceExtBean.setUpdatedAt(dt);
		feedSourceService.updateByPrimaryKeySelective(feedSourceExtBean);
		if (!subTopicsName.isEmpty() && subTopicsName != null) {
			//feedSourceService.deleteSubSource(feedSourceExtBean.getId());
			addSubtopics(feedSourceExtBean, subTopicsName, checkedUserId);
		}else{
			List<FeedSourceExtBean> subtopicsList = null;
			subtopicsList = feedSourceService.selectSubTopics(feedSourceExtBean.getId());
			try {
				for(FeedSourceExtBean subtopic : subtopicsList) {
					feedSourceService.deleteSourceUserEntry(subtopic.getId());
					addcheckedUsers(subtopic, checkedUserId);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		if(checkedUserId != null && !checkedUserId.isEmpty()){
			feedSourceService.deleteSourceUserEntry(feedSourceExtBean.getId());
			feedSourceService.insert(checkedUserId, feedSourceExtBean.getId());
		}
		return manageSources(model);
	}
	
	@RequestMapping("addSourcesForm")
	public ModelAndView addSourcesForm(Model model){
		User user = userService.getUserDetailFromSpringSecurity();
		
		getUsersDetail(model, user);
		List<String> usersList = null;
		model.addAttribute("usersNotSelected", userService.selectUnCheckedUsers(user.getGroupId(), usersList));
		model.addAttribute("formAction","addSource");
		model.addAttribute("formCaption","Add");
		//model.addAttribute("sourceTypeList", feedTypesService.selectByEample());
		
		FeedSourceExtBean feedSourceExtBean = new FeedSourceExtBean();
		
		return new ModelAndView("addSourcesForm","feedSource", feedSourceExtBean);
		
	}
	
	
	
	@RequestMapping("editSourcesForm")
	public ModelAndView editSourcesForm(Model model,@RequestParam("id") long id){
		User user = userService.getUserDetailFromSpringSecurity();
						
		getUsersDetail(model, user);
		
		//FeedSourceExtBean feedSourceExtBean;
		
		FeedSourceExtBean feedSourceExtBean = feedSourceService.selectFeedSourceForEdit(id);
		if(feedSourceExtBean.getParentId() != null)
		{
			List<FeedSourceExtBean> subtopicsList = feedSourceService.selectSubTopics(feedSourceExtBean.getParentId());
			model.addAttribute("subtopicsList",subtopicsList);
			
		}
		feedSourceExtBean.setSourceType(feedSourceExtBean.getSourceType());
		
		List<String> usersList = new ArrayList<String>(Arrays.asList(feedSourceExtBean.getUsersId().split(",")));
		
		    model.addAttribute("usersNotSelected", userService.selectUnCheckedUsers(user.getGroupId(), usersList));
			model.addAttribute("formCaption","Edit");
			model.addAttribute("formAction","editSource");
			//model.addAttribute("users",userService.selectUnCheckedUsers(usersId));
		return new ModelAndView("addSourcesForm","feedSource", feedSourceExtBean);
		
	}
	
	@RequestMapping("deleteSource")
	public ModelAndView deleteSource(Model model,@RequestParam("id") long id){
		
		feedSourceService.deleteSource(id);
		ModelAndView mv = new ModelAndView("redirect:manageSources");
		
		return mv;
		
	}
	
	@RequestMapping("deleteSubtopic")
	public void deleteSubtopic(Model model,@RequestParam("id") long id){
		
		feedSourceService.deleteSource(id);
		
	}
	
	@ModelAttribute("sourceTypeList")
	public Map<Byte,String> populateSourceType() {
		 List<FeedTypes> sourceList = feedTypesService.selectByEample();
		Map<Byte,String> sourceTypeList = new LinkedHashMap<Byte, String>();
		for (FeedTypes feedTypes : sourceList) {
			sourceTypeList.put(feedTypes.getId(), feedTypes.getName());
		}
		return sourceTypeList;
	}
	
	@ModelAttribute("regionsMap")
	public Map<Long,String> populateRegions() {
		UserDetailedBean user = (UserDetailedBean) userService.getUserDetailFromSpringSecurity();
		 List<Regions> regionsList = userService.selectAllRegions(user.getGroupId());
		Map<Long,String> regionsMap = new LinkedHashMap<Long, String>();
		for (Regions region : regionsList) {
			regionsMap.put(region.getId(), region.getRegionName());
		}
		 
		return regionsMap;
	}
	
	
	@RequestMapping("createUsers")
	public ModelAndView createUsers(Model model){
		User user = userService.getUserDetailFromSpringSecurity();
		
		User createUser = new User();
		
		getUsersDetail(model, user);
		model.addAttribute("usersCount", userService.countByGroup(user.getGroupId()));
		
		
		return new ModelAndView("createUsers","createUser", createUser);
		
	}
	
	@RequestMapping("deleteUser")
	@ResponseBody public int deleteUser(Model model, @RequestParam long id){
		User user = userService.getUserDetailFromSpringSecurity();
		
		//getUsersDetail(model, user);
		
		userService.deleteByPrimaryKey(id);
		
		model.addAttribute("usersCount", userService.countByGroup(user.getGroupId()));
		model.addAttribute("tabSelected", 5);
		
		return 1;
		
	}
	
	@RequestMapping("modifyUserDept")	
	public void modifyUserDept(@ModelAttribute("createUser") UserDetailedBean createUser){
		deptService.insertDeptsForUser(createUser.getDepts(), createUser.getId());
	}

	
	@RequestMapping("createNewUsers")
	public ModelAndView createNewUsers(@ModelAttribute("createUser") UserDetailedBean createUser, Model model, @RequestParam int tabSelected){
		
	//TODO: check if more users has already been created during trial period	
		UserDetailedBean user = (UserDetailedBean) userService.getUserDetailFromSpringSecurity();
		getUsersDetail(model, user);
		
		model.addAttribute("tabSelected", tabSelected);
		
		DateTime currentDateTime = new DateTime();
		
		UserDetailedBean userPackageInfo;
		try {
			Date dt =  user.getGroupCreationDate();
			
			DateTime trialEndDate =  new DateTime(dt).plusDays(trialPeriod);
			
		userPackageInfo = userService.getUserPackage(user.getGroupId());
		String packageError = "You cannot create users greater than allowed limit of your users.";
		
		if(user.getPackageType() == 1 && userPackageInfo.getUsersCount() > userPackageInfo.getAllowedUsers() && currentDateTime.compareTo(trialEndDate) > 0){
			model.addAttribute("packageError", packageError);	
			return createUsers(model);
		}else if(user.getPackageType() == 2 && userPackageInfo.getUsersCount() > userPackageInfo.getAllowedUsers()){
			model.addAttribute("packageError", packageError);
			return createUsers(model);
		}else if(user.getPackageType() == 3 && userPackageInfo.getUsersCount() > userPackageInfo.getAllowedUsers()){
			model.addAttribute("packageError", packageError);
			return createUsers(model);
		}else if(user.getPackageType() == 4 && userPackageInfo.getUsersCount() > userPackageInfo.getAllowedUsers()){
			model.addAttribute("packageError", packageError);
			return createUsers(model);
		}
		} catch (LoginException e) {
			e.printStackTrace();
		}
		createUser.setUserPassword(StringUtils.generateRandomPassword());
		saveUserDetail(createUser, model);
		return preferences(model,tabSelected,"");
		
	}
	
	@RequestMapping("preferences")
	public ModelAndView preferences(Model model, @RequestParam(value = "tabSelected",defaultValue = "2") int tabSelected,
												@RequestParam(value="announcementSuccessMsg",defaultValue = "") String announcementSuccessMsg) {
		UserDetailedBean user = (UserDetailedBean) userService.getUserDetailFromSpringSecurity();
		getUsersDetail(model, user);
	//	String folderSizeInMB = "0";
	//	String spacePercentage = "0";
		
		List<FeedSourceExtBean> feedSources =  feedSourceService.selectAllSources(user.getGroupId());
		
		UserDetailedBean createUser = new UserDetailedBean();
		AnnouncementExt announcement = new AnnouncementExt();
		model.addAttribute("tabSelected", tabSelected);
		model.addAttribute("feedSources", feedSources);
		/*model.addAttribute("folderSizeInMB", folderSizeInMB);
		model.addAttribute("spacePercentage", spacePercentage);
		model.addAttribute("packageEndDate", packageEndDate);
		model.addAttribute("groupCreationDate", groupCreationDate);
		model.addAttribute("usersCount", userPackageDetail.getUsersCount());
		model.addAttribute("tasksCompleted", feedEntryService.tasksCompleted(groupId));
		model.addAttribute("tasksOpen", feedEntryService.tasksOpen(groupId));
		model.addAttribute("tasksPending", feedEntryService.tasksPending(groupId));
		model.addAttribute("commentsCount", feedEntryService.commentsCount(groupId));*/
		model.addAttribute("createUser", createUser);
		//model.addAttribute("topicsCount", feedSourceService.topicsCount(groupId));
		model.addAttribute("announcement",announcement);
		model.addAttribute("announcementSuccessMsg",announcementSuccessMsg);
		//model.addAttribute("packageName", packageName);
		
	
		return new ModelAndView("preferences");
	}
	
	@RequestMapping("statsPage")
	public ModelAndView statsPage(Model model,
			@RequestParam(value="numberOfDays", defaultValue="7",required=false) int numberOfDays,
			@RequestParam(value="selectNumberOfDays",defaultValue="0", required=false) int selectNumberOfDays){
		User user = userService.getUserDetailFromSpringSecurity();
		
		
		 String[] strMonths = new String[]{
                 "Jan",
                 "Feb",
                 "Mar",
                 "Apr",
                 "May",
                 "Jun",
                 "Jul",
                 "Aug",
                 "Sep",
                 "Oct",
                 "Nov",
                 "Dec"
               };
		
		getUsersDetail(model, user);
		
		DateTime currentDate  = new DateTime();
		
		if(selectNumberOfDays > 0){
			numberOfDays = selectNumberOfDays;
		}
		
		
		DateTime startDate = currentDate.minusDays(numberOfDays);
		
		List<FeedEntryBean> tasksCreatedByUsersList =  feedEntryService.selectTaskCreatedByUsers(numberOfDays, user.getGroupId());
		Set<String> allNamesForTaskCreated =  getNames(tasksCreatedByUsersList);
	    HashMap<String, HashMap<String,Integer>> dataForTaskCreated = populateReportsData(numberOfDays, strMonths, startDate,
			tasksCreatedByUsersList, allNamesForTaskCreated,model,"created");
	    
	    List<FeedEntryBean> tasksCompletedByUsersList =  feedEntryService.selectTaskCompletedByUsers(numberOfDays, user.getGroupId());
		Set<String> allNamesForTaskCompleted =  getNames(tasksCompletedByUsersList);
	    HashMap<String, HashMap<String,Integer>> dataForTaskCompleted = populateReportsData(numberOfDays, strMonths, startDate,
	    		tasksCompletedByUsersList, allNamesForTaskCompleted,model,"completed");
	    
	    List<FeedEntryBean> tasksAssignedToUsersList =  feedEntryService.selectTaskAssignedToUsers(numberOfDays, user.getGroupId());
		Set<String> allNamesForTaskAssigned =  getNames(tasksAssignedToUsersList);
	    HashMap<String, HashMap<String,Integer>> dataForTaskAssigned = populateReportsData(numberOfDays, strMonths, startDate,
	    		tasksAssignedToUsersList, allNamesForTaskAssigned,model,"assigned");
	   
	   
	   List<String> dateWiseDaysDisplay = getDateRange(numberOfDays, strMonths,
			startDate);
	   
	   model.addAttribute("MonthName", currentDate.toString("MMM"));
	   
	   model.addAttribute("dateWiseDaysDisplay", dateWiseDaysDisplay);	   
		
		model.addAttribute("allNamesInsideList", allNamesForTaskCreated);
		
		model.addAttribute("tasksCreatedByUsersList", tasksCreatedByUsersList);	
		
		model.addAttribute("numberOfDays", numberOfDays);
		
		model.addAttribute("dataForTaskCreated", dataForTaskCreated);
		
		model.addAttribute("tasksCreatedSize", dataForTaskCreated.size());
		
		
		model.addAttribute("dataForTaskCompleted", dataForTaskCompleted);
		
		model.addAttribute("tasksCompletedSize", dataForTaskCompleted.size());
		
		
		model.addAttribute("dataForTaskAssigned", dataForTaskAssigned);
		
		model.addAttribute("tasksAssignedSize", dataForTaskAssigned.size());
		
		return new ModelAndView("stats");
		
	}



	/**
	 * @param numberOfDays
	 * @param strMonths
	 * @param startDate
	 * @return
	 */
	private List<String> getDateRange(int numberOfDays, String[] strMonths,
			DateTime startDate) {
		List<String> dateWiseDaysDisplay  = new ArrayList<>();
		   
		   for(int i=1 ; i<=numberOfDays;i++){
			   
			   DateTime dt = startDate.plusDays(i);
			   
			   String strDayMonth = strMonths[dt.getMonthOfYear()-1]+'-'+dt.getDayOfMonth();
			   
			   dateWiseDaysDisplay.add(strDayMonth);
		   }
		return dateWiseDaysDisplay;
	}



	/**
	 * @param tasksCreatedByUsersList
	 * @param allNamesForTaskCreated
	 * @return 
	 */
	private Set<String> getNames(List<FeedEntryBean> tasksCreatedByUsersList) {
		
		Set<String> allNamesForTaskCreated =  new HashSet<>();
		for (FeedEntryBean feedEntryBean : tasksCreatedByUsersList) {
			
			if(feedEntryBean.getFirstName()!= null){			
			   allNamesForTaskCreated.add(feedEntryBean.getFirstName().trim()+" "+feedEntryBean.getLastName().trim());
			}else{
				allNamesForTaskCreated.add("No name");
			}
		    }
		return allNamesForTaskCreated;
	}



	/**
	 * @param numberOfDays
	 * @param strMonths
	 * @param startDate
	 * @param tasksCreatedByUsersList
	 * @param allNamesInsideList
	 * @param listOfRecords
	 * @return 
	 */
	private HashMap<String, HashMap<String, Integer>> populateReportsData(int numberOfDays, String[] strMonths,
			DateTime startDate, List<FeedEntryBean> tasksCreatedByUsersList,
			Set<String> allNamesInsideList,Model model, String strTotal) {
		
		final HashMap<String, HashMap<String,Integer>> listOfRecords =  new HashMap<>();
		
		//final HashMap<String, HashMap<String,Integer>> map =  new HashMap<>();
		int rowTotal;
		int total = 0;
		
		
		
		
		for (String name : allNamesInsideList) {
			   
			   LinkedHashMap<String,Integer> dateWiseDays  = new LinkedHashMap<>();
			   rowTotal = 0;
			   
			   for(int i=1 ; i<=numberOfDays;i++){
				   
				   DateTime dt = startDate.plusDays(i);
				   
				   String strDayMonth = strMonths[dt.getMonthOfYear()-1]+'-'+dt.getDayOfMonth();
				   
				   dateWiseDays.put(strDayMonth, 0);
			   }
			   dateWiseDays.put("Total", 0);
			   
			   for(FeedEntryBean feedEntryBean : tasksCreatedByUsersList){
				   
				   if(name.trim().equalsIgnoreCase(feedEntryBean.getFirstName().trim()+" "+ feedEntryBean.getLastName().trim())){
					   Date taskDate;
					   if(strTotal.equals("completed")){
						    taskDate = feedEntryBean.getUpdatedAt();
					   }else{
						    taskDate = feedEntryBean.getCreatedAt();
					   }
					   
					DateTime dateHasTasks = new DateTime(taskDate);
					   
					   String strDayMonthWithValue = strMonths[dateHasTasks.getMonthOfYear()-1]+'-'+dateHasTasks.getDayOfMonth();
					   dateWiseDays.put(strDayMonthWithValue , feedEntryBean.getNumberOfTasks());
					   rowTotal +=feedEntryBean.getNumberOfTasks();
							  
				   }	   
				   		
					
			   }
			   dateWiseDays.put("Total", rowTotal);
			   total += rowTotal;
			   
			   
			   listOfRecords.put(name, dateWiseDays);
			
		   }
		model.addAttribute("total"+strTotal, total); 
		
		
		return sortList(listOfRecords);
	}



	/**
	 * @param listOfRecords
	 * @return
	 */
	private HashMap<String, HashMap<String, Integer>> sortList(
			final HashMap<String, HashMap<String, Integer>> listOfRecords) {
		List<String> SortedNamelist = new ArrayList<String>(listOfRecords.keySet());
		
				
		Collections.sort(SortedNamelist, new Comparator<String>() {
	        @Override
	        public int compare(String k1, String k2) {
	            int v1 = numberOfTasks(listOfRecords.get(k1));
	            int v2 = numberOfTasks(listOfRecords.get(k2));
	            return Integer.valueOf(v1).compareTo(Integer.valueOf(v2));
	        }
	    });
		
		final LinkedHashMap<String, HashMap<String,Integer>> sortedMap =  new LinkedHashMap<>();
		
		for (String name : SortedNamelist) {
			
			sortedMap.put(name, listOfRecords.get(name));
			
		}

		
		return sortedMap;
	}
	
	public static int numberOfTasks(HashMap<String, Integer> map) {
	    int max = 0;
	    for (Integer i : map.values()) {
	        if (i > max) max = i;
	    }
	    return max;
	}
}

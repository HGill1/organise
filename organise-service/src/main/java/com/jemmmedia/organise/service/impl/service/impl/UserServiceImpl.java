/**
 * 
 */
package com.jemmmedia.organise.service.impl.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jemm.imageutil.Image;
import com.jemm.imageutil.ImageLoader;
import com.jemm.imageutil.ImageType;
import com.jemmmedia.organise.mybatis.db.mybatis.model.Groups;
import com.jemmmedia.organise.mybatis.db.mybatis.model.Regions;
import com.jemmmedia.organise.mybatis.db.mybatis.model.RolesUsers;
import com.jemmmedia.organise.mybatis.db.mybatis.model.User;
import com.jemmmedia.organise.mybatis.db.mybatis.model.UserDetailedBean;
import com.jemmmedia.organise.service.impl.dao.UserDao;
import com.jemmmedia.organise.service.impl.exception.LoginException;
import com.jemmmedia.organise.service.impl.service.DeptService;
import com.jemmmedia.organise.service.impl.service.FeedSourceService;
import com.jemmmedia.organise.service.impl.service.GroupsService;
import com.jemmmedia.organise.service.impl.service.RegionsService;
import com.jemmmedia.organise.service.impl.service.RolesService;
import com.jemmmedia.organise.service.impl.service.UserService;
import com.jemmmedia.organise.service.impl.service.UsersLoggedInStatusService;
import com.jemmmedia.organise.service.impl.service.impl.CommonService;
import com.jemmmedia.organise.service.impl.utility.CryptoUtils;

/**
 * @author harjinder
 *
 */
@Service("userService")
public class UserServiceImpl extends CommonService implements UserService {

	//private final static String FILEPATH = "/home/onit/data/onit/profileImages/";
	//private final static String FILEPATH = "/home/onit/data/onit/";
	
	@Resource(name="userDao")
	private UserDao userDao;
	
	@Resource(name="groupsService")
	GroupsService groupsService;
	
	@Resource(name="regionsService")
	RegionsService regionsService;
	
	@Resource(name = "rolesService")
	RolesService rolesService;
	
	@Resource
	FeedSourceService feedSourceService;
	
	@Resource
	DeptService deptService;
	
	@Autowired
	UsersLoggedInStatusService usersLoggedInStatusService; 
	
	static final Logger logger = Logger
			.getLogger(UserServiceImpl.class);
	
	
	/* (non-Javadoc)
	 * @see com.jemmmedia.organise.service.impl.service.UserService#doLogin(java.lang.String, java.lang.String)
	 */
	@Override
	public User doLogin(String username, String password) throws LoginException {
		User user = userDao.getUserByName(username);
		if (user == null) {
			throw new LoginException("Invalid login!. Please check login details" +username);
		}
		return user;
	}

	/* (non-Javadoc)
	 * @see com.jemmmedia.organise.service.impl.service.UserService#checkPassword(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean checkPassword(String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.jemmmedia.organise.service.impl.service.UserService#getUserByName(java.lang.String)
	 */
	@Override
	public UserDetailedBean getUserByName(String username) throws LoginException {
		UserDetailedBean user = userDao.getUserByName(username);
		if (user == null) {
			throw new LoginException("Invalid login!. Please check login details" +username);
		}
		return user;
	}
	
	@Override
	public UserDetailedBean getUserPackage(long groupId) throws LoginException {
		UserDetailedBean user = null;
		try {
			user = userDao.getUserPackage(groupId);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return user;
	}
	
	@Override
	public User selectByPrimaryKey(int id) throws LoginException {
		User user = userDao.selectByPrimaryKey(id);
		if (user == null) {
			throw new LoginException("Not a valid user Id.");
		}
		return user;
	}
	
	@Override
	public User getUserDetailFromSpringSecurity() {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();	
		Object principal = auth.getPrincipal();
		//	principal.getClass();
			if(principal instanceof User){
				User user = (User) principal;
				//usersLoggedInStatusService.setOnlineUsers(user.getId(), user.getUsername());
				//usersLoggedInStatusService.setIsNewUserLoggededIn(1);
				return user;
			}
			return null;
	}

	@Override
	public List<User> getAllUsers(long groupId, List<Long> ids) {
		return userDao.getAllUsers(groupId,ids);
	}
	
	@Override
	public List<UserDetailedBean> selectAllUsersDetail(long groupId, List<Long> ids) {
		
		List<UserDetailedBean> usersDetailList = null ;
		
		try {
			usersDetailList = userDao.selectAllUsersDetail(groupId, ids);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return usersDetailList;
	}
	
	@Override
	public List<UserDetailedBean> filterUsers(long groupId, String searchStr) {
		
		List<UserDetailedBean> usersDetailList = null ;
		
		try {
			usersDetailList = userDao.filterUsers(groupId, searchStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return usersDetailList;
	}
	
	@Override
	public int updateByPrimaryKeySelective(User userInfo) {
		int updatedRecord = 0;
		 try {
			 updatedRecord = userDao.updateByPrimaryKeySelective(userInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return updatedRecord;
	}
	
	/**
	 * @param file
	 * @return
	 * @throws IllegalStateException
	 */
	@Override
	public File uploadProfileImageService(MultipartFile file)
			throws IllegalStateException {
		File dest = null;
		String fileName;
		UserDetailedBean userInfo = (UserDetailedBean) getUserDetailFromSpringSecurity();
		
		
		Image image;
		try {
			String orignalFileName = file.getOriginalFilename();
			 int mid= orignalFileName.lastIndexOf(".");
			String ext=orignalFileName.substring(mid+1,orignalFileName.length());
			String fileNameForDB = userInfo.getId()+"."+ext;
			fileName = filesPath + userInfo.getGroupId() +"/profileImages/" + fileNameForDB;
			
			ImageType type = ImageType.getType(ext);
			
			if(type.name().equals("UNKNOWN")){
				return null;
			}
			
			dest = new File(fileName);
			file.transferTo(dest);
			image = ImageLoader.fromFile(dest);
			if(image.getWidth()> 56 && image.getHeight()>56){
				//image.getResizedToSquare(56, 0.1).soften(0.0f).writeToJPG(dest, 0.95f);
				image.getResizedToSquare(100, 0.1).soften(0.0f).writeToFile(dest);
			}
		//	file.transferTo(dest);
			userInfo.setProfileimage(fileNameForDB);
			updateByPrimaryKeySelective(userInfo);
			
			
		} catch (IOException e) {
			e.printStackTrace();
			dest.delete();
			return null;
		}

		return dest;
	}
	
	/**
	 * @param id
	 * @param oldPassword
	 * @param password
	 * @return
	 * @throws LoginException
	 */
	@Override
	public int updatePassword(int id, String oldPassword, String password)
			throws LoginException {
		String saltPassword = null;
		String databasePassword = null;

		User user = selectByPrimaryKey(id);

		saltPassword = user.getPasswordSalt();
		databasePassword = user.getCryptedPassword();
		
		
		String enteredPassword = CustomEnocdePassword.getEnteredPassword(oldPassword, saltPassword);

		if (!StringUtils.equals(databasePassword, enteredPassword))
			return 0;
		
		

		String salt = CryptoUtils.friendlyToken();
		try {
			user.setCryptedPassword(CryptoUtils.encrypt(password + salt));
			user.setPasswordSalt(salt);
			user.setPersistenceToken(CryptoUtils.hexToken());
		} catch (NoSuchAlgorithmException | NoSuchProviderException
				| UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return updateByPrimaryKeySelective(user);
	}
	
	@Override
	public void insertSelective(UserDetailedBean user) {
		Date createdDate = new Date();
		try {
			if(user.getGroupName() != null ){
				
				Groups group = new Groups();
				group.setName(user.getGroupName());
				group.setCreatedAt(createdDate);
				group.setUpdatedAt(createdDate);
				groupsService.insertSelective(group);
				user.setGroupId(group.getId());
				
				Regions region =  new Regions();
				region.setRegionName(user.getRegionName());
				region.setGroupId(group.getId());
				regionsService.insertSelective(region);
				user.setRegionId(region.getId());				
				
				userDao.insertSelective(user);
				setUserRole(user.getId(), createdDate, createdDate, 2);
				
				File profileImages = new File(filesPath +user.getGroupId()+"/profileImages/");				
				if (profileImages.mkdirs()) {
					logger.debug("profileImages directories are created!");
				} else {
					logger.error("Failed to create profileImages directories!");
				}
				
				File attachments = new File(filesPath +user.getGroupId()+"/attachments/");				
				if (attachments.mkdirs()) {
					logger.debug("attachments directories are created!");
				} else {
					logger.error("Failed to create attachments directories!");
				}
			
				
			}else{
				userDao.insertSelective(user);
				setUserRole(user.getId(), createdDate, createdDate, user.getRoleId());
				if(user.getTopics()!=null && !user.getTopics().isEmpty()){
					feedSourceService.insertTopicsForUser(user.getTopics(), user.getId());
				}
				if(user.getDepts()!= null && !user.getDepts().isEmpty()){
					deptService.insertDeptsForUser(user.getDepts(), user.getId());
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**

	 */
	private void setUserRole(long userId, Date createdDate, Date updatedDate, int roleId) {
		RolesUsers userRole = new RolesUsers();
		userRole.setRoleId(roleId);
		userRole.setUserId(userId);
		if(createdDate != null)
			userRole.setCreatedAt(createdDate);
		
		userRole.setUpdatedAt(updatedDate);
		rolesService.insertOrUpdateSelective(userRole);
	}
	
	@Override	
	public void enableDisableUser(long userId, Date createdDate, Date updatedDate, int roleId){
		setUserRole(userId, createdDate, createdDate, roleId);
	}
	
	@Override
	public List<Regions> selectAllRegions(long groupId) {
		return userDao.selectAllRegions(groupId);
	}
	
	@Override
	public List<User> selectCheckedUsers(String usersId) {
		 List<User> usersList = null;
		 try {
			 usersList = userDao.selectCheckedUsers(usersId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usersList;
	}
	
	@Override
	public List<User> selectUnCheckedUsers(long groupId, List<String> usersId) {
		 List<User> usersList = null;
		 try {
			 usersList = userDao.selectUnCheckedUsers(groupId, usersId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usersList;
	}
	
	@Override
	public int countByGroup(long groupId) {
		return userDao.countByGroup(groupId);
	}
	
	@Override
	public int checkUserExist(String email) {
		int count = 0;
		 try {
			 count = userDao.checkUserExist(email);
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return count;
	}
	
	@Override
	public int selectUsersInRegion(long regionId) {
		int count = 0;
		 try {
			 count = userDao.selectUsersInRegion(regionId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return count;
	}
	
	
	
	@Override
	public int deleteByPrimaryKey(long id) {
		int count = 0;
		 try {
			 count = userDao.deleteByPrimaryKey(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return count;
	}
	
	@Override
	public double getGroupFolderSize(){
		UserDetailedBean userInfo = (UserDetailedBean) getUserDetailFromSpringSecurity();
		String groupFolderPath = filesPath + userInfo.getGroupId();				
		double size = FileUtils.sizeOfDirectory(new File(groupFolderPath));
		double folderSize = size/1000000000;
		return folderSize;
		
	}
	
	@Override
	public void taskAssignedEmail(boolean taskAddEmail, long id) {
		try {
			userDao.taskAssignedEmail(taskAddEmail, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void postTaskEmail(boolean taskUpdateEmail, long id) {
		try {
			userDao.postTaskEmail(taskUpdateEmail, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String selectSuperUersEmail(Long groupId) {
		String emailIds = null;
		try {
			emailIds = userDao.selectSuperUersEmail(groupId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return emailIds;
	}
	
	@Override
	public List<User> selectUsersInTask(long id,List<Long> ids){			
		List<User> list = null;
		
		 try {
			 list = userDao.selectUsersInTask(id,ids);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}

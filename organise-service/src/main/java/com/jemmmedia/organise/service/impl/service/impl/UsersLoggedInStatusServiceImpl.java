/**
 * 
 */
package com.jemmmedia.organise.service.impl.service.impl;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.jemmmedia.organise.service.impl.service.UsersLoggedInStatusService;

/**
 * @author harjinder
 * 
 */
@Service("usersLoggedInStatusService")
public class UsersLoggedInStatusServiceImpl implements UsersLoggedInStatusService {
	
	private Map<Long, String> OnlineUsers = new LinkedHashMap<Long, String>();
	
	private int isNewUserLoggededIn = 0; 

	/**
	 * @return the onlineUsers
	 */
	@Override
	public Map<Long, String> getOnlineUsers() {
		return OnlineUsers;
	}

	/**
	 * @param onlineUsers the onlineUsers to set
	 */
	@Override
	public void setOnlineUsers(Long id, String username) {
		OnlineUsers.put(id, username);
	}

	/**
	 * @return the isNewUserLoggededIn
	 */
	@Override
	public int getIsNewUserLoggededIn() {
		return isNewUserLoggededIn;
	}

	/**
	 * @param isNewUserLoggededIn the isNewUserLoggededIn to set
	 */
	@Override
	public void setIsNewUserLoggededIn(int isNewUserLoggededIn) {
		this.isNewUserLoggededIn = isNewUserLoggededIn;
	}
	
	@Override
	public void removeLoggedOutUser(Long id){
		if(!OnlineUsers.isEmpty() && OnlineUsers.size()>0)
			OnlineUsers.remove(id);
	}
	

}

/**
 * 
 */
package com.jemmmedia.organise.service.impl.service;

import java.util.Map;

/**
 * @author harjinder
 *
 */
public interface UsersLoggedInStatusService {

	Map<Long, String> getOnlineUsers();

	void setOnlineUsers(Long id, String username);

	int getIsNewUserLoggededIn();

	void setIsNewUserLoggededIn(int isNewUserLoggededIn);

	void removeLoggedOutUser(Long id);
	
}

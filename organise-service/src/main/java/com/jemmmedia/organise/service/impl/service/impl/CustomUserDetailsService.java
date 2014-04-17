/**
 * 
 */
package com.jemmmedia.organise.service.impl.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jemmmedia.organise.mybatis.db.mybatis.model.UserDetailedBean;
import com.jemmmedia.organise.service.impl.exception.LoginException;
import com.jemmmedia.organise.service.impl.exception.TrialPeriodException;
import com.jemmmedia.organise.service.impl.service.UserService;


/**
 * @author harjinder
 *
 */
@Service
@Transactional(readOnly = true)
public class CustomUserDetailsService extends CommonService implements UserDetailsService {
	
	static final Logger logger = Logger.getLogger(CustomUserDetailsService.class);
	@Value("${trial.period}")
	protected int trialPeriod;
	
	@Value("${subscription.period}")
	protected int subscriptionPeriod;
	
	@Resource(name="userService")
	private UserService userService;

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String email)
			throws UsernameNotFoundException, DataAccessException {
		UserDetailedBean domainUser = null;
		
		/*boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;*/
			try {
				
			//TODO	
				
				
				
				domainUser = userService.getUserByName(email);		
				
				//userService.getUserDetailFromSpringSecurity();
				
				Date dt =  domainUser.getGroupCreationDate();
				
				DateTime groupCreated =  new DateTime(dt).plusDays(trialPeriod);
				
				DateTime currentDateTime = new DateTime();
				
				if(domainUser.getSubscrDate() !=null){
					Date sdt =  domainUser.getSubscrDate();
					
					DateTime subscrEndDate =  new DateTime(sdt).plusDays(subscriptionPeriod);
					
					if(currentDateTime.compareTo(subscrEndDate) > 0){
						throw new TrialPeriodException("Subscription period expire for."+domainUser.getGroupName());
					}
					String groupFolderPath = filesPath + domainUser.getGroupId();				
					double size = FileUtils.sizeOfDirectory(new File(groupFolderPath));
					double folderSize = size/1000000000;
					
					if(domainUser.getAllowedStorage() != 0){
						if(folderSize > domainUser.getAllowedStorage()){
							throw new TrialPeriodException("Exceeding storage allocation."+domainUser.getGroupName());
						}
					}
					
				}
				//System.out.println("groupCreated 1 --"+groupCreated +": package Type1 --"+domainUser.getPackageType() +":  comapre should be 1 " + currentDateTime.compareTo(groupCreated));
				if(currentDateTime.compareTo(groupCreated) > 0 && domainUser.getPackageType() == 1 && domainUser.getUsersCount() > 2){
					throw new TrialPeriodException("Trial period expire for."+domainUser.getGroupName());
				}
				
				domainUser.isEnabled();
				domainUser.isAccountNonExpired();
				domainUser.isCredentialsNonExpired();
				domainUser.isAccountNonLocked();
				
		  
		//Collection<? extends GrantedAuthority> roles =  getAuthorities((long) domainUser.getRoleId());

		
		
		} catch (AccountStatusException e) {
			logger.error("Invalid Login. "+email,e);
			throw new RuntimeException(e);
		} catch (TrialPeriodException e) {
			e.printStackTrace();
		} /*catch (UsernameNotFoundException e) {
			throw new UsernameNotFoundException("Username not found. Please try again.");
		}*/ catch (LoginException e) {
			throw new UsernameNotFoundException("Username not found. Please try again.");
		}
			
			return domainUser;
	}
	
	/**
	 * Retrieves a collection of {@link GrantedAuthority} based on a numerical role
	 * @param role the numerical role
	 * @return a collection of {@link GrantedAuthority
	 */
	public static final Collection<? extends GrantedAuthority> getAuthorities(Long role) {
		List<GrantedAuthority> authList = getGrantedAuthorities(getRoles(role));
		return authList;
	}


	/**
	 * Converts a numerical role to an equivalent list of roles
	 * @param role the numerical role
	 * @return list of roles as as a list of {@link String}
	 */
	public static List<String> getRoles(Long role) {
		List<String> roles = new ArrayList<String>();
		
		if (role.intValue() == 1) {
			roles.add("ROLE_USER");
			roles.add("ROLE_ADMIN");
			roles.add("ROLE_SJADMIN");			
		} else if (role.intValue() == 2) {
			roles.add("ROLE_USER");
			roles.add("ROLE_ADMIN");
		}else if (role.intValue() == 3) {
			roles.add("ROLE_USER");
			roles.add("ROLE_ADMIN");
		}else{
			roles.add("ROLE_USER");
		}
		
		return roles;
	}
	
	/**
	 * Wraps {@link String} roles to {@link SimpleGrantedAuthority} objects
	 * @param roles {@link String} of roles
	 * @return list of granted authorities
	 */
	public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (String role : roles) {
			authorities.add(new SimpleGrantedAuthority(role));
		}
		return authorities;
	}

}

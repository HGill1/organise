package com.jemmmedia.organise.mybatis.db.mybatis.model;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.jemmmedia.organise.mybatis.db.mybatis.model.Dept;
import com.jemmmedia.organise.mybatis.db.mybatis.model.User;
//TODO
//import com.jemmmedia.organise.service.impl.service.impl.CustomUserDetailsService;

public class UserDetailedBean extends User  implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4943755944561894268L;
	private String regionName;
	private int roleId;
	private String roleName;
	private String groupName;
	private String userPassword;
	private Date groupCreationDate;
	private long packageType;
	private int usersCount;
	private int topicsCount;
	private Date subscrDate;
	private int allowedUsers;
	private int allowedStorage;
	private  List<Long> depts; 
	private  List<Long> topics;
	private List<Dept> dept;
	/**
	 * @return the regionName
	 */
	public String getRegionName() {
		return regionName;
	}

	/**
	 * @return the topics
	 */
	public List<Long> getTopics() {
		return topics;
	}

	/**
	 * @param topics the topics to set
	 */
	public void setTopics(List<Long> topics) {
		this.topics = topics;
	}

	/**
	 * @param regionName the regionName to set
	 */
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	/**
	 * @return the roleId
	 */
	public int getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	/**
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * @param roleName the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		//List<GrantedAuthority> authList = (List<GrantedAuthority>) CustomUserDetailsService.getAuthorities((long)(1));
		///return (Collection<GrantedAuthority>) CustomUserDetailsService.getAuthorities((long) getRoleId());
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	//this method needs to override to for remember me functionality from spring
	public String getUsername() {
		return this.getEmail();
	}

	/**
	 * @return the groupName
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * @param groupName the groupName to set
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
	}

	/**
	 * @return the userPassword
	 */
	public String getUserPassword() {
		return userPassword;
	}

	/**
	 * @param userPassword the userPassword to set
	 */
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	/**
	 * @return the groupCreationDate
	 */
	public Date getGroupCreationDate() {
		return groupCreationDate;
	}

	/**
	 * @param groupCreationDate the groupCreationDate to set
	 */
	public void setGroupCreationDate(Date groupCreationDate) {
		this.groupCreationDate = groupCreationDate;
	}

	/**
	 * @return the packageType
	 */
	public long getPackageType() {
		return packageType;
	}

	/**
	 * @param packageType the packageType to set
	 */
	public void setPackageType(long packageType) {
		this.packageType = packageType;
	}

	/**
	 * @return the usersCount
	 */
	public int getUsersCount() {
		return usersCount;
	}

	/**
	 * @param usersCount the usersCount to set
	 */
	public void setUsersCount(int usersCount) {
		this.usersCount = usersCount;
	}

	/**
	 * @return the subscrDate
	 */
	public Date getSubscrDate() {
		return subscrDate;
	}

	/**
	 * @param subscrDate the subscrDate to set
	 */
	public void setSubscrDate(Date subscrDate) {
		this.subscrDate = subscrDate;
	}

	/**
	 * @return the allowedUsers
	 */
	public int getAllowedUsers() {
		return allowedUsers;
	}

	/**
	 * @param allowedUsers the allowedUsers to set
	 */
	public void setAllowedUsers(int allowedUsers) {
		this.allowedUsers = allowedUsers;
	}

	/**
	 * @return the allowedStorage
	 */
	public int getAllowedStorage() {
		return allowedStorage;
	}

	/**
	 * @param allowedStorage the allowedStorage to set
	 */
	public void setAllowedStorage(int allowedStorage) {
		this.allowedStorage = allowedStorage;
	}

	/**
	 * @return the topicsCount
	 */
	public int getTopicsCount() {
		return topicsCount;
	}

	/**
	 * @param topicsCount the topicsCount to set
	 */
	public void setTopicsCount(int topicsCount) {
		this.topicsCount = topicsCount;
	}

	/**
	 * @return the depts
	 */
	public List<Long> getDepts() {
		return depts;
	}

	/**
	 * @param depts the depts to set
	 */
	public void setDepts(List<Long> depts) {
		this.depts = depts;
	}

	/**
	 * @return the dept
	 */
	public List<Dept> getDept() {
		return dept;
	}

	/**
	 * @param dept the dept to set
	 */
	public void setDept(List<Dept> dept) {
		this.dept = dept;
	}

	
}

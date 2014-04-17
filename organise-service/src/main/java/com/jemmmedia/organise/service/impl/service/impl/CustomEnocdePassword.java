/**
 * 
 */
package com.jemmmedia.organise.service.impl.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.encoding.PasswordEncoder;

import com.jemmmedia.organise.mybatis.db.mybatis.model.User;
import com.jemmmedia.organise.service.impl.exception.LoginException;
import com.jemmmedia.organise.service.impl.service.UserService;
import com.jemmmedia.organise.service.impl.utility.CryptoUtils;

/**
 * @author harjinder
 *
 */
public class CustomEnocdePassword implements PasswordEncoder {
	
	@Resource(name="userService")
	private UserService userService;

	/* (non-Javadoc)
	 * @see org.springframework.security.authentication.encoding.PasswordEncoder#encodePassword(java.lang.String, java.lang.Object)
	 */
	@Override
	public String encodePassword(String password, Object username)
			throws DataAccessException {
		if (StringUtils.isBlank(password)) {
			throw new IllegalArgumentException("Password cannot be empty");
		}
		
		if(username.equals(null)){
			throw new IllegalArgumentException("Username cannot be null");
		}
		
		String saltPassword = getSaltPassword(username);
		
		
		String enteredPassword = getEnteredPassword(password, saltPassword);
		return enteredPassword;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.authentication.encoding.PasswordEncoder#isPasswordValid(java.lang.String, java.lang.String, java.lang.Object)
	 */
	@Override
	public boolean isPasswordValid(String encPassword, String password, Object username)
			throws DataAccessException {
		if (StringUtils.isBlank(password)) {
			return false;
		}
		
		String saltPassword = null;
		String databasePassword = null;
		
		try {
			User domainUser = userService.getUserByName(username.toString());
			 saltPassword = domainUser.getPasswordSalt();
			 databasePassword = domainUser.getCryptedPassword();
		} catch (LoginException e1) {
			e1.printStackTrace();
		}
		
		
		String enteredPassword = getEnteredPassword(password, saltPassword);
		
		
		 return StringUtils.equals(databasePassword, enteredPassword);
		//return StringUtils.equals(databasePassword, password);
	}

	/**
	 * @param password
	 * @param saltPassword
	 * @return
	 */
	public static String getEnteredPassword(String password, String saltPassword) {
		String enteredPassword = null;
		
			try {
				enteredPassword = CryptoUtils.encrypt(password + saltPassword);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (NoSuchProviderException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		return enteredPassword;
	}

	/**
	 * @param username
	 * @return
	 */
	private String getSaltPassword(Object username) {
		String saltPassword = null;
		try {
			User domainUser = userService.getUserByName(username.toString());
			 saltPassword = domainUser.getPasswordSalt();
		} catch (LoginException e1) {
			e1.printStackTrace();
		}
		return saltPassword;
	}

}

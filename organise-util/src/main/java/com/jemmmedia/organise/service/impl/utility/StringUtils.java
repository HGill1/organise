/**
 * 
 */
package com.jemmmedia.organise.service.impl.utility;

import java.security.SecureRandom;
import java.util.Random;

/**
 * @author harjinder
 *
 */
public class StringUtils {
 
	 private static final Random RANDOM = new SecureRandom();
	  public static final int PASSWORD_LENGTH = 8;
	  
	  public static String generateRandomPassword()
	  {
	      String letters = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ23456789+@";

	      String pw = "";
	      for (int i=0; i<PASSWORD_LENGTH; i++)
	      {
	          int index = (int)(RANDOM.nextDouble()*letters.length());
	          pw += letters.substring(index, index+1);
	      }
	      return pw;
	  }
	
}

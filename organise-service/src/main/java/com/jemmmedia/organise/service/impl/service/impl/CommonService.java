/**
 * 
 */
package com.jemmmedia.organise.service.impl.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author harjinder
 *
 */
@Component
public class CommonService {

	
	@Value("${files.path}") 
	
	protected String filesPath ;
	
	//protected final static String FILEPATH = "/home/onit/data/onit/";
	//protected final static String FILEPATH = "/home/onit/mnt/nfs/data--test-www.organise.net/";

}

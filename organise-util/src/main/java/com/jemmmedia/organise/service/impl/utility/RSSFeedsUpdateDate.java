/**
 * 
 */
package com.jemmmedia.organise.service.impl.utility;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * @author harjinder
 *
 */
@Service("rSSFeedsUpdateDate")
public class RSSFeedsUpdateDate {	
	public Map<Long,Date>RSSFeedDate = new HashMap<Long, Date>();
}

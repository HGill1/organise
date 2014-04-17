/**
 * 
 */
package com.jemmmedia.organise.service.impl.service;

import java.util.Map;

import com.jemmmedia.organise.mybatis.db.mybatis.model.Status;

/**
 * @author harjinder
 *
 */
public interface StatusService {
	Map<Integer, Status> getAllStatus();
	
}

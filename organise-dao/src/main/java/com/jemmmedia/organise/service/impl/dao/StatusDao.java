/**
 * 
 */
package com.jemmmedia.organise.service.impl.dao;

import java.util.Map;

import com.jemmmedia.organise.mybatis.db.mybatis.model.Status;

/**
 * @author harjinder
 *
 */
public interface StatusDao {
	public Map<Integer, Status> getAllStatus();
}

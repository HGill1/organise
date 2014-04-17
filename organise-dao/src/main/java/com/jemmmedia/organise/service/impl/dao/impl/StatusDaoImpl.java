/**
 * 
 */
package com.jemmmedia.organise.service.impl.dao.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jemmmedia.organise.mybatis.db.mybatis.dao.StatusMapper;
import com.jemmmedia.organise.mybatis.db.mybatis.model.Status;
import com.jemmmedia.organise.service.impl.dao.StatusDao;

/**
 * @author harjinder
 *
 */
@Service("statusDao")
public class StatusDaoImpl implements StatusDao {
	
	@Resource
	StatusMapper statusMapper;

	/* (non-Javadoc)
	 * @see com.jemmmedia.organise.service.impl.dao.StatusDao#getAllStatus()
	 */
	@Override
	public Map<Integer, Status> getAllStatus() {
		return statusMapper.getAllStatus();
	}

}

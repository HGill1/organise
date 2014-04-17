/**
 * 
 */
package com.jemmmedia.organise.service.impl.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jemmmedia.organise.mybatis.db.mybatis.model.Status;
import com.jemmmedia.organise.service.impl.dao.StatusDao;
import com.jemmmedia.organise.service.impl.service.StatusService;

/**
 * @author harjinder
 * 
 */
@Service("statusService")
public class StatusServiceImpl implements StatusService {

	@Resource(name = "statusDao")
	StatusDao statusDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jemmmedia.organise.service.impl.service.StatusService#getAllStatus()
	 */
	@Override
	public Map<Integer, Status> getAllStatus() {
		Map<Integer, Status> allStatus = statusDao.getAllStatus();

		return allStatus;
	}

}

/**
 * 
 */
package com.jemmmedia.organise.service.impl.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jemmmedia.organise.mybatis.db.mybatis.model.FeedTypes;
import com.jemmmedia.organise.service.impl.dao.FeedTypesDao;
import com.jemmmedia.organise.service.impl.service.FeedTypesService;

/**
 * @author harjinder
 *
 */
@Service("feedTypesService")
public class FeedTypesServiceImpl implements FeedTypesService {

	@Resource
	FeedTypesDao feedTypesDao;
	
	/* (non-Javadoc)
	 * @see com.jemmmedia.organise.service.impl.service.FeedTypesService#selectByEample()
	 */
	@Override
	public List<FeedTypes> selectByEample() {
		List<FeedTypes> feedTypesList = null;
		
		try {
			feedTypesList = feedTypesDao.selectByEample();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return feedTypesList;
	}

}

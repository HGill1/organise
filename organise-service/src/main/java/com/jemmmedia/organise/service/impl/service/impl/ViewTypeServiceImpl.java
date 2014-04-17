/**
 * 
 */
package com.jemmmedia.organise.service.impl.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jemmmedia.organise.mybatis.db.mybatis.model.ViewType;
import com.jemmmedia.organise.service.impl.dao.ViewTypeDao;
import com.jemmmedia.organise.service.impl.service.ViewTypeService;

/**
 * @author harjinder
 *
 */
@Service("viewTypeService")
public class ViewTypeServiceImpl implements ViewTypeService {

	@Resource
	private ViewTypeDao viewTypeDao;
	
	@Override
	public void insert (ViewType viewType){
		
		viewTypeDao.insert(viewType);
	}
	
	@Override
	public ViewType selectByPrimaryKey(long userId){
		
		ViewType list =  null;
		
		try {
			list = viewTypeDao.selectByPrimaryKey(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
}

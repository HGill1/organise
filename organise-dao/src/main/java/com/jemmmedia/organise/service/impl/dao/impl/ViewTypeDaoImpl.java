/**
 * 
 */
package com.jemmmedia.organise.service.impl.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jemmmedia.organise.mybatis.db.mybatis.dao.ViewTypeMapper;
import com.jemmmedia.organise.mybatis.db.mybatis.model.ViewType;
import com.jemmmedia.organise.service.impl.dao.ViewTypeDao;

/**
 * @author harjinder
 *
 */
@Service("viewTypeDao")
public class ViewTypeDaoImpl implements ViewTypeDao {

	@Resource
	private ViewTypeMapper viewTypeMapper;
	
	@Override
	public void insert(ViewType viewType){
		if(viewType.getViewType() != 0 )
			viewTypeMapper.insertSelective(viewType);
		else
			viewTypeMapper.deleteByPrimaryKey(viewType.getUserId());
	}
	
	@Override
	public ViewType selectByPrimaryKey(long userId){
		
		
			return viewTypeMapper.selectByPrimaryKey(userId);
		
	}
	
}

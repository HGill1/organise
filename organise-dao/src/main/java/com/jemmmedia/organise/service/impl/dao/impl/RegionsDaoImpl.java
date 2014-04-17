/**
 * 
 */
package com.jemmmedia.organise.service.impl.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jemmmedia.organise.mybatis.db.mybatis.dao.RegionsMapper;
import com.jemmmedia.organise.mybatis.db.mybatis.model.Regions;
import com.jemmmedia.organise.mybatis.db.mybatis.model.RegionsExample;
import com.jemmmedia.organise.service.impl.dao.RegionsDao;

/**
 * @author harjinder
 *
 */
@Service("regionsDao")
public class RegionsDaoImpl implements RegionsDao {
	
	@Resource
	RegionsMapper regionsMapper;
	
	@Override
	public int insertSelective(Regions region){
		
			return regionsMapper.insertSelective(region);
	}
	
	@Override
	public int updateByPrimaryKeySelective(Regions region){
		
			return regionsMapper.updateByPrimaryKeySelective(region);
	}
	
	@Override
	public int deleteByPrimaryKey(long regionId){
		
			return regionsMapper.deleteByPrimaryKey(regionId);
	}
	
	@Override
	public int regionsCount(long groupId){
		RegionsExample  regionsExample =  new RegionsExample();
		 
		regionsExample.createCriteria().andGroupIdEqualTo(groupId);
		
			return regionsMapper.countByExample(regionsExample);
	}

}

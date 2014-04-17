package com.jemmmedia.organise.service.impl.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jemmmedia.organise.mybatis.db.mybatis.model.Regions;
import com.jemmmedia.organise.service.impl.dao.RegionsDao;
import com.jemmmedia.organise.service.impl.service.RegionsService;

@Service("regionsService")
public class RegionsServiceImpl implements RegionsService {

@Resource(name="regionsDao")
RegionsDao regionsDao;
	

	@Override
	public int insertSelective(Regions region) {

		int count = 0;

		try {
			count = regionsDao.insertSelective(region);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	
	@Override
	public int updateByPrimaryKeySelective(Regions region){
			
			int count = 0;
			
			try {
				count = regionsDao.updateByPrimaryKeySelective(region);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return count;
		}
	
	@Override
	public int deleteByPrimaryKey(long regionId){
			
			int count = 0;
			
			try {
				count = regionsDao.deleteByPrimaryKey(regionId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return count;
		}
	
	@Override
	public int regionsCount(long groupId){
			
			int count = 0;
			
			try {
				count = regionsDao.regionsCount(groupId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return count;
		}
}

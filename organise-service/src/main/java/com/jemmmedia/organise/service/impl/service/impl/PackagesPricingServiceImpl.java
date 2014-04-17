/**
 * 
 */
package com.jemmmedia.organise.service.impl.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jemmmedia.organise.mybatis.db.mybatis.model.PackagesPricing;
import com.jemmmedia.organise.service.impl.dao.PackagesPricingDao;
import com.jemmmedia.organise.service.impl.service.PackagesPricingService;

/**
 * @author harjinder
 *
 */
@Service("packagesPricingService")
public class PackagesPricingServiceImpl implements PackagesPricingService {
	
	@Resource
	PackagesPricingDao packagesPricingDao;
	
	@Override
	public int insertSelective(PackagesPricing packagesPricing){
		
		int count = 0;
		
		try {
			count =  packagesPricingDao.insertSelective(packagesPricing);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	@Override
	public PackagesPricing selectByPrimaryKey(long packageId){
		
		PackagesPricing packagesPricing = null;
		
		try {
			packagesPricing =  packagesPricingDao.selectByPrimaryKey(packageId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return packagesPricing;
	}
	

}

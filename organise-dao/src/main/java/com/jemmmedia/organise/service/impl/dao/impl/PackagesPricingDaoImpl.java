/**
 * 
 */
package com.jemmmedia.organise.service.impl.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jemmmedia.organise.mybatis.db.mybatis.dao.PackagesPricingMapper;
import com.jemmmedia.organise.mybatis.db.mybatis.model.PackagesPricing;
import com.jemmmedia.organise.service.impl.dao.PackagesPricingDao;

/**
 * @author harjinder
 *
 */
@Service("packagesPricingDao")
public class PackagesPricingDaoImpl implements PackagesPricingDao {
	
	@Resource
	PackagesPricingMapper packagesPricingMapper;
	
	@Override
	public int insertSelective(PackagesPricing packagesPricing){
		
		return packagesPricingMapper.insertSelective(packagesPricing);
	}
	
	@Override
	public PackagesPricing selectByPrimaryKey(long packageId){
		
		return packagesPricingMapper.selectByPrimaryKey(packageId);
	}
	
	

}

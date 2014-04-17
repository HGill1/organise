/**
 * 
 */
package com.jemmmedia.organise.service.impl.dao;

import com.jemmmedia.organise.mybatis.db.mybatis.model.PackagesPricing;

/**
 * @author harjinder
 *
 */
public interface PackagesPricingDao {

	int insertSelective(PackagesPricing packagesPricing);

	PackagesPricing selectByPrimaryKey(long packageId);

}

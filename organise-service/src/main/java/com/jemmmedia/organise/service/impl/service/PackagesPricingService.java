/**
 * 
 */
package com.jemmmedia.organise.service.impl.service;

import com.jemmmedia.organise.mybatis.db.mybatis.model.PackagesPricing;

/**
 * @author harjinder
 *
 */
public interface PackagesPricingService {

	int insertSelective(PackagesPricing PackagesPricing);

	PackagesPricing selectByPrimaryKey(long packageId);

}

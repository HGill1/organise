/**
 * 
 */
package com.jemmmedia.organise.service.impl.service;

import com.jemmmedia.organise.mybatis.db.mybatis.model.Regions;

/**
 * @author harjinder
 *
 */
public interface RegionsService {

	int insertSelective(Regions region);

	int updateByPrimaryKeySelective(Regions region);

	int deleteByPrimaryKey(long regionId);

	int regionsCount(long groupId);

}

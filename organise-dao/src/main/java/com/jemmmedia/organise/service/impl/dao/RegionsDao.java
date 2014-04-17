/**
 * 
 */
package com.jemmmedia.organise.service.impl.dao;

import com.jemmmedia.organise.mybatis.db.mybatis.model.Regions;

/**
 * @author harjinder
 *
 */
public interface RegionsDao {

	int insertSelective(Regions region);

	int updateByPrimaryKeySelective(Regions region);

	int deleteByPrimaryKey(long regionId);

	int regionsCount(long groupId);

}

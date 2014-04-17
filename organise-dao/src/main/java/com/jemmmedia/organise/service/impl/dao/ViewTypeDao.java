/**
 * 
 */
package com.jemmmedia.organise.service.impl.dao;

import com.jemmmedia.organise.mybatis.db.mybatis.model.ViewType;

/**
 * @author harjinder
 *
 */
public interface ViewTypeDao {

	void insert(ViewType viewType);

	ViewType selectByPrimaryKey(long userId);

}

/**
 * 
 */
package com.jemmmedia.organise.service.impl.service;

import com.jemmmedia.organise.mybatis.db.mybatis.model.ViewType;

/**
 * @author harjinder
 *
 */
public interface ViewTypeService {

	void insert(ViewType viewType);

	ViewType selectByPrimaryKey(long userId);
	
}

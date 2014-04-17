/**
 * 
 */
package com.jemmmedia.organise.service.impl.dao;

import com.jemmmedia.organise.mybatis.db.mybatis.model.Orders;

/**
 * @author harjinder
 *
 */
public interface OrdersDao {

	void insertSelective(Orders order);
  
}

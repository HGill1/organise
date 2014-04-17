/**
 * 
 */
package com.jemmmedia.organise.service.impl.service;

import com.jemmmedia.organise.mybatis.db.mybatis.model.Orders;

/**
 * @author harjinder
 *
 */
public interface OrdersService {

	void insertSelective(Orders order);

}

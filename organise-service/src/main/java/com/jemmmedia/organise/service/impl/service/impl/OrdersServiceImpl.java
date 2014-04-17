/**
 * 
 */
package com.jemmmedia.organise.service.impl.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jemmmedia.organise.mybatis.db.mybatis.model.Orders;
import com.jemmmedia.organise.service.impl.dao.OrdersDao;
import com.jemmmedia.organise.service.impl.service.OrdersService;

/**
 * @author harjinder
 *
 */
@Service("ordersService")
public class OrdersServiceImpl implements OrdersService {

	@Resource
	OrdersDao ordersDao;
	
	@Override
	public
	void insertSelective(Orders order){
		try {
			ordersDao.insertSelective(order);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

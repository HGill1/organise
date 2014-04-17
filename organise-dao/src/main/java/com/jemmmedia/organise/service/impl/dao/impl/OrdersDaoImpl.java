/**
 * 
 */
package com.jemmmedia.organise.service.impl.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jemmmedia.organise.mybatis.db.mybatis.dao.OrdersMapper;
import com.jemmmedia.organise.mybatis.db.mybatis.model.Orders;
import com.jemmmedia.organise.service.impl.dao.OrdersDao;

/**
 * @author harjinder
 *
 */
@Service("ordersDao")
public class OrdersDaoImpl implements OrdersDao {
	
	@Resource
	OrdersMapper ordersMapper;
	
	@Override
	public
	void insertSelective(Orders order){
		
		 ordersMapper.insertSelective(order);
	}
}

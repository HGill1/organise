/**
 * 
 */
package com.jemmmedia.organise.service.impl.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jemmmedia.organise.mybatis.db.mybatis.model.User;
import com.jemmmedia.organise.service.impl.dao.JemmAdminDao;
import com.jemmmedia.organise.service.impl.service.JemmAdminService;

/**
 * @author harjinder
 *
 */
@Service("jemmAdminService")
public class JemmAdminServiceImpl implements JemmAdminService {

	@Resource
	private JemmAdminDao jemmAdminDao;
	
	@Override
	public List<User> selectAllGroups(){
		
		List<User> list = null;
		
		try {
			list = jemmAdminDao.selectAllGroups();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
		
	}
	
}

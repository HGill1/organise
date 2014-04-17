/**
 * 
 */
package com.jemmmedia.organise.service.impl.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jemmmedia.organise.mybatis.db.mybatis.model.Dept;
import com.jemmmedia.organise.mybatis.db.mybatis.model.DeptUsers;
import com.jemmmedia.organise.service.impl.dao.DeptDao;
import com.jemmmedia.organise.service.impl.service.DeptService;

/**
 * @author harjinder
 *
 */
@Service("deptService")
public class DeptServiceImpl implements DeptService {
	
	@Resource
	DeptDao deptDao;
	
	@Override
	public int insertSelective(Dept dept) {

		int count = 0;

		try {
			count = deptDao.insertSelective(dept);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	
	@Override
	public int updateByPrimaryKeySelective(Dept dept){
			
			int count = 0;
			
			try {
				count = deptDao.updateByPrimaryKeySelective(dept);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return count;
		}
	
	@Override
	public int deleteByPrimaryKey(long deptId){
			
			int count = 0;
			
			try {
				count = deptDao.deleteByPrimaryKey(deptId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return count;
		}
	
	@Override
	public List<Dept> selectAllDepts(long groupId){
		
		List<Dept> list = null;
		
			 try {
				list = deptDao.selectAllDepts(groupId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			 
			 return list;
	}
	
	@Override
	public List<Dept> selectAllUsersInDepts(long groupId){
		
		List<Dept> list = null;
		
			 try {
				list = deptDao.selectAllUsersInDepts(groupId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			 
			 return list;
	}
	
	@Override
	public void insertDeptsForUser(List<Long> deptsId, long userId){
		
		DeptUsers deptUsers =  new DeptUsers();
		
		Date date = new Date();
		
		deptDao.deleteAllUserDept(userId);
		
		for (Long deptId : deptsId) {
			
			deptUsers.setDeptId(deptId);
			deptUsers.setUserId(userId);
			
			deptUsers.setCreatedAt(date);
			deptUsers.setUpdatedAt(date);
			
			try {
				deptDao.insertDeptUsers(deptUsers);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
	@Override
	public List<String> selectUsersEmailInDept(long deptId){
		
		List<String> list = null;
		
			 try {
				list = deptDao.selectUsersEmailInDept(deptId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			 
			 return list;
	}

}

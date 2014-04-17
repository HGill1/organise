/**
 * 
 */
package com.jemmmedia.organise.service.impl.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jemmmedia.organise.mybatis.db.mybatis.dao.DeptMapper;
import com.jemmmedia.organise.mybatis.db.mybatis.dao.DeptUsersMapper;
import com.jemmmedia.organise.mybatis.db.mybatis.model.Dept;
import com.jemmmedia.organise.mybatis.db.mybatis.model.DeptUsers;
import com.jemmmedia.organise.service.impl.dao.DeptDao;

/**
 * @author harjinder
 *
 */
@Service("deptDao")
public class DeptDaoImpl implements DeptDao {
	@Resource
	DeptMapper deptMapper;
	
	@Resource
	DeptUsersMapper deptUsersMapper;
	
	@Override
	public int insertSelective(Dept dept){
		
			return deptMapper.insertSelective(dept);
	}
	
	@Override
	public int updateByPrimaryKeySelective(Dept dept){
		
			return deptMapper.updateByPrimaryKeySelective(dept);
	}
	
	@Override
	public int deleteByPrimaryKey(long deptId){
		
			return deptMapper.deleteByPrimaryKey(deptId);
	}
	
	@Override
	public List<Dept> selectAllDepts(long groupId){
		
			return deptMapper.selectAllDepts(groupId);
	}
	
	@Override
	public List<Dept> selectAllUsersInDepts(long groupId){
		
			return deptMapper.selectAllUsersInDepts(groupId);
	}
	
	@Override
	public int insertDeptUsers(DeptUsers deptUsers){
		
			return deptUsersMapper.insertSelective(deptUsers);
	}
	
	@Override
	public void deleteAllUserDept(long userId){
		
			deptUsersMapper.deleteAllUserDept(userId);
	}
	
	@Override
	public List<String> selectUsersEmailInDept(long deptId){
		
			return deptMapper.selectUsersEmailInDept(deptId);
	}

}

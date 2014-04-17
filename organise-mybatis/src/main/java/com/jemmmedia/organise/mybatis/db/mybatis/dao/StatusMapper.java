package com.jemmmedia.organise.mybatis.db.mybatis.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.jemmmedia.organise.mybatis.db.mybatis.model.Status;
import com.jemmmedia.organise.mybatis.db.mybatis.model.StatusExample;

public interface StatusMapper {
    /**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table status
	 * @mbggenerated  Tue Nov 27 09:25:37 GMT 2012
	 */
	int countByExample(StatusExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table status
	 * @mbggenerated  Tue Nov 27 09:25:37 GMT 2012
	 */
	int deleteByExample(StatusExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table status
	 * @mbggenerated  Tue Nov 27 09:25:37 GMT 2012
	 */
	int deleteByPrimaryKey(Byte id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table status
	 * @mbggenerated  Tue Nov 27 09:25:37 GMT 2012
	 */
	int insert(Status record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table status
	 * @mbggenerated  Tue Nov 27 09:25:37 GMT 2012
	 */
	int insertSelective(Status record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table status
	 * @mbggenerated  Tue Nov 27 09:25:37 GMT 2012
	 */
	List<Status> selectByExample(StatusExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table status
	 * @mbggenerated  Tue Nov 27 09:25:37 GMT 2012
	 */
	Status selectByPrimaryKey(Byte id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table status
	 * @mbggenerated  Tue Nov 27 09:25:37 GMT 2012
	 */
	int updateByExampleSelective(@Param("record") Status record,
			@Param("example") StatusExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table status
	 * @mbggenerated  Tue Nov 27 09:25:37 GMT 2012
	 */
	int updateByExample(@Param("record") Status record,
			@Param("example") StatusExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table status
	 * @mbggenerated  Tue Nov 27 09:25:37 GMT 2012
	 */
	int updateByPrimaryKeySelective(Status record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table status
	 * @mbggenerated  Tue Nov 27 09:25:37 GMT 2012
	 */
	int updateByPrimaryKey(Status record);

	@Select("SELECT id, name FROM status")
    @MapKey("id")
    Map<Integer,Status> getAllStatus();
    
}
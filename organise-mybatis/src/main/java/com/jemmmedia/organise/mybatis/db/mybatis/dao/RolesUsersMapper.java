package com.jemmmedia.organise.mybatis.db.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jemmmedia.organise.mybatis.db.mybatis.model.RolesUsers;
import com.jemmmedia.organise.mybatis.db.mybatis.model.RolesUsersExample;

public interface RolesUsersMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table roles_users
     *
     * @mbggenerated Mon Nov 05 09:14:45 GMT 2012
     */
    int countByExample(RolesUsersExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table roles_users
     *
     * @mbggenerated Mon Nov 05 09:14:45 GMT 2012
     */
    int deleteByExample(RolesUsersExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table roles_users
     *
     * @mbggenerated Mon Nov 05 09:14:45 GMT 2012
     */
    int insert(RolesUsers record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table roles_users
     *
     * @mbggenerated Mon Nov 05 09:14:45 GMT 2012
     */
    int insertSelective(RolesUsers record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table roles_users
     *
     * @mbggenerated Mon Nov 05 09:14:45 GMT 2012
     */
    List<RolesUsers> selectByExample(RolesUsersExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table roles_users
     *
     * @mbggenerated Mon Nov 05 09:14:45 GMT 2012
     */
    int updateByExampleSelective(@Param("record") RolesUsers record, @Param("example") RolesUsersExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table roles_users
     *
     * @mbggenerated Mon Nov 05 09:14:45 GMT 2012
     */
    int updateByExample(@Param("record") RolesUsers record, @Param("example") RolesUsersExample example);
    
    void insertOrUpdateSelective(RolesUsers rolesUsers);
    
    int getUserRoleId(int userId);
}
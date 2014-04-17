package com.jemmmedia.organise.mybatis.db.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jemmmedia.organise.mybatis.db.mybatis.model.Note;
import com.jemmmedia.organise.mybatis.db.mybatis.model.NoteExample;

public interface NoteMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table notes
     *
     * @mbggenerated Mon Aug 06 10:08:53 BST 2012
     */
    int countByExample(NoteExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table notes
     *
     * @mbggenerated Mon Aug 06 10:08:53 BST 2012
     */
    int deleteByExample(NoteExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table notes
     *
     * @mbggenerated Mon Aug 06 10:08:53 BST 2012
     */
    int insert(Note record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table notes
     *
     * @mbggenerated Mon Aug 06 10:08:53 BST 2012
     */
    int insertSelective(Note record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table notes
     *
     * @mbggenerated Mon Aug 06 10:08:53 BST 2012
     */
    List<Note> selectByExampleWithBLOBs(NoteExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table notes
     *
     * @mbggenerated Mon Aug 06 10:08:53 BST 2012
     */
    List<Note> selectByExample(NoteExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table notes
     *
     * @mbggenerated Mon Aug 06 10:08:53 BST 2012
     */
    int updateByExampleSelective(@Param("record") Note record, @Param("example") NoteExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table notes
     *
     * @mbggenerated Mon Aug 06 10:08:53 BST 2012
     */
    int updateByExampleWithBLOBs(@Param("record") Note record, @Param("example") NoteExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table notes
     *
     * @mbggenerated Mon Aug 06 10:08:53 BST 2012
     */
    int updateByExample(@Param("record") Note record, @Param("example") NoteExample example);
}
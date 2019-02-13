package com.yajie.springboot.learn.dao;

import com.yajie.springboot.learn.entity.SysLog;
import com.yajie.springboot.learn.entity.SysLogCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface SysLogMapper {
    long countByExample(SysLogCriteria example);

    int deleteByExample(SysLogCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysLog record);

    int insertSelective(SysLog record);

    List<SysLog> selectByExampleWithRowbounds(SysLogCriteria example, RowBounds rowBounds);

    List<SysLog> selectByExample(SysLogCriteria example);

    SysLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysLog record, @Param("example") SysLogCriteria example);

    int updateByExample(@Param("record") SysLog record, @Param("example") SysLogCriteria example);

    int updateByPrimaryKeySelective(SysLog record);

    int updateByPrimaryKey(SysLog record);
}
package com.yajie.springboot.learn.dao;

import com.yajie.springboot.learn.entity.Log;
import com.yajie.springboot.learn.entity.LogCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface LogMapper {
    long countByExample(LogCriteria example);

    int deleteByExample(LogCriteria example);

    int deleteByPrimaryKey(Integer logId);

    int insert(Log record);

    int insertSelective(Log record);

    List<Log> selectByExampleWithRowbounds(LogCriteria example, RowBounds rowBounds);

    List<Log> selectByExample(LogCriteria example);

    Log selectByPrimaryKey(Integer logId);

    int updateByExampleSelective(@Param("record") Log record, @Param("example") LogCriteria example);

    int updateByExample(@Param("record") Log record, @Param("example") LogCriteria example);

    int updateByPrimaryKeySelective(Log record);

    int updateByPrimaryKey(Log record);
}
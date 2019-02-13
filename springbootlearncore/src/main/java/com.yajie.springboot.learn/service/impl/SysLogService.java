package com.yajie.springboot.learn.service.impl;

import com.yajie.springboot.learn.dao.SysLogMapper;
import com.yajie.springboot.learn.entity.SysLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/** 
* @ClassName: SysLogService 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author
* @date 2018年2月2日
*  
*/
@Service
public class SysLogService {
	@Autowired
	SysLogMapper sysLogMapper ;
	
	/** 
	* @Title: addLog 
	* @Description: TODO(这里用一句话描述这个方法的作用) 添加日志
	* @param @param log
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @throws 
	*/
	@Transactional
	public boolean addLog(SysLog log) {
		int affectedRows = sysLogMapper.insertSelective(log);
		return affectedRows > 0;
	}


	/** 
	* @Title: delLog 
	* @Description: TODO(这里用一句话描述这个方法的作用) 单个删除日志
	* @param @param logId
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @throws 
	*/
	@Transactional
	public boolean delLog(Integer logId) {
		int affectedRows = sysLogMapper.deleteByPrimaryKey(logId);
		return affectedRows > 0;
	}


	/** 
	* @Title: editLog 
	* @Description: TODO(这里用一句话描述这个方法的作用) 更新日志
	* @param @param log
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @throws 
	*/
	@Transactional
	public boolean editLog(SysLog log) {
		int affectedRows = sysLogMapper.updateByPrimaryKeySelective(log);
		return affectedRows > 0;
	}


	/** 
	* @Title: getAll 
	* @Description: TODO(这里用一句话描述这个方法的作用) 查询日志
	* @param @return    设定文件 
	* @return List<Log>    返回类型 
	* @throws 
	*/
	public List<SysLog> getAll() {
		return sysLogMapper.selectByExample(null);
	}


	/** 
	* @Title: insertLog 
	* @Description: TODO(这里用一句话描述这个方法的作用) 插入日志
	* @param @param log    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	@Transactional
	public void insertLog(SysLog log) {
		sysLogMapper.insertSelective(log);
	}


	/** 
	* @Title: selectByPrimaryKey 
	* @Description: TODO(这里用一句话描述这个方法的作用) 根据id返回一个对象 
	* @param @param logId
	* @param @return    设定文件 
	* @return Log    返回类型 
	* @throws 
	*/
	public SysLog selectByPrimaryKey(Integer logId) {
		return sysLogMapper.selectByPrimaryKey(logId);
	}
	
}

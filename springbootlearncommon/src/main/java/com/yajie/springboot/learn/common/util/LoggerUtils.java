package com.yajie.springboot.learn.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
* @ClassName: LoggerUtils	
* @author:
*/
public class LoggerUtils {

	private Logger logger;

	/**
	 * 构造方法，初始化Slf4j的日志对象
	 */
	private LoggerUtils(Logger Slf4jLogger) {
		logger = Slf4jLogger;
	}

	/**
	 * 获取构造器，根据类初始化Logger对象
	 * 
	 * @param Class
	 *            Class对象
	 * @return Logger对象
	 */
	public static LoggerUtils getLogger(Class classObject) {
		return new LoggerUtils(LoggerFactory.getLogger(classObject));
	}

	/**
	 * 获取构造器，根据类名初始化Logger对象
	 * 
	 * @param String
	 *            类名字符串
	 * @return Logger对象
	 */
	public static LoggerUtils getLogger(String loggerName) {
		return new LoggerUtils(LoggerFactory.getLogger(loggerName));
	}

	public String getName() {
		return logger.getName();
	}

	public Logger getSlf4jLogger() {
		return logger;
	}

	public boolean equals(LoggerUtils newLogger) {
		return logger.equals(newLogger.getSlf4jLogger());
	}
	
	public void debug(String info) {
		logger.debug(info);
	}

	public void debug(String info, Object obj) {
		logger.debug(info, obj);
	}
	
	public void error(String info) {
		logger.error(info);
	}
	
	public void error(String info, Object obj) {
		logger.error(info, obj);
	}

	public void info(String info) {
		logger.info(info);
	}
	
	public void info(String info, Object obj) {
		logger.info(info, obj);
	}

	
}
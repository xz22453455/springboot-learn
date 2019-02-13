package com.yajie.springboot.learn.common.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/** 
* @ClassName: ShiroUtils 
* @Description: Shiro工具类
* @author WangYiZhi yizhi_wang@126.com 
* @date 2018年2月1日
*  
*/
public class ShiroUtils {

	public static Session getSession() {
		return getSubject().getSession();
	}

	public static Subject getSubject() {
		return SecurityUtils.getSubject();
	}
	
	public static Object getCurrentUser() {
		return getSessionAttribute(Const.SESSION_USER);
	}

	public static Object getCurrentUserName() {
		return getSubject().getPrincipal();
	}

	public static void setSessionAttribute(Object key, Object value) {
		getSession().setAttribute(key, value);
	}

	public static Object getSessionAttribute(Object key) {
		return getSession().getAttribute(key);
	}

	public static boolean isLogin() {
		return getCurrentUserName() != null;
	}

	public static void logout() {
		getSubject().logout();
	}

}

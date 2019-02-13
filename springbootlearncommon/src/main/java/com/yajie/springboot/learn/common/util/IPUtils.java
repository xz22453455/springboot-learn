package com.yajie.springboot.learn.common.util;

import javax.servlet.http.HttpServletRequest;

/** 
* @ClassName: IPUtils 
* @Description: 获取IP地址工具類
* @author WangYiZhi yizhi_wang@126.com 
* @date 2018年1月10日
*  
*/
public class IPUtils {
	private static LoggerUtils logger = LoggerUtils.getLogger(IPUtils.class);

	/**
	 * 获取IP地址
	 * 
	 * 使用Nginx等反向代理软件， 则不能通过request.getRemoteAddr()获取IP地址
	 * 如果使用了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP地址，X-Forwarded-For中第一个非unknown的有效IP字符串，则为真实IP地址
	 */
	public static String getIpAddr(HttpServletRequest request) {

		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
	}
	
	// IP转int类型
	public static int ip2Integer(String ipAddress) {
		int result = 0;
		String[] ipAddressInArray = ipAddress.split("\\.");
		for (int i = 3; i >= 0; i--) {
			int ip = Integer.parseInt(ipAddressInArray[3 - i]);
			//left shifting 24,16,8,0 and bitwise OR
			//1. 192 << 24
			//1. 168 << 16
			//1. 1   << 8
			//1. 2   << 0
			result |= ip << (i * 8);
		}
		return result;
	}
	
	// int类型转IP
	public static String Integer2Ip(int ip) {
		StringBuilder result = new StringBuilder(15);
		for (int i = 0; i < 4; i++) {
			result.insert(0,Long.toString(ip & 0xff));
			if (i < 3) {
				result.insert(0,'.');
			}
			ip = ip >> 8;
		}
		return result.toString();
	}
	
	// int类型转IP
	public static String Integer2Ip_(int ip) {
		return ((ip >> 24) & 0xFF) + "." 
			+ ((ip >> 16) & 0xFF) + "." 
			+ ((ip >> 8) & 0xFF) + "." 
			+ (ip & 0xFF);
	}

}

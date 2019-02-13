package com.yajie.springboot.learn.common.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/** 
* @ClassName: ServletUtils 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author WangYiZhi yizhi_wang@126.com 
* @date 2018年1月10日
*  
*/
public class ServletUtils {

    /**
     * 获取HttpServletRequest
     * @return
     */
    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

}

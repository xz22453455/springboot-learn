
package com.yajie.springboot.learn.aspect;


import com.yajie.springboot.learn.annotation.SystemLog;
import com.yajie.springboot.learn.common.util.IPUtils;
import com.yajie.springboot.learn.common.util.LoggerUtils;
import com.yajie.springboot.learn.common.util.ServletUtils;
import com.yajie.springboot.learn.common.util.ShiroUtils;
import com.yajie.springboot.learn.entity.SysLog;
import com.yajie.springboot.learn.service.impl.SysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @version V1.0
 * @ClassName: SystemLogAspect
 * @Description: 切点类
 * @author:
 * @date: 2017年11月1日 下午8:15:57
 */
@Aspect
@Component
public class SysLogAspect {

    // 本地异常日志记录对象
    private LoggerUtils logger = LoggerUtils.getLogger(this.getClass());

    // 注入Service用于把日志保存数据库
    @Autowired
    private SysLogService sysLogService;

    // Service层切点
    @Pointcut("@annotation(com.yajie.springboot.learn.annotation.SystemLog)")
    //@Pointcut("execution(public * com.mes.service..*.*(..))")
    public void serviceAspect() {
    }

    // Controller层切点
    @Pointcut("@annotation(com.yajie.springboot.learn.annotation.SystemLog)")
    //@Pointcut("execution(public * com.mes.controller..*.*(..))")
    public void controllerAspect() {
    }

    /**
     * 前置通知 用于拦截Controller层记录用户的操作
     *
     * @param joinPoint
     *            切点
     * @throws Throwable
     */
//	@Before("controllerAspect()")
//	public void doBefore(JoinPoint joinPoint) throws Throwable {
//		long beginTime = System.currentTimeMillis();
//		// 获取执行时长(毫秒)
//		long executeTime = System.currentTimeMillis() - beginTime;
//		logger.info(">>>>>>>>>>executeTime: " + executeTime);
//		
//		// 获取当前系统用户名
//		String operator = (String) ShiroUtils.getCurrentUserName();
//		if (operator == null) {
//			operator = "未知用户";
//		}
//		logger.info(">>>>>>>>>>operator: " + operator);
//		
//		HttpServletRequest request = ServletUtils.getHttpServletRequest();
//		// 获取客户端请求的IP
//		int remoteAddr = IPUtils.ip2Integer(IPUtils.getIpAddr(request));
//		logger.info(">>>>>>>>>>remoteAddr: " + IPUtils.getIpAddr(request));
//		
//		// 获取用户代理
//		String userAgent = request.getHeader("user-agent");
//		logger.info(">>>>>>>>>>userAgent: " + userAgent);
//		
//		// 获取请求URI
//		String requestUri = request.getRequestURI();
//		logger.info(">>>>>>>>>>requestUri: " + requestUri);
//		
//		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//		Method method = signature.getMethod();
//		SystemLog systemLog = method.getAnnotation(SystemLog.class);
//		// 获取模块名称
//		String moduleName = systemLog.moduleName();
//		logger.info(">>>>>>>>>>模块名称: " + moduleName);
//		// 获取日志内容
//		String content = systemLog.description();
//		logger.info(">>>>>>>>>>content: " + content);
//		
//		// 获取请求的方法名
//		String className = joinPoint.getTarget().getClass().getName();
//		String methodName = signature.getName();
//		String requestMethod = className + "." + methodName + "()";
//		logger.info(">>>>>>>>>>requestMethod: " + requestMethod);
//		
//		// 获取请求的参数
//		Object[] args = joinPoint.getArgs();
//		String requestParams = "{ ";
//		if (args != null && args.length > 0) {
//			for (int i = 0; i < args.length; i++) {
//				if (i == (args.length - 1)) {
//					requestParams += args[i].getClass().getName();
//				} else {
//					requestParams += args[i].getClass().getName() + ", ";
//				}
//			}
//		}
//		requestParams += " }";
//
//		try {
//			// *========数据库日志=========*//
//			SysLog log = new SysLog();
//			log.setId(null);
//			log.setType("1");
//			log.setUserAgent(userAgent);
//			log.setModuleName(moduleName);
//			log.setContent(content);
//			log.setRequestUri(requestUri);
//			log.setRequestMethod(requestMethod);
////			log.setRequestParams(JSON.toJSONString(requestParams));
//			log.setRequestParams(requestParams);
//			log.setExecuteTime(executeTime);
//			log.setRemoteAddr(remoteAddr);
//			log.setOperator(operator);
//			log.setCreateTime(new Date());
//			// 保存数据库
//			sysLogService.addLog(log);
//		} catch (Exception e) {
//			// 记录本地异常日志
//			logger.error("Around环绕通知异常: " + e.getMessage());
//		}
//	}

    /**
     * 环绕通知 用于拦截Controller层记录用户的操作
     *
     * @param joinPoint 切点
     * @throws Throwable
     */
    @Around("controllerAspect()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long beginTime = System.currentTimeMillis();
        // 执行方法
        Object result = joinPoint.proceed();
        //logger.info(">>>>>>>>>>服务端响应结果: " + result.getClass().getName() + " >> " + JSON.toJSONString(result));
        // 获取执行时长(毫秒)
        long executeTime = System.currentTimeMillis() - beginTime;
        logger.info(">>>>>>>>>>执行时长(毫秒): " + executeTime);

        // 获取当前系统用户名
        String operator = (String) ShiroUtils.getCurrentUserName();
        if (operator == null) {
            operator = "未知用户";
        }
        logger.info(">>>>>>>>>>当前系统用户名: " + operator);

        HttpServletRequest request = ServletUtils.getHttpServletRequest();
        // 获取客户端请求的IP
        int remoteAddr = IPUtils.ip2Integer(IPUtils.getIpAddr(request));
        logger.info(">>>>>>>>>>客户端请求的IP: " + remoteAddr);

        // 获取用户代理
        String userAgent = request.getHeader("user-agent");
        logger.info(">>>>>>>>>>用户代理: " + userAgent);

        // 获取请求URI
        String requestUri = request.getRequestURI();
        logger.info(">>>>>>>>>>请求URI: " + requestUri);

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SystemLog systemLog = method.getAnnotation(SystemLog.class);
        // 获取模块名称
        String moduleName = systemLog.moduleName();
        logger.info(">>>>>>>>>>模块名称: " + moduleName);
        // 获取日志内容
        String content = systemLog.description();
        logger.info(">>>>>>>>>>日志内容: " + content);

        // 获取请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        String requestMethod = className + "." + methodName + "()";
        logger.info(">>>>>>>>>>请求的方法名: " + requestMethod);

        // 获取请求的参数
		/*Object[] args = joinPoint.getArgs();
		String requestParams = new Gson().toJson(args);*/
        Object[] args = joinPoint.getArgs();
        String requestParams = "{ ";
        if (args != null && args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                if (i == (args.length - 1)) {
                    requestParams += args[i].getClass().getName();
                } else {
                    requestParams += args[i].getClass().getName() + ", ";
                }
            }
        }
        requestParams += " }";
        logger.info(">>>>>>>>>>请求的参数: " + requestParams);

        try {
            // *========数据库日志=========*//
            SysLog log = new SysLog();
            log.setId(null);
            log.setType("1");
            log.setUserAgent(userAgent);
            log.setModuleName(moduleName);
            log.setContent(content);
            log.setRequestUri(requestUri);
            log.setRequestMethod(requestMethod);
            log.setRequestParams(requestParams);
            log.setExecuteTime(executeTime);
            log.setRemoteAddr(remoteAddr);
            log.setOperator(operator);
            log.setCreateTime(new Date());
            // 保存数据库
            sysLogService.addLog(log);
        } catch (Exception e) {
            // 记录本地异常日志
            logger.error("Around环绕通知异常: " + e.getMessage());
        }
        return result;
    }

    /**
     * 异常通知 用于拦截service层记录异常日志
     *
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "serviceAspect()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
        long beginTime = System.currentTimeMillis();
        // 获取执行时长(毫秒)
        long executeTime = System.currentTimeMillis() - beginTime;

        // 获取当前系统用户名
        String operator = (String) ShiroUtils.getCurrentUserName();
        if (operator == null) {
            operator = "未知用户";
        }

        HttpServletRequest request = ServletUtils.getHttpServletRequest();
        // 获取客户端请求的IP
        int remoteAddr = IPUtils.ip2Integer(IPUtils.getIpAddr(request));
        logger.info("客户端IP为: " + remoteAddr);

        // 获取用户代理
        String userAgent = request.getHeader("user-agent");

        // 获取请求URI
        String requestUri = request.getRequestURI();

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SystemLog systemLog = method.getAnnotation(SystemLog.class);
        // 获取模块名称
        String moduleName = systemLog.moduleName();
        // 获取日志内容
        //String content = systemLog.description();
        String content = e.getMessage();

        // 获取请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        String requestMethod = className + "." + methodName + "()";

        // 获取请求的参数
        Object[] args = joinPoint.getArgs();
        String requestParams = "{ ";
        if (args != null && args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                if (i == (args.length - 1)) {
                    requestParams += args[i].getClass().getName();
                } else {
                    requestParams += args[i].getClass().getName() + ", ";
                }
            }
        }
        requestParams += " }";

        try {
            // *========数据库日志=========*//
            SysLog log = new SysLog();
            log.setId(null);
            log.setType("2");
            log.setUserAgent(userAgent);
            log.setModuleName(moduleName);
            log.setContent(content);
            log.setRequestUri(requestUri);
            log.setRequestMethod(requestMethod);
            log.setRequestParams(requestParams);
            log.setExecuteTime(executeTime);
            log.setRemoteAddr(remoteAddr);
            log.setOperator(operator);
            log.setCreateTime(new Date());
            // 保存数据库
            sysLogService.addLog(log);
        } catch (Exception ex) {
            // 记录本地异常日志
            logger.error("AfterThrowing通知异常: " + ex.getMessage());
        }
    }
}
/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: ExceptionNoticeAspect
 * Author:   Administrator
 * Date:     2019/1/25 0025 11:01
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yajie.springboot.learn.aspect;

import com.alibaba.fastjson.JSON;
import com.yajie.springboot.learn.annotation.ExceptionNotice;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Administrator
 * @create 2019/1/25 0025
 * @since 1.0.0
 * //JoinPoint对象封装了SpringAop中切面方法的信息,在切面方法中添加JoinPoint参数,
 *     // 就可以获取到封装了该方法信息的JoinPoint对象
 */
@Aspect
@Component
@Slf4j
public class ExceptionNoticeAspect {

    @Autowired
    private ExecutorService executorService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Pointcut("@annotation(com.yajie.springboot.learn.annotation.ExceptionNotice)")
    public void exceptionNoticeAspect() {
    }
    //连接点（joinpoint）切点对象  可以获取方法名1.joinpoint.getargs():获取带参方法的参数
    //2.joinpoint.getTarget():.获取他们的目标对象信息 3..joinpoint.getSignature():
    // (signature是信号,标识的意思):获取被增强的方法相关信息
    //包名和类名方法名
    @AfterThrowing(pointcut = "exceptionNoticeAspect()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Throwable e) {
        log.error("-------------------******异步线程开始************-------------------");

        //异常通知控制次数
        if (!redisCount(joinPoint)) {
            return;
        }
        executorService.execute(() -> {
            log.error("异常开始捕捉=============================================");
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            Method method = methodSignature.getMethod();
            Object[] args = joinPoint.getArgs();
            ExceptionNotice exceptionNotice = method.getAnnotation(ExceptionNotice.class);
            log.info("自定义注解的值：" + exceptionNotice.noticeNickName() + exceptionNotice.noticeRemarks() + exceptionNotice.sendWayType());


            // 获取用户请求方法的参数并序列化为JSON格式字符串
            String params = "";
            if (Objects.nonNull(args)) {
                params = JSON.toJSONString(args);
            }
            log.info("请求方法:" + (joinPoint.getTarget().getClass().getName() + "."
                    + joinPoint.getSignature().getName() + "()"));
            System.out.println();
            log.info("请求参数：{}" + params);

            log.error("-------------------afterThrowing.handler.start-------------------");
            log.error("异常名称：" + e.getClass().toString());
            log.error("异常栈信息:" + e);

            StringBuffer bufferErrorMsg = new StringBuffer();
            bufferErrorMsg
                    .append("异常自定义关键字：")
                    .append(exceptionNotice.noticeRemarks()).append(",").append("\n")
                    .append("请求方法：")
                    .append((joinPoint.getTarget().getClass().getName() + "."+ joinPoint.getSignature().getName() + "(),")).append("\n")
                    .append("请求参数：")
                    .append(params).append(",").append("\n")
                    .append("异常名称：")
                    .append(e.getClass().toString()).append(",").append("\n")
                    .append("异常栈信息：")
                    .append(e).append("\n");
            log.error(bufferErrorMsg.toString());
        });
        log.error("-------------------afterThrowing.handler.end-------------------");

    }

    /**
     * 同一方法异常通知次数控制
     * @param joinPoint
     * @return
     */
    private boolean redisCount(JoinPoint joinPoint) {
        String redisKey = String.valueOf((joinPoint.getTarget().getClass().getName() + joinPoint.getSignature().getName()).hashCode());
        BoundValueOperations<String, Integer> operations = redisTemplate.boundValueOps(redisKey);
        redisTemplate.expire(redisKey, 5, TimeUnit.MINUTES);
        operations.increment(1);
        if (operations.get() > 20) {
            log.error("同一个请求5分钟之内异常超过20次,不再通知了");
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
}
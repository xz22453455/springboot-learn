package com.yajie.springboot.learn.aspect;

import com.yajie.springboot.learn.annotation.LockRedis;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 〈说明〉<br> 
 * 〈〉
 * @author mao
 * @Date: 2019/1/25 0025
 * @Description:
 * @since 1.0.0
 */
@Aspect
@Slf4j
@Component
@ConditionalOnBean(JedisPool.class)
//@Conditional是Spring4新提供的注解，它的作用是按照一定的条件进行判断，满足条件给容器注册bean
public class LockRedisAspect {

    @Autowired
    private JedisPool jedisPool;

    @Pointcut("@annotation(com.yajie.springboot.learn.annotation.LockRedis)")
    public void lockRedis() {
    }
    /**
     * ProceedingJoinPoint用在环绕通知上 joinPoint用在前置后置
     * 1)JoinPoint
     *    java.lang.Object[] getArgs()：获取连接点方法运行时的入参列表；
     *    Signature getSignature() ：获取连接点的方法签名对象；
     *    java.lang.Object getTarget() ：获取连接点所在的目标对象；
     *    java.lang.Object getThis() ：获取代理对象本身；
     * 2)ProceedingJoinPoint
     * ProceedingJoinPoint继承JoinPoint子接口，它新增了两个用于执行连接点方法的方法：
     *    java.lang.Object proceed() throws java.lang.Throwable：通过反射执行目标对象的连接点处的方法；
     *    java.lang.Object proceed(java.lang.Object[] args) throws java.lang.Throwable：通过反射执行目标对象连接点处的方法，不过使用新的入参替换原来的入参。
     * @param joinPoint
     * @throws InterruptedException
     */
    @Around(value = "lockRedis()")
    public void isGetLockRedis(ProceedingJoinPoint joinPoint) throws InterruptedException {
        Jedis jedis = jedisPool.getResource();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        LockRedis lockRedis = method.getAnnotation(LockRedis.class);
        String redisKey = joinPoint.getTarget().getClass().getName().concat(".").concat(joinPoint.getSignature().getName());
        String redisValue = String.valueOf(System.currentTimeMillis());
        Long cxt = jedis.setnx(redisKey, redisValue);
        if (1L == cxt) {
            try {
                joinPoint.proceed();
            } catch (Throwable throwable) {
                log.error("出错异常，throwable = [{}]", throwable);
            } finally {
                manageLockRedis(lockRedis, jedis, redisKey, redisValue);
            }
        }
        if (0L == cxt) {
            String lockRedisTime = jedis.get(redisKey);
            if (StringUtils.isEmpty(lockRedisTime)) {
                managePolling(lockRedis, jedis, joinPoint);
                return;
            }
            Long lockTime = Long.valueOf(lockRedisTime);
            Long differSecond = (System.currentTimeMillis() - lockTime) / 1000;
            Long expireSecond = lockRedis.expireTime();
            if (differSecond < expireSecond) {
                managePolling(lockRedis, jedis, joinPoint);
                return;
            }
            if (differSecond >= expireSecond) {
                String oldTime = jedis.get(redisKey);
                String mayBeOldTime = jedis.getSet(redisKey,String.valueOf(System.currentTimeMillis()));
                if (Objects.equals(oldTime, mayBeOldTime)) {
                    jedis.del(redisKey);
                    String newRedisValue = String.valueOf(System.currentTimeMillis());
                    cxt = jedis.setnx(redisKey,newRedisValue);
                    if (1 != cxt) {
                        log.error("正常不会出现的情况");
                        return;
                    }
                    try {
                        joinPoint.proceed();
                    } catch (Throwable throwable) {
                        log.error("出错异常，throwable = [{}]", throwable);
                    } finally {
                        manageLockRedis(lockRedis, jedis, redisKey, newRedisValue);
                    }
                }
            }
        }
    }

    /**
     * 处理是否需要轮询
     * @param lockRedis
     * @param jedis
     * @param joinPoint
     */
    private void managePolling(LockRedis lockRedis, Jedis jedis, ProceedingJoinPoint joinPoint) {
        //是否轮询
        boolean flag = lockRedis.isPolling();
        if (!flag) {
            jedis.close();
            return;
        } else {
            int pollingIntervalTime = lockRedis.pollingIntervalTime();
            try {
                Thread.sleep(1000L * pollingIntervalTime);
                log.warn("Thread.sleep(),pollingIntervalTime==[{}]", pollingIntervalTime);
                isGetLockRedis(joinPoint);
            } catch (InterruptedException e) {
                log.error("处理是否需要轮询出现异常e = [{}]", e);
            } finally {
                jedis.close();
            }
        }
    }


    /**
     * 释放锁资源及设置过期时间
     * @param lockRedis
     * @param jedis
     * @param redisKey
     */
    private void manageLockRedis(LockRedis lockRedis, Jedis jedis, String redisKey, String redisValue) {
        boolean flag = lockRedis.isDelayReleaseLock();
        String value = jedis.get(redisKey);
        if (!flag) {
            //数据值相同进行删除
            if (Objects.equals(redisValue,value)) {
                jedis.del(redisKey);
            }
        } else {
            if (Objects.equals(redisValue,value)) {
                jedis.expire(redisKey, lockRedis.leaseTime());
            }
        }
        jedis.close();
    }


}

package com.yajie.springboot.learn.annotation;

import java.lang.annotation.*;

/**
 * 〈说明〉<br>
 * 〈〉
 *
 * @author mao
 * @Date: 2019/1/25 0025
 * @Description:
 * @since 1.0.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LockRedis {
    /**
     * 锁的过期时间时间，防止死锁(秒)
     * @return
     */
    long expireTime() default 300;

    /**
     *上锁以后xxx秒自动解锁
     * @return
     */
    int leaseTime() default 30;

    /**
     * 是否延迟释放锁
     * @return
     */
    boolean isDelayReleaseLock() default false;

    /**
     * 当不能获取锁时，是否轮询请求(如果不轮询则终止请求)
     * @return
     */
    boolean isPolling() default false;

    /**
     * 轮询请求间隔时间(秒）
     * @return
     */
    int pollingIntervalTime() default 1;

    /**
     * 注释
     * @return
     */
    String remark() default "备注";
}

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
@Target({ElementType.PARAMETER,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExceptionNotice {

    /** 需要通知人员的花名： **/
    String noticeNickName() default "yajie";

    /** 通知消息的备注内容： **/
    String noticeRemarks() default "无";

    /**
     * 消息发送类型(位图,目前三位,短信,钉钉,邮件)
     * 0表示不发送，1表示发送
     * @return
     */
    String sendWayType() default "1";
}

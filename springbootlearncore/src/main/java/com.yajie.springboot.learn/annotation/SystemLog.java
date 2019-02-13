package com.yajie.springboot.learn.annotation;

import java.lang.annotation.*;


@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemLog {
	// 模块名称
	String moduleName() default "";
	// 日志描述
	String description() default "";

}

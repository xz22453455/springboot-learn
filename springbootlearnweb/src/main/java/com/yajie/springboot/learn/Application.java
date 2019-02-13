package com.yajie.springboot.learn; /**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: application
 * Author:   Administrator
 * Date:     2019/1/23 0023 9:12
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2019/1/23 0023
 * @since 1.0.0
 */
@EnableScheduling
@EnableCaching
//@ImportResource(locations = { "classpath:dubbo/dubbo-all.xml" })
@ComponentScan({"com.yajie.springboot"})
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        // 程序启动入口
        // 启动嵌入式的 Tomcat 并初始化 Spring 环境及其各 Spring 组件
        SpringApplication.run(Application.class,args);
    }
}

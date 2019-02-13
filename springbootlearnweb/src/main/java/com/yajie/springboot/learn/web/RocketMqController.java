/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: RocketMqController
 * Author:   Administrator
 * Date:     2019/1/23 0023 13:07
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yajie.springboot.learn.web;

import com.yajie.springboot.learn.service.IRocketMqService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 〈一句话功能简述〉<br> 
 * @create 2019/1/23 0023
 */
@Slf4j
@RestController
public class RocketMqController {
    @Autowired
    private IRocketMqService iRocketMqService;

    @RequestMapping(value = "mqinit",method = RequestMethod.GET)
    public Object sendMessage(){
        log.info("开始mq初始化任务");
        try {
            iRocketMqService.sendMessage();
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        return Boolean.TRUE;
    }
}

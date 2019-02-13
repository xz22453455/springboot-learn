/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: RocketMqServiceImpl
 * Author:   Administrator
 * Date:     2019/1/23 0023 11:14
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yajie.springboot.learn.service.impl;

import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.MessageQueueSelector;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.yajie.springboot.learn.common.domain.BizResult;
import com.yajie.springboot.learn.service.IRocketMqService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2019/1/23 0023
 * @since 1.0.0
 */
@Slf4j
@Service
public class RocketMqServiceImpl implements IRocketMqService {
    
    @Autowired
    private DefaultMQProducer defaultMQProducer;
    
    @Override
    public BizResult<Boolean> sendMessage() throws Exception {
        for (int i = 0; i < 5; i++) {
            Message message = new Message("TEST",
                    "first",
                    "aa",//key用于标识业务的唯一性
                    ("helloRocketMq~~~~~"+"---"+i).getBytes()// body 二进制字节数组
            );
            SendResult send = defaultMQProducer.send(message);
            System.out.println(send);

        }
        return new BizResult<>();
    }
}

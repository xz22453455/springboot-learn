package com.yajie.springboot.learn.service;

import com.yajie.springboot.learn.common.model.MessageMarkDownModel;
import com.yajie.springboot.learn.common.model.MessageTextModel;

/**
 * Created by jiangjingming on 2017/8/5.
 */
public interface IDingDingRobotService {

    Boolean sendDingDingRobotMarkMessage(MessageMarkDownModel messageModel);

    Boolean sendDingDingRobotTextMessage(MessageTextModel messageTextModel);
}

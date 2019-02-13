package com.yajie.springboot.learn.web;

import com.google.common.collect.Lists;
import com.yajie.springboot.learn.model.MessageTextModel;
import com.yajie.springboot.learn.service.IDingDingRobotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by jiangjingming on 2017/8/5.
 */
@Slf4j
@RequestMapping(value = "ding")
@RestController
public class DingDingController {
    @Autowired
    private IDingDingRobotService dingDingRobotService;

    @RequestMapping(value = "sendMark", method = RequestMethod.GET)
    public Boolean DingDingSendMessageMark() {
        return dingDingRobotService.sendDingDingRobotMarkMessage(null);
    }

    @RequestMapping(value = "sendText/{text}/{phoneNum}", method = RequestMethod.GET)
    public Boolean DingDingSendMessageText(@PathVariable("text") String text, @PathVariable("phoneNum") String phoneNum) {
        MessageTextModel messageTextModel = new MessageTextModel();
        MessageTextModel.Text textContent = new MessageTextModel.Text();
        MessageTextModel.At at = new MessageTextModel.At();
        at.setAtAll(false);
        at.setAtMobiles(Lists.newArrayList(phoneNum));
        textContent.setContent(text);
        messageTextModel.setAt(at);
        messageTextModel.setText(textContent);
        return dingDingRobotService.sendDingDingRobotTextMessage(messageTextModel);
    }
}

package com.yajie.springboot.learn.service.impl;

import com.alibaba.fastjson.JSON;
import com.yajie.springboot.learn.model.MessageMarkDownModel;
import com.yajie.springboot.learn.model.MessageTextModel;
import com.yajie.springboot.learn.service.IDingDingRobotService;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by jiangjingming on 2017/8/5.
 */
@Slf4j
@Service
public class DingDingRobotServiceImpl implements IDingDingRobotService {
    private static final String DING_DING_ROBOT_URL = "https://oapi.dingtalk.com/robot/send?access_token=9319d10b330c4c091d866c50c3023f6aa7df0f1e375bef058d2380dfa56fe6ea";
    @Override
    public Boolean sendDingDingRobotMarkMessage(MessageMarkDownModel messageMarkDownModel) {
        messageMarkDownModel = new MessageMarkDownModel();
        messageMarkDownModel.setMsgtype("markdown");
        MessageMarkDownModel.Markdown markdown = new MessageMarkDownModel.Markdown();
        markdown.setTitle("# 注意注意");
        markdown.setText("标题\n" +
                "# 一级标题\n" +
                "## 二级标题\n" +
                "### 三级标题\n" +
                "#### 四级标题");
        MessageMarkDownModel.At at = new MessageMarkDownModel.At();
        at.setAtAll(true);
        //at.setAtMobiles(Lists.newArrayList("17621616291"));
        messageMarkDownModel.setMarkdown(markdown);
        messageMarkDownModel.setAt(at);
        String textMsg = JSON.toJSONString(messageMarkDownModel);
        HttpPost httppost = new HttpPost(DING_DING_ROBOT_URL);
        httppost.addHeader("Content-Type", "application/json; charset=utf-8");
        StringEntity se = new StringEntity(textMsg, "utf-8");
        httppost.setEntity(se);
        return sendHttpCommon(httppost);
    }

    @Override
    public Boolean sendDingDingRobotTextMessage(MessageTextModel messageTextModel) {
//        messageTextModel = new MessageTextModel();
//        MessageTextModel.Text textContent = new MessageTextModel.Text();
//        textContent.setContent("小易易口腔溃疡好了没？");
//        MessageTextModel.At at = new MessageTextModel.At();
//        at.setAtAll(true);
//        //at.setAtMobiles(Lists.newArrayList("17621616291"));
//        messageTextModel.setText(textContent);
//        messageTextModel.setAt(at);
        String textMsg = JSON.toJSONString(messageTextModel);
        HttpPost httppost = new HttpPost(DING_DING_ROBOT_URL);
        httppost.addHeader("Content-Type", "application/json; charset=utf-8");
        StringEntity se = new StringEntity(textMsg, "utf-8");
        httppost.setEntity(se);
        return sendHttpCommon(httppost);
    }

    private Boolean sendHttpCommon(HttpPost httppost) {
        try {
            HttpClient httpclient = HttpClients.createDefault();
            HttpResponse response = httpclient.execute(httppost);
            if (response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
                String result= EntityUtils.toString(response.getEntity(), "utf-8");
                System.out.println(result);
                return Boolean.TRUE;
            }
        } catch (IOException e) {
            log.error("发送钉钉消息异常,异常信息为：{}",e);
        }
        return Boolean.FALSE;
    }

}

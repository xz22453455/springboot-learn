package com.yajie.springboot.learn.service;

import com.yajie.springboot.learn.common.domain.BizResult;

/**
 * 〈说明〉<br>
 * 〈〉
 *
 * @author mao
 * @Date: 2019/1/23 0023
 * @Description:
 * @since 1.0.0
 */
public interface IRocketMqService {
    BizResult<Boolean> sendMessage() throws Exception;
}

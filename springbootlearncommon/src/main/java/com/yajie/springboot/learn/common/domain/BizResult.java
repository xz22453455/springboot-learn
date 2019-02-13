package com.yajie.springboot.learn.common.domain;

import lombok.Data;

import java.util.Map;

/**
 * 〈说明〉<br>
 * 〈〉
 *
 * @author mao
 * @Date: 2019/1/23 0023
 * @Description:
 * @since 1.0.0
 */
@Data
public class BizResult<T> {
    private boolean success = false;
    private String code;
    private String message;
    private T data;
    private Map<String, Object> extraInfo;
}

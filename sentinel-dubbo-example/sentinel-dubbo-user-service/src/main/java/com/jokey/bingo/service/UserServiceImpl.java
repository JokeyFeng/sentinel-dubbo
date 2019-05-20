package com.jokey.bingo.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jokey.bingo.util.TraceIdUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Zhengjingfeng
 * @created 2019/4/17 11:45
 * @comment
 */
@Slf4j
@Service(version = "1.0.0", interfaceClass = UserService.class)
public class UserServiceImpl implements UserService {

    @Override
    public String get(String message) {
        String traceId = TraceIdUtil.getTraceId();
        log.info("[服务提供方]测试服务提供方,traceId: {}", traceId);
        return message;
    }
}

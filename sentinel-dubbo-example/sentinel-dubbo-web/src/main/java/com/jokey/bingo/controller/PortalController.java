package com.jokey.bingo.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jokey.bingo.common.TraceIdGenerator;
import com.jokey.bingo.service.UserService;
import com.jokey.bingo.util.TraceIdUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Zhengjingfeng
 * @created 2019/4/17 14:15
 * @comment
 */
@Slf4j
@RestController
public class PortalController {

    @Reference(version = "1.0.0")
    private UserService userService;

    @GetMapping("/get")
    public Object get(@RequestParam String message) {
        TraceIdGenerator.createTraceId(message);
        String traceId = TraceIdUtil.getTraceId();
        log.info("[服务消费方]执行业务逻辑开始,traceId: {}", traceId);
        return userService.get(message);
    }
}

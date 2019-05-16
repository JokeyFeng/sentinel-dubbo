package com.jokey.bingo.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jokey.bingo.service.UserService;
import com.jokey.bingo.service.common.ApplicationUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Zhengjingfeng
 * @created 2019/4/17 14:15
 * @comment
 */
@RestController
@RequestMapping("/portal")
public class PortalController {

    @Reference(version = "1.0.0")
    private UserService userService;

    @GetMapping("/get")
    public Object get(@RequestParam String message) {
        System.out.println(Thread.currentThread().getName());
        ApplicationUtil.setSessionUserInfo(message);
        System.out.println(ApplicationUtil.getSessionUserInfo());
        return userService.get(message);
    }
}

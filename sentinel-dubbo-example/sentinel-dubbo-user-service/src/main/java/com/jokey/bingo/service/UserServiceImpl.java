package com.jokey.bingo.service;

import com.alibaba.dubbo.config.annotation.Service;

/**
 * @author Zhengjingfeng
 * @created 2019/4/17 11:45
 * @comment
 */
@Service(version = "1.0.0", interfaceClass = UserService.class)
public class UserServiceImpl implements UserService {

    @Override
    public String get(String message) {
        return message;
    }
}

package com.jokey.bingo;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author Zhengjingfeng
 * @created 2019/4/17 11:29
 * @comment
 */
@SpringBootApplication
@NacosPropertySource(dataId = "example", autoRefreshed = true)
public class UserServiceApplication {

    private static Logger logger = LoggerFactory.getLogger(UserServiceApplication.class);

    public static void main(String[] args) {
        logger.info("用户服务开始启动......");
        ConfigurableApplicationContext applicationContext = SpringApplication.run(UserServiceApplication.class, args);
        logger.info("用户服务完成启动......");
    }
}

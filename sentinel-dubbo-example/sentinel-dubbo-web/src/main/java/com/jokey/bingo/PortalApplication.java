package com.jokey.bingo;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Zhengjingfeng
 * @created 2019/4/17 14:12
 * @comment
 */

@NacosPropertySource(dataId = "portal", autoRefreshed = true)
@SpringBootApplication
public class PortalApplication {
    public static void main(String[] args) {
        SpringApplication.run(PortalApplication.class, args);
    }
}

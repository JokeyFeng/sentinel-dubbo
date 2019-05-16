package com.jokey.bingo;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import com.jokey.bingo.service.common.ApplicationUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * @author Zhengjingfeng
 * @created 2019/4/17 14:12
 * @comment
 */

@NacosPropertySource(dataId = "portal", autoRefreshed = true)
@SpringBootApplication
@Import({ApplicationUtil.class})
public class PortalApplication {
    public static void main(String[] args) {
        SpringApplication.run(PortalApplication.class, args);
    }
}

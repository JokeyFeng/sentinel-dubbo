# Nacos、Dubbo、Sentinel、Spring-boot集成

####1、启动Sentinel Dashboard 客户端控制台
前往 https://github.com/alibaba/Sentinel/releases 下载sentinel-dashboard-1.5.0.jar(基于spring-boot-2.0.5版本)
启动命令:
    java -Dserver.port=8080 -Dcsp.sentinel.dashboard.server=127.0.0.1:8080 -Dproject.name=sentinel-dashboard  -jar F:\dubbo\sentinel-dashboard-1.5.0.jar
访问 http://127.0.0.1:8080 进入控制台
 
####2、启动Nacos注册配置管理中心(具有服务注册发现、配置管理功能(基于数据库))
前往 https://github.com/alibaba/nacos/releases 下载压缩包 nacos-server-1.0.0.zip
解压后，进入项目目录/nacos/bin，执行startup.cmd脚本，即可启动一个注册配置中心。
访问 http://127.0.0.1:8848/nacos/index.html 进入控制台，默认账号：nacos 密码：nacos
如果要添加数据库相关信息，进入/nacos/conf目录下，在application.properties末尾添加：

**MYSQL**
spring.datasource.platform=mysql
db.num=1
db.url.0=jdbc:mysql://127.0.0.1:3306/nacos_config?characterEncoding=utf8&autoReconnect=true
db.user=root
db.password=密码
**nacos_config数据库**的相关建表SQL在/nacos/conf/nacos-mysql.sql文件里面


####3、服务提供者工程(sentinel-dubbo-user-service)
在pom.xml文件中引入dubbo、nacos、sentinel的依赖:
        <dependency>
            <groupId>com.alibaba.boot</groupId>
            <artifactId>dubbo-spring-boot-starter</artifactId>
            <version>0.2.1.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>dubbo</artifactId>
            <version>2.6.5</version>
        </dependency>
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>dubbo-registry-nacos</artifactId>
            <version>0.0.1</version>
        </dependency>
        <!--nacos配置管理功能-->
        <dependency>
            <groupId>com.alibaba.boot</groupId>
            <artifactId>nacos-config-spring-boot-starter</artifactId>
            <version>0.2.1</version>
        </dependency>
        <!-- Sentinel熔断降级-->
        <dependency>
            <groupId>com.alibaba.csp</groupId>
            <artifactId>sentinel-dubbo-adapter</artifactId>
            <version>1.5.0</version>
        </dependency>
        <dependency>
            <groupId>com.alibaba.csp</groupId>
            <artifactId>sentinel-transport-simple-http</artifactId>
            <version>1.5.0</version>
        </dependency>
        <!--热点参数限流-->
        <dependency>
            <groupId>com.alibaba.csp</groupId>
            <artifactId>sentinel-parameter-flow-control</artifactId>
            <version>1.5.0</version>
        </dependency>


启动类上添加@NacosPropertySource(dataId = "example", autoRefreshed = true)，该注解是给配置中心使用，example是sentinel默认的dataID，可以自行定义，但要与数据库的文件保持一致
在application.properties配置文件上添加：
server.port=8888
nacos.config.server-addr=127.0.0.1:8848
dubbo.scan.base-packages=com.jokey.bingo.service
dubbo.registry.address=nacos://127.0.0.1:8848
dubbo.application.name=user-center-dubbo
dubbo.protocol.port=12345
dubbo.consumer.check=false

服务启动命令添加JVM参数 
-Dserver.port=8888 -Dcsp.sentinel.dashboard.server=127.0.0.1:8080 -Dproject.name=user-service-provider
成功运行后便可以在nacos和sentinel控制台看到该服务的相关信息


####4、服务消费者(sentinel-dubbo-web)
和上面的步骤3一样
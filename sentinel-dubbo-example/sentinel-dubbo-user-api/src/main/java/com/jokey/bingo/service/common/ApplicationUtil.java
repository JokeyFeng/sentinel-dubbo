package com.jokey.bingo.service.common;

import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.ttl.TransmittableThreadLocal;
import org.springframework.context.ApplicationContext;

/**
 * @author Zhengjingfeng
 * @created 2019/5/16 16:35
 * @comment
 */
public class ApplicationUtil {

    private static ApplicationContext applicationContext = null;

    private static TransmittableThreadLocal<String> localUserInfo = new TransmittableThreadLocal<>();

    public static void setApplicationContext(ApplicationContext applicationContext) {
        if (ApplicationUtil.applicationContext == null) {
            ApplicationUtil.applicationContext = applicationContext;
        }
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }


    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);

    }

    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 获取当前用户信息
     *
     * @return
     */
    public static String getSessionUserInfo() {
        String message = getSessionUserInfoByRpcContext();
        if (null == message) {
            message = localUserInfo.get();
        }
        return message;
    }

    public static void setSessionUserInfo(String userInfo) {
        if (userInfo != null) {
            localUserInfo.set(userInfo);
        }
    }

    /**
     * 获取当前用户信息
     *
     * @return
     */
    public static String getSessionUserInfoByRpcContext() {
        String message = RpcContext.getContext().getAttachment("message");
        if (message == null) {
            return null;
        }
        setSessionUserInfo(message);

        return message;
    }
}
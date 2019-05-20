package com.jokey.bingo.common;

import com.jokey.bingo.util.TraceIdUtil;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author Zhengjingfeng
 * @created 2019/5/17 15:26
 * @comment
 */
public class TraceIdGenerator {
    /**
     * 消费端创建TraceId,并设置到线程上下文中
     * 该方法只调用一次
     *
     * @return
     */
    public static String createTraceId() {
        // 创建的同时就设置到上下文中
        String traceId = getTraceId();
        TraceIdUtil.setTraceId(traceId);
        return traceId;
    }

    public static String createTraceId(String var) {
        TraceIdUtil.setTraceId(var);
        return var;
    }

    /**
     * 生成32位traceId
     *
     * @return
     */
    private static String getTraceId() {
        StringBuilder result = new StringBuilder();
        String ip;

        // 获取本地ipv4地址
        try {
            InetAddress address = InetAddress.getLocalHost();
            ip = address.getHostAddress();
        } catch (Exception var5) {
            return result.toString();
        }

        // 根据.截取为String数组
        String[] ipAddressInArray = ip.split("\\.");
        // 拼装为字符串,将每一个元素转换为16进制
        for (int i = 3; i >= 0; --i) {
            Integer id = Integer.parseInt(ipAddressInArray[3 - i]);
            result.append(String.format("%02x", id));
        }
        // 拼装时间戳及随机数
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        result.append(simpleDateFormat.format(new Date())).append(UUID.randomUUID().toString(), 0, 7);
        return result.toString();
    }

}

package com.jokey.bingo.util;

import com.alibaba.ttl.TransmittableThreadLocal;

/**
 * @author Zhengjingfeng
 * @created 2019/5/17 15:27
 * @comment
 */
public class TraceIdUtil {
    /**
     * 使用TransmittableThreadLocal便于在父子线程间传递参数
     */
    private static final ThreadLocal<String> TRACE_ID = new TransmittableThreadLocal<>();
   // private static final ThreadLocal<String> TRACE_ID = new ThreadLocal<>();

    public TraceIdUtil() {
    }

    /**
     * 从当前线程局部变量获取TraceId
     * 首次调用该方法会生成traceId，后续每次都从线程上下文获取
     *
     * @return
     */
    public static String getTraceId() {
        return TRACE_ID.get();
    }

    public static void setTraceId(String traceId) {
        TRACE_ID.set(traceId);
    }

    public static void removeTraceId() {
        TRACE_ID.remove();
    }
}

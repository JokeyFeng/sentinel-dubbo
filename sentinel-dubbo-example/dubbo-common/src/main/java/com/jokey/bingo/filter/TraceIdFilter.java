package com.jokey.bingo.filter;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.rpc.*;
import com.jokey.bingo.common.TraceIdGenerator;
import com.jokey.bingo.util.TraceIdUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Zhengjingfeng
 * @created 2019/5/17 15:49
 * @comment TraceId Dubbo过滤器，用于在服务之间传递TraceId
 * 根据服务端/消费端选择具体的TraceId传递策略
 */
@Activate(group = {Constants.PROVIDER, Constants.CONSUMER})
public class TraceIdFilter implements Filter {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private final static String TRACE_ID = "trace_id";

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        RpcContext rpcContext = RpcContext.getContext();
        String traceId;
        if (rpcContext.isConsumerSide()) {
            if (StringUtils.isBlank(TraceIdUtil.getTraceId())) {
                // 根调用，生成TraceId
                traceId = TraceIdGenerator.createTraceId();
                LOGGER.debug("[消费者] 线程变量TraceId为空,新建TraceId: {}", traceId);
            } else {
                // 后续调用，从Rpc上下文取出并设置到线程上下文
                traceId = TraceIdUtil.getTraceId();
                LOGGER.debug("[消费者] 线程变量存在TraceId: {}", traceId);
            }
            TraceIdUtil.setTraceId(traceId);
            RpcContext.getContext().setAttachment(TRACE_ID, TraceIdUtil.getTraceId());
            LOGGER.debug("[消费者] 将线程变量TraceId设置到RPC上下文RpcContext,TraceId: {}", traceId);
        }
        if (rpcContext.isProviderSide()) {
            // 服务提供方，从Rpc上下文获取traceId
            traceId = RpcContext.getContext().getAttachment(TRACE_ID);
            TraceIdUtil.setTraceId(traceId);
            LOGGER.debug("[提供者] 从RPC上下文RpcContext获取TraceId, and set it to other trace Context,TraceId: {}", traceId);
        }
        return invoker.invoke(invocation);
    }
}
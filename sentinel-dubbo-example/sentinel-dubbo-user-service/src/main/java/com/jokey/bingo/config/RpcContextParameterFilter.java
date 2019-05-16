package com.jokey.bingo.config;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
import com.jokey.bingo.service.common.ApplicationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * dubbo调用拦截器, 拦截所有dubbo接口
 *
 * @author chenfenghai
 * @date 2019/05/13
 */
@Activate(group = Constants.CONSUMER, order = -20000)
public class RpcContextParameterFilter implements Filter {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        String message = ApplicationUtil.getSessionUserInfo();
        if (message != null) {
            RpcContext.getContext().setAttachment("message", message);
        }
        return invoker.invoke(invocation);
    }
}

package com.jokey.bingo.thread;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.threadpool.ThreadPool;
import com.alibaba.dubbo.common.threadpool.support.AbortPolicyWithReport;
import com.alibaba.dubbo.common.utils.NamedThreadFactory;
import com.alibaba.ttl.threadpool.TtlExecutors;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author Zhengjingfeng
 * @created 2019/5/16 15:40
 * @comment
 */
@Slf4j
public class MyDubboThreadPool implements ThreadPool {

    @Override
    public Executor getExecutor(URL url) {
        log.info("###########=====加载自定义线程池=====######");
        int threads = url.getParameter(Constants.THREADS_KEY, Constants.DEFAULT_THREADS);
        int queues = url.getParameter(Constants.QUEUES_KEY, Constants.DEFAULT_QUEUES);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(threads, threads, 0, TimeUnit.MILLISECONDS,
                queues == 0 ? new SynchronousQueue<>() :
                        (queues < 0 ? new LinkedBlockingQueue<>()
                                : new LinkedBlockingQueue<>(queues)),
                new NamedThreadFactory("OriStar", true), new AbortPolicyWithReport("OriStar", url));

        return TtlExecutors.getTtlExecutorService(executor);
    }
}

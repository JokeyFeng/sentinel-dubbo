package com.jokey.bingo.config;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.threadpool.ThreadPool;
import com.alibaba.dubbo.common.threadpool.support.AbortPolicyWithReport;
import com.alibaba.dubbo.common.utils.NamedThreadFactory;
import com.alibaba.ttl.threadpool.TtlExecutors;

import java.util.concurrent.*;

/**
 * @author Zhengjingfeng
 * @created 2019/5/16 15:40
 * @comment
 */
public class MyDubboThreadPool implements ThreadPool {

    @Override
    public Executor getExecutor(URL url) {
        System.out.println("加载自定义线程..........");
        String name = url.getParameter(Constants.THREAD_NAME_KEY, Constants.DEFAULT_THREAD_NAME);
        // int threads = url.getParameter(Constants.THREADS_KEY, Constants.DEFAULT_THREADS);
        //  int queues = url.getParameter(Constants.QUEUES_KEY, Constants.DEFAULT_QUEUES);
        int queues = 1;
        int threads = 1;
        ThreadPoolExecutor executor = new ThreadPoolExecutor(threads, threads, 0, TimeUnit.MILLISECONDS,
                queues == 0 ? new SynchronousQueue<>() :
                        (queues < 0 ? new LinkedBlockingQueue<>()
                                : new LinkedBlockingQueue<>(queues)),
                new NamedThreadFactory("oristar-dubbo", true), new AbortPolicyWithReport("oristar-dubbo", url));

        return TtlExecutors.getTtlExecutorService(executor);
    }
}

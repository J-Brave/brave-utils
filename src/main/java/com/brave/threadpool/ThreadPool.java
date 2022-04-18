package com.brave.threadpool;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author jbrave
 */
@Configuration
public class ThreadPool implements AsyncConfigurer {

    /**
     * 核心线程数
     */
    private static final int corePoolSize = 5;
    /**
     * 最大线程数
     */
    private static final int maxPoolSize = 20;
    /**
     * 线程空闲时间
     */
    private static final int keepAliveTime = 10;
    /**
     * 缓冲队列数
     */
    private static final int queueCapacity = 500;

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
        pool.setThreadNamePrefix("**全局线程池-实现类**");
        pool.setCorePoolSize(corePoolSize);
        pool.setMaxPoolSize(maxPoolSize);
        pool.setKeepAliveSeconds(keepAliveTime);
        pool.setQueueCapacity(queueCapacity);
        // 直接在execute方法的调用线程中运行
        pool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 初始化
        pool.initialize();
        return pool;
    }

}

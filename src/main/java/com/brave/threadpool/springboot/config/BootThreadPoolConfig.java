package com.brave.threadpool.springboot.config;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author jbrave
 */
@Configuration
@Slf4j
public class BootThreadPoolConfig {

    //获取当前系统的CPU数目
    static int cpuNums = Runtime.getRuntime().availableProcessors();
    //核心线程数o
    private static final int coolPoolSize = 10;
    //最大线程数
    private static final int maxNumPoolSize = cpuNums * 5;

    //@Bean
    public ExecutorService buildThreadPool() {
        log.info("创建线程数: {}, 最大线程数: {}", coolPoolSize, maxNumPoolSize);
        return new ThreadPoolExecutor(coolPoolSize, maxNumPoolSize, 0L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(100), new ThreadFactoryBuilder().setNameFormat("PROS-%d").build());
    }

}

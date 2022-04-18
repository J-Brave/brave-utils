package com.brave.threadpool.staticmethod;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author jbrave
 */
@Slf4j
public class StaticMethodThreadPool {

    /**
     * 1. 不可使用Executors的方法创建线程池, 这个是大量的生产事故得出来的结论
     * 2. new ThreadFactoryBuilder().setNameFormat("PROS-%d").build() 给每个线程已名字，可以方便调试 - guava
     */

    //获取当前系统的CPU数目
    static int cpuNums = Runtime.getRuntime().availableProcessors();
    //核心线程数
    private static final int coolPoolSize = 10;
    //最大线程数
    private static final int maxNumPoolSize = cpuNums * 5;

    public static ExecutorService executorService = null;
    /**
     * 静态方法
     */
    static {
        log.info("创建线程数: {}, 最大线程数: {}", coolPoolSize, maxNumPoolSize);
        executorService = new ThreadPoolExecutor(coolPoolSize, maxNumPoolSize, 0L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(100), new ThreadFactoryBuilder().setNameFormat("PROS-%d").build());
    }

    public static void main(String[] args) {
        StaticMethodThreadPool.executorService.execute(() -> {
            log.info(Thread.currentThread().getName());
        });
        StaticMethodThreadPool.executorService.execute(() -> {
            log.info(Thread.currentThread().getName());
        });
        StaticMethodThreadPool.executorService.execute(() -> {
            log.info(Thread.currentThread().getName());
        });
    }

}

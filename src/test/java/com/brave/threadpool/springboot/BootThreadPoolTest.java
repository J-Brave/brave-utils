package com.brave.threadpool.springboot;

import com.brave.threadpool.springboot.config.BootThreadPoolConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author jbrave
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class BootThreadPoolTest {

    @Autowired
    private BootThreadPoolConfig bootThreadPoolConfig;

    @Test
    public void test1() {
        bootThreadPoolConfig.buildThreadPool().execute(() -> {log.info("线程: {}, 执行", Thread.currentThread().getName());});
        bootThreadPoolConfig.buildThreadPool().execute(() -> {log.info("线程: {}, 执行", Thread.currentThread().getName());});
        bootThreadPoolConfig.buildThreadPool().execute(() -> {log.info("线程: {}, 执行", Thread.currentThread().getName());});
        bootThreadPoolConfig.buildThreadPool().execute(() -> {log.info("线程: {}, 执行", Thread.currentThread().getName());});
        bootThreadPoolConfig.buildThreadPool().execute(() -> {log.info("线程: {}, 执行", Thread.currentThread().getName());});
        bootThreadPoolConfig.buildThreadPool().execute(() -> {log.info("线程: {}, 执行", Thread.currentThread().getName());});
    }

}

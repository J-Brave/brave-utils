package com.brave.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author: Qiang.cao
 * Date: 2022/3/25
 * Time: 17:59
 * Description:
 */
@Service
@Slf4j
public class TestService {

    @Async("taskExecutor")
    public void test1() {
       log.info("线程: {}, 执行", Thread.currentThread().getName());
    }

    @Async
    public void test2() {
        log.info("线程: {}, 执行", Thread.currentThread().getName());
    }

}

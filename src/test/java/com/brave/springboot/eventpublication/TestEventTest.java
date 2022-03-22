package com.brave.springboot.eventpublication;

import com.brave.springboot.eventpublication.enums.EventEnum;
import com.brave.springboot.eventpublication.publish.TransactionEvent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author jbrave
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestEventTest {

    @Autowired
    private ApplicationEventPublisher publisher;

    /**
     * source: 业务数据
     */
    @Test
    public void test1() {
        publisher.publishEvent(new TransactionEvent("aaa", EventEnum.TEST));
    }

}
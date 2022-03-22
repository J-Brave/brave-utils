package com.brave.springboot.eventpublication.listener;

import com.brave.springboot.eventpublication.enums.EventEnum;
import com.brave.springboot.eventpublication.publish.TransactionEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * @author jbrave
 */
@Component
public class EventListener {

    private final static Logger LOGGER = LoggerFactory.getLogger(EventListener.class);

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void doEvent(TransactionEvent event) {
        if (EventEnum.TEST == event.getEventEnum()) {
            LOGGER.info("事件: {} 执行", EventEnum.TEST.getOperator());
        }
    }

}

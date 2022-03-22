package com.brave.springboot.eventpublication.publish;

import com.brave.springboot.eventpublication.enums.EventEnum;
import com.brave.springboot.eventpublication.model.EventModel;
import org.springframework.context.ApplicationEvent;

/**
 * @author jbrave
 */
public class TransactionEvent extends ApplicationEvent {

    private EventEnum eventEnum;

    private EventModel eventModel;

    public TransactionEvent(Object source, EventEnum eventEnum) {
        super(source);
        this.eventEnum = eventEnum;
    }

    public EventEnum getEventEnum() {
        return eventEnum;
    }

    public void setEventEnum(EventEnum eventEnum) {
        this.eventEnum = eventEnum;
    }

    public EventModel getEventModel() {
        return eventModel;
    }

    public void setEventModel(EventModel eventModel) {
        this.eventModel = eventModel;
    }
}

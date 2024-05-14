package com.demolistener.listener.event;

import org.springframework.context.ApplicationEvent;

public class OrderedEvent extends ApplicationEvent {
    public OrderedEvent(Object source) {
        super(source);
    }
}

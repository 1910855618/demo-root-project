package com.demolistener.listener.event;

import org.springframework.context.ApplicationEvent;

public class AsyncCustomEvent extends ApplicationEvent {
    public AsyncCustomEvent(Object source) {
        super(source);
    }
}

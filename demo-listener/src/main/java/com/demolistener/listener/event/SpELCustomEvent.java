package com.demolistener.listener.event;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class SpELCustomEvent extends ApplicationEvent {
    private boolean flag;

    public SpELCustomEvent(Object source) {
        super(source);
    }
}

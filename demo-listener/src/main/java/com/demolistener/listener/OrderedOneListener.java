package com.demolistener.listener;

import com.demolistener.listener.event.OrderedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderedOneListener implements Ordered {
    @EventListener
    public void asyncCustomEvent(OrderedEvent event) {
        log.info("监听到自己发布的排序事件(高优先级), {}", event);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}

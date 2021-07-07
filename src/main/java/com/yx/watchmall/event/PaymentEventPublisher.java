package com.yx.watchmall.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class PaymentEventPublisher {
    private ApplicationEventPublisher publisher;

    @Autowired
    public void setApplicationEventPublisher(ApplicationEventPublisher eventPublisher) {
        publisher = eventPublisher;
    }

    public void publishPaymentEvent(final PaymentEvent paymentEvent) {
        publisher.publishEvent(paymentEvent);
    }
}

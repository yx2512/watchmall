package com.yx.watchmall.listener;

import com.yx.watchmall.event.PaymentEvent;
import com.yx.watchmall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentListener {
    private OrderService orderService;

    @Autowired
    public void setOrderService(OrderService service) {
        orderService = service;
    }

    @EventListener
    public void paymentEventHandler(PaymentEvent paymentEvent) {
        if(paymentEvent != null) {
            if(paymentEvent.getPlatformStatus().equals("success")) {
                orderService.paid(paymentEvent.getOrderNo());
            }
        }
    }
}

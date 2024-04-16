package com.age2001.order.service.controller;

import com.age2001.order.service.payload.OrderEvent;
import com.age2001.order.service.service.OrderInformationService;
import com.age2001.order.service.service.kafka.OrderProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequestMapping("/api/orders")
@RestController
public class OrderController {

    @Autowired
    private OrderProducer orderProducer;

    @Autowired
    private OrderInformationService orderInformationService;

    @PostMapping("/placed")
    public String placeOrder(@RequestBody OrderEvent orderEvent) {
        OrderEvent newOrderEvent = new OrderEvent();
        newOrderEvent.setOrderId(UUID.randomUUID().toString());
        newOrderEvent.setStatus("PENDING");
        newOrderEvent.setName(orderEvent.getName());
        newOrderEvent.setQuantity(orderEvent.getQuantity());
        newOrderEvent.setPrice(orderEvent.getPrice());

        orderInformationService.createOrderInformation(newOrderEvent);

        orderProducer.sendMessage(newOrderEvent);
        return "Order placed successfully";
    }
}

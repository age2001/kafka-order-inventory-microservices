package com.age2001.order.service.service.kafka;

import com.age2001.order.service.entity.OrderInformation;
import com.age2001.order.service.payload.OrderEvent;
import com.age2001.order.service.service.OrderInformationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ItemInformationConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(ItemInformationConsumer.class);

    @Autowired
    private OrderInformationService orderInformationService;

    @KafkaListener(
            topics = "inventory-topic",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(OrderEvent orderEvent) {
        LOGGER.info(String.format("order event received in order service => %s", orderEvent.toString()));

        OrderInformation orderInformation = orderInformationService.getOrderInformationByOrderId(orderEvent.getOrderId());

        orderInformation.setStatus(orderEvent.getStatus());

        orderInformationService.updateOrderInformation(orderInformation);
    }
}

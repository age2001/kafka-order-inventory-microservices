package com.age2001.inventory.service.service.kafka;

import com.age2001.inventory.service.entity.Item;
import com.age2001.inventory.service.payload.OrderEvent;
import com.age2001.inventory.service.repository.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderConsumer.class);

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemInformationProducer itemInformationProducer;

    @KafkaListener(
            topics = "order-topic",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(OrderEvent orderEvent) {
        LOGGER.info(String.format("order event received in inventory service => %s", orderEvent.toString()));

        String itemName = orderEvent.getName();
        Item itemInDB = itemRepository.findByName(itemName);
        OrderEvent newOrderEvent = orderEvent;

        if (itemInDB == null) {
            newOrderEvent.setStatus("INVALID");
            itemInformationProducer.sendMessage(newOrderEvent);
            return;
        }
        if (itemInDB.getQuantity() < orderEvent.getQuantity()) {
            newOrderEvent.setStatus("INVALID");
            itemInformationProducer.sendMessage(newOrderEvent);
            return;
        }

        itemInDB.setQuantity(itemInDB.getQuantity() - orderEvent.getQuantity());
        itemRepository.save(itemInDB);

        newOrderEvent.setStatus("VALID");
        System.out.println(newOrderEvent.toString());
        itemInformationProducer.sendMessage(newOrderEvent);
    }
}

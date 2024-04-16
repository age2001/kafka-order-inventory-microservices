package com.age2001.order.service.service.impl;

import com.age2001.order.service.entity.OrderInformation;
import com.age2001.order.service.payload.OrderEvent;
import com.age2001.order.service.repository.OrderInformationRepository;
import com.age2001.order.service.service.OrderInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderInformationServiceImpl implements OrderInformationService {

    @Autowired
    private OrderInformationRepository orderInformationRepository;

    @Override
    public OrderInformation createOrderInformation(OrderEvent orderEvent) {
        OrderInformation orderInformation = new OrderInformation();
        orderInformation.setName(orderEvent.getName());
        orderInformation.setQuantity(orderEvent.getQuantity());
        orderInformation.setStatus(orderEvent.getStatus());
        orderInformation.setTotal_price(orderEvent.getQuantity() * orderEvent.getPrice());
        orderInformation.setOrderId(orderEvent.getOrderId());

        return orderInformationRepository.save(orderInformation);
    }

    @Override
    public List<OrderInformation> getAllOrderInformation() {
        return orderInformationRepository.findAll();
    }

    @Override
    public OrderInformation updateOrderInformation(Long id) {
        return null;
    }

    @Override
    public OrderInformation getOrderInformationByOrderId(String id) {
        OrderInformation orderInformation = orderInformationRepository.findByOrderId(id);
        return orderInformation;
    }

    @Override
    public OrderInformation updateOrderInformation(OrderInformation orderInformation) {
        OrderInformation orderInformationUpdate = orderInformationRepository
                .findById(orderInformation.getTransactionId())
                .orElseThrow(() -> new RuntimeException("Invalid order, information not found"));
        orderInformationUpdate.setOrderId(orderInformation.getOrderId());
        orderInformationUpdate.setQuantity(orderInformation.getQuantity());
        orderInformationUpdate.setTotal_price(orderInformation.getTotal_price());
        orderInformationUpdate.setName(orderInformation.getName());
        orderInformationUpdate.setStatus(orderInformation.getStatus());
        return orderInformationRepository.save(orderInformationUpdate);
    }
}

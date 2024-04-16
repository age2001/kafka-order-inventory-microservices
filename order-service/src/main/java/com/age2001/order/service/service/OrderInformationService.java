package com.age2001.order.service.service;

import com.age2001.order.service.entity.OrderInformation;
import com.age2001.order.service.payload.OrderEvent;

import java.util.List;

public interface OrderInformationService {
    OrderInformation createOrderInformation(OrderEvent orderEvent);
    List<OrderInformation> getAllOrderInformation();
    OrderInformation updateOrderInformation(Long id);
    OrderInformation getOrderInformationByOrderId(String id);
    OrderInformation updateOrderInformation(OrderInformation orderInformation);
}

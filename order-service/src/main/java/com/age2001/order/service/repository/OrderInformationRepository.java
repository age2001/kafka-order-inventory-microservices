package com.age2001.order.service.repository;

import com.age2001.order.service.entity.OrderInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderInformationRepository extends JpaRepository<OrderInformation, Long> {
    OrderInformation findByOrderId(String id);
}

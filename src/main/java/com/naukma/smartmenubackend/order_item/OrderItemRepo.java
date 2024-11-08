package com.naukma.smartmenubackend.order_item;

import com.naukma.smartmenubackend.order_item.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepo extends JpaRepository<OrderItem, Long> {

}

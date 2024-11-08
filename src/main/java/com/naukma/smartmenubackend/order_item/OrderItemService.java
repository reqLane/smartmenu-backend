package com.naukma.smartmenubackend.order_item;

import com.naukma.smartmenubackend.order_item.model.OrderItem;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class OrderItemService {
    private final OrderItemRepo orderItemRepo;

    public OrderItemService(OrderItemRepo orderItemRepo) {
        this.orderItemRepo = orderItemRepo;
    }

    // BUSINESS LOGIC



    // CRUD OPERATIONS

    public Set<OrderItem> findAll() {
        return new HashSet<>(orderItemRepo.findAll());
    }

    public Optional<OrderItem> findById(Long id) {
        return orderItemRepo.findById(id);
    }

    public OrderItem save(OrderItem orderItem) {
        return orderItemRepo.save(orderItem);
    }

    public void deleteById(Long id) {
        orderItemRepo.deleteById(id);
    }

    public void delete(OrderItem orderItem) {
        orderItemRepo.deleteById(orderItem.getOrderItemId());
    }

    public void deleteAll() {
        orderItemRepo.deleteAll();
    }
}

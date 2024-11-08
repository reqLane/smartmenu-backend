package com.naukma.smartmenubackend.order;

import com.naukma.smartmenubackend.order.model.Order;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class OrderService {
    private final OrderRepo orderRepo;

    public OrderService(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    // BUSINESS LOGIC



    // CRUD OPERATIONS

    public Set<Order> findAll() {
        return new HashSet<>(orderRepo.findAll());
    }

    public Optional<Order> findById(Long id) {
        return orderRepo.findById(id);
    }

    public Order save(Order order) {
        return orderRepo.save(order);
    }

    public void deleteById(Long id) {
        orderRepo.deleteById(id);
    }

    public void delete(Order order) {
        orderRepo.deleteById(order.getOrderId());
    }

    public void deleteAll() {
        orderRepo.deleteAll();
    }
}

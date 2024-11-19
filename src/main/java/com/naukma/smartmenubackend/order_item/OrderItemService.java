package com.naukma.smartmenubackend.order_item;

import com.naukma.smartmenubackend.exception.InvalidOrderItemDataException;
import com.naukma.smartmenubackend.order_item.model.OrderItem;
import com.naukma.smartmenubackend.order_item.model.OrderItemDTO;
import com.naukma.smartmenubackend.utils.DTOMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class OrderItemService {
    private final OrderItemRepo orderItemRepo;

    public OrderItemService(OrderItemRepo orderItemRepo) {
        this.orderItemRepo = orderItemRepo;
    }

    // BUSINESS LOGIC

    public OrderItemDTO markOrderItemAsDone(Long orderItemId) {
        OrderItem orderItem = orderItemRepo.findById(orderItemId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("ORDER ITEM ID-%d NOT FOUND TO MARK AS DONE", orderItemId)));
        if (orderItem.getIsDone())
            throw new InvalidOrderItemDataException(String.format("ORDER ITEM ID-%d IS ALREADY DONE", orderItemId));

        orderItem.setIsDone(true);

        orderItem = save(orderItem);
        return DTOMapper.toDTO(orderItem);
    }

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

    public List<OrderItem> saveAll(Iterable<OrderItem> orderItems) {
        return orderItemRepo.saveAll(orderItems);
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

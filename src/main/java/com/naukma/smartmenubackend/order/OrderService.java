package com.naukma.smartmenubackend.order;

import com.naukma.smartmenubackend.exception.InvalidOrderDataException;
import com.naukma.smartmenubackend.menu_item.MenuItemService;
import com.naukma.smartmenubackend.menu_item.model.MenuItem;
import com.naukma.smartmenubackend.order.model.Order;
import com.naukma.smartmenubackend.order.model.OrderDTO;
import com.naukma.smartmenubackend.order_item.OrderItemService;
import com.naukma.smartmenubackend.order_item.model.OrderItem;
import com.naukma.smartmenubackend.order_item.model.OrderItemDTO;
import com.naukma.smartmenubackend.table.TableService;
import com.naukma.smartmenubackend.utils.DTOMapper;
import com.naukma.smartmenubackend.waiter.WaiterService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.naukma.smartmenubackend.utils.Utils.isNullOrEmpty;

@Service
public class OrderService {
    private final OrderRepo orderRepo;
    private final OrderItemService orderItemService;
    private final MenuItemService menuItemService;
    private final WaiterService waiterService;
    private final TableService tableService;

    public OrderService(OrderRepo orderRepo, OrderItemService orderItemService, MenuItemService menuItemService, WaiterService waiterService, TableService tableService) {
        this.orderRepo = orderRepo;
        this.orderItemService = orderItemService;
        this.menuItemService = menuItemService;
        this.waiterService = waiterService;
        this.tableService = tableService;
    }

    // BUSINESS LOGIC

    public OrderDTO createOrder(OrderDTO orderDTO) {
        if (orderDTO.waiterId() == null)
            throw new InvalidOrderDataException("CAN'T CREATE ORDER WITHOUT WAITER ID");
        if (orderDTO.tableId() == null)
            throw new InvalidOrderDataException("CAN'T CREATE ORDER WITHOUT TABLE ID");
        if (isNullOrEmpty(orderDTO.orderItems()))
            throw new InvalidOrderDataException("CAN'T CREATE EMPTY ORDER");

        Order order = new Order();
        for (OrderItemDTO orderItemDTO : orderDTO.orderItems()) {
            MenuItem menuItem = menuItemService.findById(orderItemDTO.menuItemId())
                    .orElseThrow(() -> new EntityNotFoundException(String.format("MENU ITEM ID-%d NOT FOUND", orderItemDTO.menuItemId())));

            OrderItem orderItem = new OrderItem(orderItemDTO.quantity(), orderItemDTO.specialInstructions(), order, menuItem);
            order.getOrderItems().add(orderItem);
        }
        order.calculatePrice();

        order = orderRepo.save(order);
        List<OrderItem> orderItemsSaved = orderItemService.saveAll(order.getOrderItems());

        return DTOMapper.toDTO(order);
    }

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

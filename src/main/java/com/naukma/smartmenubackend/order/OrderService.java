package com.naukma.smartmenubackend.order;

import com.naukma.smartmenubackend.exception.InvalidOrderDataException;
import com.naukma.smartmenubackend.menu_item.MenuItemService;
import com.naukma.smartmenubackend.menu_item.model.MenuItem;
import com.naukma.smartmenubackend.order.model.Order;
import com.naukma.smartmenubackend.order.model.OrderDTO;
import com.naukma.smartmenubackend.order.status.OrderStatus;
import com.naukma.smartmenubackend.order_item.OrderItemService;
import com.naukma.smartmenubackend.order_item.model.OrderItem;
import com.naukma.smartmenubackend.order_item.model.OrderItemDTO;
import com.naukma.smartmenubackend.table.TableService;
import com.naukma.smartmenubackend.table.model.Table;
import com.naukma.smartmenubackend.utils.DTOMapper;
import com.naukma.smartmenubackend.waiter.WaiterService;
import com.naukma.smartmenubackend.waiter.model.Waiter;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

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

        Waiter waiter = waiterService.findById(orderDTO.waiterId())
                .orElseThrow(() -> new EntityNotFoundException(String.format("WAITER ID-%d NOT FOUND TO CREATE ORDER", orderDTO.waiterId())));
        Table table = tableService.findById(orderDTO.tableId())
                .orElseThrow(() -> new EntityNotFoundException(String.format("TABLE ID-%d NOT FOUND TO CREATE ORDER", orderDTO.tableId())));
        if (tableService.hasActiveOrder(table.getTableId()))
            throw new InvalidOrderDataException(String.format("TABLE ID-%d ALREADY ACTIVE, CAN'T CREATE ORDERS UNTIL COMPLETED OR CANCELLED", table.getTableId()));

        Order order = new Order(waiter, table);
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

    public OrderDTO payOrder(Long orderId) {
        Order order = findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("ORDER ID-%d NOT FOUND TO PAY", orderId)));

        order.setPaymentTime(new Timestamp(System.currentTimeMillis()));
        order.setStatus(OrderStatus.COMPLETED);

        order = save(order);
        return DTOMapper.toDTO(order);
    }

    public OrderDTO cancelOrder(Long orderId) {
        Order order = findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("ORDER ID-%d NOT FOUND TO CANCEL", orderId)));
        if (order.getStatus() != OrderStatus.PENDING)
            throw new InvalidOrderDataException(String.format("ORDER ID-%d IS %s, CAN'T CANCEL IT", orderId, order.getStatus()));

        order.setStatus(OrderStatus.CANCELLED);

        order = orderRepo.save(order);
        return DTOMapper.toDTO(order);
    }

    public List<OrderDTO> getPendingOrders() {
        return findAll()
                .stream()
                .filter(order -> order.getStatus() == OrderStatus.PENDING)
                .sorted(Comparator.comparing(Order::getOrderTime))
                .map(DTOMapper::toDTO)
                .toList();
    }

    public List<OrderDTO> getActiveOrders() {
        return findAll()
                .stream()
                .filter(order -> order.getStatus() == OrderStatus.PENDING || order.getStatus() == OrderStatus.COOKED)
                .sorted(Comparator.comparing(Order::getOrderTime))
                .map(DTOMapper::toDTO)
                .toList();
    }

    public Optional<Order> getActiveOrderByTableId(Long tableId) {
        return findAll()
                .stream()
                .filter(order -> order.getTable().getTableId().equals(tableId) && order.getStatus() == OrderStatus.COMPLETED)
                .max(Comparator.comparing(Order::getOrderTime));
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

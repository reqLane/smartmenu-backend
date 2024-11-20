package com.naukma.smartmenubackend.order;

import com.naukma.smartmenubackend.order.model.OrderDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("")
    public ResponseEntity<OrderDTO> create(@RequestBody OrderDTO orderDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(orderDTO));
    }

    @PatchMapping("/{orderId}/pay")
    public ResponseEntity<OrderDTO> pay(@PathVariable("orderId") Long orderId) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.payOrder(orderId));
    }

    @PatchMapping("/{orderId}/cancel")
    public ResponseEntity<OrderDTO> cancel(@PathVariable("orderId") Long orderId) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.cancelOrder(orderId));
    }

    @GetMapping("/pending")
    public ResponseEntity<List<OrderDTO>> getPendingOrders() {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getPendingOrders());
    }

    @GetMapping("/active")
    public ResponseEntity<List<OrderDTO>> getActiveOrders() {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getActiveOrders());
    }
}

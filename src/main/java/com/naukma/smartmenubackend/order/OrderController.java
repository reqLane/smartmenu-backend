package com.naukma.smartmenubackend.order;

import com.naukma.smartmenubackend.order.model.OrderDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

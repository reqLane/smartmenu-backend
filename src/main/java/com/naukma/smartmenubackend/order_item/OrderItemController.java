package com.naukma.smartmenubackend.order_item;

import com.naukma.smartmenubackend.order_item.model.OrderItemDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order-items")
public class OrderItemController {
    private final OrderItemService orderItemService;

    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @PatchMapping("/{orderItemId}/done")
    public ResponseEntity<OrderItemDTO> markAsDone(@PathVariable Long orderItemId) {
        return ResponseEntity.status(HttpStatus.OK).body(orderItemService.markOrderItemAsDone(orderItemId));
    }
}

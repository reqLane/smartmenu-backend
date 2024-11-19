package com.naukma.smartmenubackend.order.model;

import com.naukma.smartmenubackend.order.status.OrderStatus;
import com.naukma.smartmenubackend.order_item.model.OrderItemDTO;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public record OrderDTO(
        Long orderId,
        Timestamp orderTime,
        OrderStatus status,
        BigDecimal totalAmount,
        Timestamp paymentTime,
        Long reviewId,
        Long waiterId,
        Long tableId,
        List<OrderItemDTO> orderItems
) {
}

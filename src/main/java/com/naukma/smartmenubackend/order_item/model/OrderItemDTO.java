package com.naukma.smartmenubackend.order_item.model;

import java.math.BigDecimal;

public record OrderItemDTO(
        Long orderItemId,
        Long menuItemId,
        Long quantity,
        String specialInstructions,
        Boolean isDone,
        String menuItemName,
        BigDecimal menuItemPrice
) {
}

package com.naukma.smartmenubackend.order_item.model;

public record OrderItemDTO(
        Long orderItemId,
        Long menuItemId,
        Long quantity,
        String specialInstructions,
        Boolean isDone,
        String menuItemName
) {
}

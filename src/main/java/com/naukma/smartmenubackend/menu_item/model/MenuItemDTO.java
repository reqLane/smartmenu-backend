package com.naukma.smartmenubackend.menu_item.model;

import java.math.BigDecimal;

public record MenuItemDTO(
        Long menuItemId,
        String name,
        String description,
        BigDecimal price,
        String imageUrl
) {
}

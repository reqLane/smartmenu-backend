package com.naukma.smartmenubackend.menu_item.model;

import com.naukma.smartmenubackend.order_item.model.OrderItem;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "menu_items")
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long menuItemId;

    @Column(unique = true, nullable = false, length = 100)
    private String name;

    private String description;

    @Column(nullable = false, precision = 6, scale = 2)
    private BigDecimal price;

    @Column(length = 300)
    private String imageURL;

    @Column(nullable = false)
    private Boolean availability;

    @OneToMany(mappedBy = "menuItem")
    private Set<OrderItem> orderItems;
}

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

    @OneToMany(mappedBy = "menuItem")
    private Set<OrderItem> orderItems;

    public MenuItem(String name, String description, BigDecimal price, String imageURL) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageURL = imageURL;
    }
}

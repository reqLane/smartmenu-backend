package com.naukma.smartmenubackend.order_item.model;

import com.naukma.smartmenubackend.menu_item.model.MenuItem;
import com.naukma.smartmenubackend.order.model.Order;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemId;

    @Column(nullable = false)
    private Long quantity;

    private String specialInstructions;

    @Column(nullable = false)
    private Boolean isDone;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "menu_item_id", nullable = false)
    private MenuItem menuItem;
}

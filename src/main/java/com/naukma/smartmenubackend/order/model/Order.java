package com.naukma.smartmenubackend.order.model;

import com.naukma.smartmenubackend.order.status.OrderStatus;
import com.naukma.smartmenubackend.order_item.model.OrderItem;
import com.naukma.smartmenubackend.review.model.Review;
import com.naukma.smartmenubackend.table.model.Table;
import com.naukma.smartmenubackend.waiter.model.Waiter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@jakarta.persistence.Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column(nullable = false)
    @CreationTimestamp
    private Timestamp orderTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status = OrderStatus.PENDING;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal totalAmount;

    private Timestamp paymentTime;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "review_id_fk")
    private Review review;

    @ManyToOne
    @JoinColumn(name = "waiter_id_fk", nullable = false)
    private Waiter waiter;

    @ManyToOne
    @JoinColumn(name = "table_id_fk", nullable = false)
    private Table table;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderItem> orderItems = new HashSet<>();

    public Order(Waiter waiter, Table table) {
        this.waiter = waiter;
        this.table = table;
    }

    public void calculatePrice() {
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (OrderItem orderItem : orderItems) {
            totalAmount = totalAmount.add(orderItem.getMenuItem().getPrice().multiply(new BigDecimal(orderItem.getQuantity())));
        }
        setTotalAmount(totalAmount);
    }
}

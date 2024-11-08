package com.naukma.smartmenubackend.waiter.model;

import com.naukma.smartmenubackend.order.model.Order;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "waiters")
public class Waiter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long waiterId;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "waiter")
    private Set<Order> orders = new HashSet<>();
}

package com.naukma.smartmenubackend.table.model;

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
@jakarta.persistence.Table(name = "tables")
public class Table {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tableId;

    @OneToMany(mappedBy = "table")
    private Set<Order> orders = new HashSet<>();
}

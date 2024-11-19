package com.naukma.smartmenubackend.review.model;

import com.naukma.smartmenubackend.order.model.Order;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Range;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @Column(nullable = false)
    @Range(min = 1, max = 5)
    private Long rating;

    private String comment;

    @Column(nullable = false)
    @CreationTimestamp
    private Timestamp reviewTime;

    @OneToOne(mappedBy = "review")
    private Order order;

    public Review(Long rating, String comment, Order order) {
        this.rating = rating;
        this.comment = comment;
        this.order = order;
    }
}

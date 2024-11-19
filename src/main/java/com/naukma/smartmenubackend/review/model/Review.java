package com.naukma.smartmenubackend.review.model;

import com.naukma.smartmenubackend.order.model.Order;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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

    @Column(nullable = false)
    private Timestamp reviewTime;

    private String comment;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    public Review(Long rating, Timestamp reviewTime) {
        this.rating = rating;
        this.reviewTime = reviewTime;
    }
}

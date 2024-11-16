package com.naukma.smartmenubackend.review.model;

import java.sql.Timestamp;

public record ReviewDTO(
        Long reviewId,
        Long rating,
        Timestamp reviewTime
) {
}

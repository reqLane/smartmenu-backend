package com.naukma.smartmenubackend.review.model;

import java.sql.Timestamp;

public record ReviewDTO(
        Long reviewId,
        Long tableId,
        Long rating,
        String comment,
        Timestamp reviewTime
) {
}

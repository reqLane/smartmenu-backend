package com.naukma.smartmenubackend.review;

import com.naukma.smartmenubackend.review.model.ReviewDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("")
    public ResponseEntity<ReviewDTO> create(@RequestBody ReviewDTO reviewDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewService.createReview(reviewDTO));
    }

    @GetMapping("")
    public ResponseEntity<List<ReviewDTO>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(reviewService.getAllReviews());
    }
}

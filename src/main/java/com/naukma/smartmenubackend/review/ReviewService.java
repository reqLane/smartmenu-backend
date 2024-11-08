package com.naukma.smartmenubackend.review;

import com.naukma.smartmenubackend.review.model.Review;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ReviewService {
    private final ReviewRepo reviewRepo;

    public ReviewService(ReviewRepo reviewRepo) {
        this.reviewRepo = reviewRepo;
    }

    // BUSINESS LOGIC



    // CRUD OPERATIONS

    public Set<Review> findAll() {
        return new HashSet<>(reviewRepo.findAll());
    }

    public Optional<Review> findById(Long id) {
        return reviewRepo.findById(id);
    }

    public Review save(Review review) {
        return reviewRepo.save(review);
    }

    public void deleteById(Long id) {
        reviewRepo.deleteById(id);
    }

    public void delete(Review review) {
        reviewRepo.deleteById(review.getReviewId());
    }

    public void deleteAll() {
        reviewRepo.deleteAll();
    }
}

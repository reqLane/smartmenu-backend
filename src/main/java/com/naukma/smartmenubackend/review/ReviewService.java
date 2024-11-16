package com.naukma.smartmenubackend.review;

import com.naukma.smartmenubackend.review.model.Review;
import com.naukma.smartmenubackend.review.model.ReviewDTO;
import com.naukma.smartmenubackend.utils.DTOMapper;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReviewService {
    private final ReviewRepo reviewRepo;

    public ReviewService(ReviewRepo reviewRepo) {
        this.reviewRepo = reviewRepo;
    }

    // BUSINESS LOGIC

    public List<ReviewDTO> getAllReviews() {
        return reviewRepo.findAll()
                .stream()
                .sorted(Comparator.comparing(Review::getReviewTime).reversed())
                .map(DTOMapper::toDTO)
                .toList();
    }

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

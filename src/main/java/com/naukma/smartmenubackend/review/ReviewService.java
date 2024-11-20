package com.naukma.smartmenubackend.review;

import com.naukma.smartmenubackend.exception.InvalidReviewDataException;
import com.naukma.smartmenubackend.order.OrderService;
import com.naukma.smartmenubackend.order.model.Order;
import com.naukma.smartmenubackend.review.model.Review;
import com.naukma.smartmenubackend.review.model.ReviewDTO;
import com.naukma.smartmenubackend.utils.DTOMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReviewService {
    private final ReviewRepo reviewRepo;
    private final OrderService orderService;

    public ReviewService(ReviewRepo reviewRepo, OrderService orderService) {
        this.reviewRepo = reviewRepo;
        this.orderService = orderService;
    }

    // BUSINESS LOGIC

    public ReviewDTO createReview(ReviewDTO reviewDTO) {
        if (reviewDTO.tableId() == null
                || reviewDTO.rating() == null)
            throw new InvalidReviewDataException("REVIEW REQUIRED FIELD IS EMPTY");

        Order order = orderService.getActiveOrderByTableId(reviewDTO.tableId())
                .orElseThrow(() -> new EntityNotFoundException(String.format("COMPLETED ORDERS AT TABLE ID-%d NOT FOUND", reviewDTO.tableId())));

        Review review = new Review(reviewDTO.rating(), reviewDTO.comment(), order);

        order.setReview(review);
        orderService.save(order);
        return DTOMapper.toDTO(order.getReview());
    }

    public List<ReviewDTO> getAllReviews() {
        return findAll()
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

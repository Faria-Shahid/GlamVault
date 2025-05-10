package com.example.pinkbullmakeup.Controller;

import com.example.pinkbullmakeup.DTO.AddReview;
import com.example.pinkbullmakeup.Entity.Review;
import com.example.pinkbullmakeup.Service.ReviewService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public ResponseEntity<Review> createReview(@Valid @RequestBody AddReview addReview) {
        Review review = reviewService.createReview(addReview);
        return ResponseEntity.created(URI.create("/api/reviews/" + review.getReviewId())).body(review);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Review>> getReviewsByProduct(@PathVariable UUID productId) {
        List<Review> reviews = reviewService.getAllReviewsOfProduct(productId);
        return ResponseEntity.ok(reviews);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable UUID id) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }
}


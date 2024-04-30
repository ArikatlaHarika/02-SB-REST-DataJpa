package com.javaexpress.controller;

import com.javaexpress.exceptions.InvalidrequestException;
import com.javaexpress.models.Review;
import com.javaexpress.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/{productId}/reviews")
    public Review addReviewToProduct(@PathVariable Long productId, @RequestBody Review review) {
        if(review.getProduct().getId()==null){
        throw new InvalidrequestException("Unable to review");
        }
        return reviewService.addReviewToProduct(productId, review);
    }


    @DeleteMapping("{reviewId}")
    public void deleteReview(@PathVariable Long reviewId){
        reviewService.deleteById(reviewId);
    }

}

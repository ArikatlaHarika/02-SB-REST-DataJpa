package com.javaexpress.service;

import com.javaexpress.models.Product;
import com.javaexpress.models.Review;
import com.javaexpress.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    @Autowired
   private ProductService productService;


    @Autowired
    ReviewRepository reviewRepository;
    public void deleteById(Long reviewId) {
        reviewRepository.deleteById(reviewId);

    }

    public Review addReviewToProduct(Long productId, Review review) {
            // Fetch the product
            Product product = productService.getProductById(productId);
            // Set the product for the review
            review.setProduct(product);
            // Save the review
            return reviewRepository.save(review);
        }
    }

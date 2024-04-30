package com.javaexpress.service;

import com.javaexpress.exceptions.ResourceNotFoundException;
import com.javaexpress.models.Category;
import com.javaexpress.models.Product;
import com.javaexpress.models.Review;
import com.javaexpress.repository.ProductRepository;
import com.javaexpress.repository.ReviewRepository;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    public Product createProduct(Product product) {
        product.setInStock(true);
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    public Product getByProductId(Long productId) {
     return productRepository.
                findById(productId).orElseThrow(()->new ResourceNotFoundException("Product not found "+productId));
    }

    public Product getProductById(Long productId) {
            return productRepository.findById(productId)
                    .orElseThrow(() -> new RuntimeException("Product not found"));
        }

    public List<Product> findProductByCategoryName(String categoryName) {
      return productRepository.findByCategoryName(categoryName);

    }
}





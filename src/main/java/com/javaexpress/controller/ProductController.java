package com.javaexpress.controller;

import com.javaexpress.models.Product;
import com.javaexpress.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("api/v1/product")
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping("/{productId}")
    public Product getProductById(@PathVariable Long productId) {
        return productService.getProductById(productId);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product product) {
        log.info(" ProductController :: createProduct {} ",product.getName());
        return productService.createProduct(product);

    }

    @GetMapping
    public List<Product> getAllProducts(){
        return productService.getAllProducts();

    }

    @GetMapping("category/{name}")
    public List<Product> findProductsByCategoryName(@PathVariable(name= "name") String categoryName){
        log.info("ProductController :: findProductsByCategoryName" );
       return productService.findProductByCategoryName(categoryName);


    }


}

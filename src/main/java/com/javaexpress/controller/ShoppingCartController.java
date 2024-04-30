package com.javaexpress.controller;

import com.javaexpress.dtos.AddToCartRequest;
import com.javaexpress.exceptions.InvalidrequestException;
import com.javaexpress.models.Product;
import com.javaexpress.models.ShoppingCart;
import com.javaexpress.models.User;
import com.javaexpress.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/shopping_cart")
@Slf4j
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long createCart(@RequestParam Long userId){
      var result=  shoppingCartService.createShoppingCart(userId);
      return result.getId();
    }
    //DTO pattern
    @PostMapping("{cartId}/items")
    public void addToCart(@PathVariable Long cartId, @RequestBody AddToCartRequest request){
        if(request.getQuantity()<=0){
            throw new InvalidrequestException("Quantity must be greater than zero");
        }
         //Retrieve the shopping cart (validation)
     ShoppingCart shoppingCart= shoppingCartService.retrieveShoppingCartById(cartId);
        //Retrieve the product by its id (validation)
      Product product=  shoppingCartService.fetchProductById(request.getProductId());
        //add the item to the cart

        shoppingCartService.addToCart(shoppingCart,product,request.getQuantity(),request.getUser());
        log.info("product added to cart {}",product.getName(),shoppingCart.getId());

    }
}

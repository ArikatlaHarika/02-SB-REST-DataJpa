package com.javaexpress.controller;

import com.javaexpress.dtos.CreateOrderRequest;
import com.javaexpress.models.Order;
import com.javaexpress.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public void createOrder(@RequestBody CreateOrderRequest request){
        orderService.createOrder(request);
        //productId,quantity,userId

    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Order>> fetchOrderByUserId(@PathVariable Long userId){
       List<Order> orders= orderService.fetchOrderById(userId);
        return ResponseEntity.ok(orders);


    }

}

package com.javaexpress.service;


import com.javaexpress.dtos.CreateOrderRequest;
import com.javaexpress.dtos.OrderItemRequest;
import com.javaexpress.exceptions.OrderCreationException;
import com.javaexpress.models.Order;
import com.javaexpress.models.OrderItem;
import com.javaexpress.models.OrderStatus;
import com.javaexpress.models.User;
import com.javaexpress.repository.OrderRepository;
import com.javaexpress.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;


    public void createOrder(CreateOrderRequest request) {
        try{
            validateOrderRequest(request);
            double totalPrice = caluculateTotalPrice(request.getItems());
            //create order entity
            //alt + enter short cut to get left side variable
            Order order = createOrderEntity(request, totalPrice);

            orderRepository.save(order);

        }catch(Exception ex){
            throw new OrderCreationException("Failed to create order due to unexpected error");
        }

    }

    private void validateOrderRequest(CreateOrderRequest request) {
        if (request.getItems() == null || request.getItems().isEmpty()) {
            throw new IllegalArgumentException("Order items cannot be empty");
        }
    }

    private Order createOrderEntity(CreateOrderRequest request, double totalPrice) {
        Order order = new Order();
        order.setTotalPrice(totalPrice);
        order.setOrderDate(LocalDateTime.now());
        order.setUser(request.getUser());
        order.setStatus(OrderStatus.CREATED);//initial status is created
        order.setOrderItemList(createOrderItems(request.getItems(), order));
        return order;
    }

    private List<OrderItem> createOrderItems(List<OrderItemRequest> items, Order order) {
        return items.stream().map(item -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setQuantity(item.getQuantity());
            orderItem.setProduct(productRepository.findById(item.getProductId()).orElse(null));
            return orderItem;
        }).collect(Collectors.toList());
    }


    private double caluculateTotalPrice(List<OrderItemRequest> items) {
       return items.stream().
               mapToDouble(item->item.getQuantity()*getProductPrice(item.getProductId())).sum();


    }

    //quantity*getProductPrice(productId)
    public double getProductPrice(Long productId){
       var product= productRepository.findById(productId).orElse(null);
       return product!=null?product.getPrice():0.0;


    }


    public List<Order> fetchOrderById(Long userId) {
       return orderRepository.findByUserId(userId);
    }
}

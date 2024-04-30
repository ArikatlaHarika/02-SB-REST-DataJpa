package com.javaexpress.dtos;

import com.javaexpress.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CreateOrderRequest {

    private List<OrderItemRequest> items; //items dto to order item entity
    private User user;
}

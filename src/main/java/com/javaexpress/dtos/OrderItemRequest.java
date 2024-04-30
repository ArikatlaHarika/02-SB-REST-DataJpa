package com.javaexpress.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
public class OrderItemRequest {

    private Long productId;

    private int quantity;
}

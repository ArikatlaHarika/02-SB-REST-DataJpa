package com.javaexpress.dtos;

import com.javaexpress.models.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddToCartRequest {

    private User user;
    private Long productId;
    private int quantity;

}

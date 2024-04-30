package com.javaexpress.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="order_id",nullable = false)
    @JsonIgnore
    private Order order;

    @ManyToOne
    @JoinColumn(name="product_id",nullable = false)
    private Product product;
    private  int quantity;
}

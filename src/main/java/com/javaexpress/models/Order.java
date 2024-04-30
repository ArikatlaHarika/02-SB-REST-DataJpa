package com.javaexpress.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name="orders")
//order is a reserved keyword in sql
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime orderDate;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    private double totalPrice;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    //fetch = FetchType.LAZY
    //lazy means when you want you will get the data , eager means you wont get the parent data
    private List<OrderItem> orderItemList;
}

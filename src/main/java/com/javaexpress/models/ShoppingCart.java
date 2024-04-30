package com.javaexpress.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ShoppingCart {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "shoppingCart",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<CartItems> cartItemsList;

    @OneToOne
    @JoinColumn(name="user_id")
    private User user;
}

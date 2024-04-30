package com.javaexpress.repository;

import com.javaexpress.models.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemsRepository extends JpaRepository<CartItems,Long> {
}

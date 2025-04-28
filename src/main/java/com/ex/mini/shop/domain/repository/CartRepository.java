package com.ex.mini.shop.domain.repository;

import com.ex.mini.shop.domain.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}

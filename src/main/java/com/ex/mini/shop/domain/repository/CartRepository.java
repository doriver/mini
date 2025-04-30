package com.ex.mini.shop.domain.repository;

import com.ex.mini.shop.domain.entity.ItemInCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<ItemInCart, Long> {

    List<ItemInCart> findAllByUserId(Long userId);
}

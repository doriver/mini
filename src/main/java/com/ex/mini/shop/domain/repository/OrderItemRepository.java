package com.ex.mini.shop.domain.repository;

import com.ex.mini.shop.domain.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}

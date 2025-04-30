package com.ex.mini.shop.domain.repository;

import com.ex.mini.shop.domain.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}

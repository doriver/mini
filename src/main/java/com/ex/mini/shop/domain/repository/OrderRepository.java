package com.ex.mini.shop.domain.repository;

import com.ex.mini.shop.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}

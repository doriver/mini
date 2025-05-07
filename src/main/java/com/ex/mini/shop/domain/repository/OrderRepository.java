package com.ex.mini.shop.domain.repository;

import com.ex.mini.shop.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    public List<Order> findAllByUserId(Long userId);
}

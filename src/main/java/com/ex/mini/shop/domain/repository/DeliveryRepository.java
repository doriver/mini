package com.ex.mini.shop.domain.repository;

import com.ex.mini.shop.domain.entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
}

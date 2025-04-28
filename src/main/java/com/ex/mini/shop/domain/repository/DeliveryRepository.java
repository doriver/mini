package com.ex.mini.shop.domain.repository;

import com.ex.mini.shop.domain.model.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
}

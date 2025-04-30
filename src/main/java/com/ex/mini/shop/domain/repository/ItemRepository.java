package com.ex.mini.shop.domain.repository;

import com.ex.mini.shop.domain.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}

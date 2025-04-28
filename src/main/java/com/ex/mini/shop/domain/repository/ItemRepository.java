package com.ex.mini.shop.domain.repository;

import com.ex.mini.shop.domain.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}

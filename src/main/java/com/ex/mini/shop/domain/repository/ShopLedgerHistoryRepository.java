package com.ex.mini.shop.domain.repository;

import com.ex.mini.shop.domain.entity.ShopLedgerHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopLedgerHistoryRepository extends JpaRepository<ShopLedgerHistory, Long> {
}

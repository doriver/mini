package com.ex.mini.user.domain.repository;

import com.ex.mini.user.domain.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
}

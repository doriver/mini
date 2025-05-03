package com.ex.mini.user.application;

import com.ex.mini.common.exception.ErrorCode;
import com.ex.mini.common.exception.ExpectedException;
import com.ex.mini.user.domain.entity.Wallet;
import com.ex.mini.user.domain.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletReadService {
    private final WalletRepository walletRepository;

    /*
        특정 사용자가 가진 돈 조회
     */
    public long selectMoney(Long userId) {
        Wallet wallet = walletRepository.findByUserId(userId)
                .orElseThrow(() -> new ExpectedException(ErrorCode.WALLET_NOT_FOUND));

        return wallet.getMoney();
    }
}

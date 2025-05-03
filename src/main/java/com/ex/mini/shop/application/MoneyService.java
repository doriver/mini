package com.ex.mini.shop.application;

import com.ex.mini.common.exception.ErrorCode;
import com.ex.mini.common.exception.ExpectedException;
import com.ex.mini.shop.domain.entity.ShopLedgerHistory;
import com.ex.mini.shop.domain.entity.ShopTransaction;
import com.ex.mini.shop.domain.repository.ShopLedgerHistoryRepository;
import com.ex.mini.user.application.WalletReadService;
import com.ex.mini.user.domain.entity.Wallet;
import com.ex.mini.user.domain.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MoneyService {

    private final WalletRepository walletRepository;
    private final ShopLedgerHistoryRepository shopLedgerHistoryRepository;

    private final WalletReadService walletReadService;

    private final CartReadService cartReadService;



    /*
        구매자 돈 차감 , 마트 장부에 입금 처리
        1. 장바구니 총 비용
        2. 구매자 지갑 조회해서, 차감하기
        3. 마트 장부에 반영하기
     */
    public void moneyTransaction(Long userId) {
        long totalPrice = calculatePriceInCart(userId);
        Wallet wallet = walletRepository.findByUserId(userId)
                .orElseThrow(() -> new ExpectedException(ErrorCode.WALLET_NOT_FOUND));
        wallet.minusMoney(totalPrice);

        ShopLedgerHistory shopLedgerHistory = ShopLedgerHistory.builder()
                .walletId(wallet.getId()).shopTransaction(ShopTransaction.PAY).amount(totalPrice).createdAt(LocalDateTime.now())
                .build();

        try {
            walletRepository.save(wallet);
            shopLedgerHistoryRepository.save(shopLedgerHistory);
        } catch (Exception e) {
            throw new ExpectedException(ErrorCode.FAIL_TRANSACTION);
        }
    }





}

package com.ex.mini.shop.application;

import com.ex.mini.shop.domain.entity.ItemInCart;
import com.ex.mini.user.application.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MoneyService {

    private final CartService cartService;
    private final WalletService walletService;

    /*
        장바구니에 있는 Item들 총 가격, 구매자의 있는돈 비교하기
        1. 장바구니에 있는 item들 가져오기   2. 총 가격 구하기    3. 구매자 돈과 비교하기
     */
    public boolean judgeBuyableMoney(Long userId) {
        List<ItemInCart> itemsInCart = cartService.selectItemsInCart(userId);
        long totalPrice = 0;

        for (ItemInCart itemInCart: itemsInCart) {
            totalPrice += itemInCart.getTotalPrice();
        }

        long money = walletService.selectWalletMoney(userId);

        return money >= totalPrice;
    }




}

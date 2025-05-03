package com.ex.mini.shop.application;

import com.ex.mini.common.exception.ErrorCode;
import com.ex.mini.common.exception.ExpectedException;
import com.ex.mini.shop.domain.entity.ItemInCart;
import com.ex.mini.shop.domain.repository.ItemInCartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartReadService {

    private final ItemInCartRepository itemInCartRepository;

    /*
        장바구니에 있는 아이템들 가져오기
     */
    public List<ItemInCart> selectItemsInCart(Long userId) {
        List<ItemInCart> itemsInCart = itemInCartRepository.findAllByUserId(userId);

        // findAllBy 결과값 확인해야함
        if (itemsInCart.isEmpty()) {
            throw new ExpectedException(ErrorCode.ZERO_CART);
        }
        return itemsInCart;
    }


    /*
        장바구니에 있는 item들 총 가격 구하기
     */
    public long calculatePriceInCart(Long userId) {
        List<ItemInCart> itemsInCart = selectItemsInCart(userId);
        long totalPrice = 0;

        for (ItemInCart itemInCart: itemsInCart) {
            totalPrice += itemInCart.getTotalPrice();
        }

        return totalPrice;
    }




}

package com.ex.mini.shop.application.leaf;

import com.ex.mini.common.exception.ErrorCode;
import com.ex.mini.common.exception.ExpectedException;
import com.ex.mini.shop.domain.entity.ItemInCart;
import com.ex.mini.shop.domain.repository.ItemInCartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemInCartServiceLeaf {

    private final ItemInCartRepository itemInCartRepository;

    // 단순 저장
    public ItemInCart insertItemInCart(ItemInCart itemInCart, ErrorCode errorCode) {
        ItemInCart savedItemInCart = null;
        try {
            savedItemInCart = itemInCartRepository.save(itemInCart);
        } catch (Exception e) {
            throw new ExpectedException(errorCode);
        }
        return savedItemInCart;
    }
}

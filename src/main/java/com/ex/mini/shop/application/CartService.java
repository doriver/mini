package com.ex.mini.shop.application;

import com.ex.mini.common.exception.ErrorCode;
import com.ex.mini.common.exception.ExpectedException;
import com.ex.mini.common.utils.UserUtils;
import com.ex.mini.shop.domain.model.Cart;
import com.ex.mini.shop.domain.repository.CartRepository;
import com.ex.mini.shop.presentation.dto.request.CartCreateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    /*
        장바구니에 아이템 저장
     */
    public Long saveCart(CartCreateDTO cartCreateDTO, Long userId) {
        UserUtils.checkLogin(userId);

        Cart cart = Cart.builder()
                .userId(userId).itemId(cartCreateDTO.getItemId()).count(cartCreateDTO.getCount()).createdAt(LocalDateTime.now())
                .build();
        Long savedCartId = cartRepository.save(cart).getId();
        if (savedCartId == null) {
            throw new ExpectedException(ErrorCode.FAIL_SAVE_CART);
        }
        return savedCartId;
    }
}

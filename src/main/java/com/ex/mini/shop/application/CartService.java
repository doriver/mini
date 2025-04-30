package com.ex.mini.shop.application;

import com.ex.mini.common.exception.ErrorCode;
import com.ex.mini.common.exception.ExpectedException;
import com.ex.mini.common.utils.UserUtils;
import com.ex.mini.shop.domain.entity.ItemInCart;
import com.ex.mini.shop.domain.repository.CartRepository;
import com.ex.mini.shop.presentation.dto.request.CartCreateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    /*
        장바구니에 아이템 저장
     */
    public Long saveCart(CartCreateDTO cartCreateDTO, Long userId) {
        UserUtils.checkLogin(userId);

        ItemInCart itemInCart = ItemInCart.builder()
                .userId(userId).itemId(cartCreateDTO.getItemId()).count(cartCreateDTO.getCount()).createdAt(LocalDateTime.now())
                .build();
        Long savedCartId = cartRepository.save(itemInCart).getId();
        if (savedCartId == null) {
            throw new ExpectedException(ErrorCode.FAIL_SAVE_CART);
        }
        return savedCartId;
    }

    /*
        장바구니에 있는 아이템들 가져오기
     */
    public List<ItemInCart> selectItemsInCart(Long userId) {
        List<ItemInCart> itemsInItemInCart = cartRepository.findAllByUserId(userId);

        // findAllBy 결과값 확인해야함
        if (itemsInItemInCart.isEmpty()) {
            throw new ExpectedException(ErrorCode.ZERO_CART);
        }
        return itemsInItemInCart;
    }
}

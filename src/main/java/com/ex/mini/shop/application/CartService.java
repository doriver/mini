package com.ex.mini.shop.application;

import com.ex.mini.common.exception.ErrorCode;
import com.ex.mini.common.exception.ExpectedException;
import com.ex.mini.common.utils.MoneyCalculation;
import com.ex.mini.common.utils.UserUtils;
import com.ex.mini.shop.domain.entity.Item;
import com.ex.mini.shop.domain.entity.ItemInCart;
import com.ex.mini.shop.domain.repository.ItemInCartRepository;
import com.ex.mini.shop.domain.repository.ItemRepository;
import com.ex.mini.shop.presentation.dto.request.ItemInCartCreateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final ItemInCartRepository itemInCartRepository;
    private final ItemRepository itemRepository;
    /*
        장바구니에 아이템 저장
     */
    public Long saveCart(ItemInCartCreateDTO itemInCartCreateDTO, Long userId) {
        UserUtils.checkLogin(userId);

        Long itemId = itemInCartCreateDTO.getItemId();
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new ExpectedException(ErrorCode.ITEM_NOT_FOUND));

        int itemCount = itemInCartCreateDTO.getCount();
        long totalPrice = MoneyCalculation.priceCount(item.getPrice(), itemCount);

        ItemInCart itemInCart = ItemInCart.builder()
                .userId(userId).itemId(itemId).count(itemCount).totalPrice(totalPrice).createdAt(LocalDateTime.now())
                .build();
        Long savedItemInCartId = itemInCartRepository.save(itemInCart).getId();
        if (savedItemInCartId == null) {
            throw new ExpectedException(ErrorCode.FAIL_SAVE_CART);
        }
        return savedItemInCartId;
    }

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
}

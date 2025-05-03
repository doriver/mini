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
        1. Item조회
        2. 조회한 정보로 저장
     */
    public Long InsertItemInCart(ItemInCartCreateDTO itemInCartCreateDTO, Long userId) {
        UserUtils.checkLogin(userId);

        // Item조회
        Long itemId = itemInCartCreateDTO.getItemId();
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new ExpectedException(ErrorCode.ITEM_NOT_FOUND));

        int itemCount = itemInCartCreateDTO.getCount();
        long totalPrice = MoneyCalculation.priceCount(item.getPrice(), itemCount);

        // ItemInCart저장
        ItemInCart itemInCart = new ItemInCart(userId, itemId, item.getName(), itemCount, totalPrice, LocalDateTime.now());
        ItemInCart savedItemInCart = itemInCartRepository.save(itemInCart);
        return savedItemInCart.getId();
    }

    /*
        주문 완료후, 장바구니 비우기
        userId에 해당하는 것들 지움
     */
    public void emptyCartAfterOrder(Long userId) {
        try {
            itemInCartRepository.deleteByUserId(userId);
        } catch (Exception ignored) {}
    }
    



}

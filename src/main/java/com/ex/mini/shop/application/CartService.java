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
import java.util.Optional;

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
                .userId(userId).itemId(itemId).name(item.getName()).count(itemCount).totalPrice(totalPrice).createdAt(LocalDateTime.now())
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

    /*
        장바구니 비우기
        userId에 해당하는 것들 지움
     */
    public void emptyCart(Long userId) {
        try {
            itemInCartRepository.deleteByUserId(userId);
        } catch (Exception e) {
            throw new ExpectedException(ErrorCode.FAIL_TRANSACTION);
        }
    }

    /*
        장바구니에 담긴 item과 실제 item 개수 비교
         1. 장바구니에 있는 아이템 가져오기   2. 거기의 개수와 item개수 비교하기
     */
    public String judgeBuyableCount(Long userId) {
        List<ItemInCart> itemsInCart = selectItemsInCart(userId);

        for (ItemInCart itemInCart : itemsInCart) {
           Item item = itemRepository.findById(itemInCart.getItemId()).orElse(null);
           if (item == null) {
               return itemInCart.getName() + "는 존재하지 않습니다.";
           }

           if (itemInCart.getCount() > item.getCount()) {
               return item.getName() + "의 재고가 부족합니다.";
           }
        }
        return "ok";
    }

}

package com.ex.mini.shop.application;

import com.ex.mini.shop.domain.entity.Item;
import com.ex.mini.shop.domain.entity.ItemInCart;
import com.ex.mini.shop.domain.repository.ItemRepository;
import com.ex.mini.user.application.WalletReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartBeforeOrderService {

    private final CartReadService cartReadService;
    private final WalletReadService walletReadService;

    private final ItemRepository itemRepository;

    /*
        장바구니에 있는 Item들 총 가격, 구매자의 있는돈 비교하기
     */
    public boolean buyablePrice(Long userId) {

        long totalPrice = cartReadService.calculatePriceInCart(userId);
        long money = walletReadService.selectMoney(userId);

        return money >= totalPrice;
    }

    /*
        장바구니에 담긴 item과 실제 item 개수 비교
         1. 장바구니에 있는 아이템 가져오기   2. 거기의 개수와 item개수 비교하기
     */
    public String buyableCount(Long userId) {
        List<ItemInCart> itemsInCart = cartReadService.selectItemsInCart(userId);

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

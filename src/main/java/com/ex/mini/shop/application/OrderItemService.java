package com.ex.mini.shop.application;

import com.ex.mini.shop.domain.entity.ItemInCart;
import com.ex.mini.shop.domain.entity.OrderItem;
import com.ex.mini.shop.domain.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;

    private final CartService cartService;

    /*
        주문된 아이템 등록
     */
    public void saveOrderItem(Long savedOrderId, Long userId) {
        List<ItemInCart> itemsInCart = cartService.selectItemsInCart(userId);

        List<OrderItem> orderItemList = new ArrayList<>();
        for (ItemInCart itemInCart : itemsInCart) {
            OrderItem orderItem = OrderItem.builder()
                    .orderId(savedOrderId).itemId(itemInCart.getItemId()).count(itemInCart.getCount()).createdAt(LocalDateTime.now())
                    .build();
            orderItemList.add(orderItem);
        }

        // 여기 실패 경우에대한 처리 해줘야함
        orderItemRepository.saveAll(orderItemList);
    }
}

package com.ex.mini.shop.application;

import com.ex.mini.common.exception.ErrorCode;
import com.ex.mini.common.exception.ExpectedException;
import com.ex.mini.shop.domain.entity.ItemInCart;
import com.ex.mini.shop.domain.entity.OrderItem;
import com.ex.mini.shop.domain.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;

    /*
        주문된 아이템 등록
     */
    public void saveOrderItem(Long savedOrderId, List<ItemInCart> itemsInCart) {

        List<OrderItem> orderItems = cartIntoOrder(savedOrderId, itemsInCart);

        try {
            orderItemRepository.saveAll(orderItems);
        } catch (Exception e) {
            throw new ExpectedException(ErrorCode.FAIL_ORDER_ITEM);
        }
    }


    /*
        카트에 담긴 Item들 OrderItem으로
    */
    public List<OrderItem> cartIntoOrder(Long orderId, List<ItemInCart> itemsInCart) {

        List<OrderItem> orderItemList = new ArrayList<>();
        for (ItemInCart itemInCart : itemsInCart) {
            OrderItem orderItem = new OrderItem(orderId, itemInCart.getItemId(), itemInCart.getCount(), itemInCart.getTotalPrice(), LocalDateTime.now());
            orderItemList.add(orderItem);
        }
        return orderItemList;
    }

}

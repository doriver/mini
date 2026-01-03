package com.ex.mini.shop.application;

import com.ex.mini.common.exception.ErrorCode;
import com.ex.mini.common.exception.Expected4xxException;
import com.ex.mini.shop.domain.Cart;
import com.ex.mini.shop.domain.entity.Order;
import com.ex.mini.shop.domain.entity.OrderStatus;
import com.ex.mini.shop.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final OrderItemService orderItemService;
    private final MoneyService moneyService;
    private final ItemService itemService;

    private final OrderRepository orderRepository;

    @Transactional
    public Long order(Long userId, Long deliveryId, Cart cart) {
        // Order생성
        Order order = new Order(userId, deliveryId, OrderStatus.REGISTER, LocalDateTime.now());
        Long savedOrderId = null;
        try {
            savedOrderId = orderRepository.save(order).getId();
        } catch (Exception e) {
            throw new Expected4xxException(ErrorCode.FAIL_ORDER);
        }

        // OrderItem들 저장
        orderItemService.saveOrderItem(savedOrderId, cart.getItemsInCart());

        // Item들 개수 차감
        cart.countItems();
        itemService.itemCountDownByOrder(cart.getItemAndCountMap());

        // 구매자 돈 차감 , 마트 장부에 입금 처리
        moneyService.moneyTransaction(userId, cart.getTotalPrice());

        return savedOrderId;
    }
}

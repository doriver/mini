package com.ex.mini.shop.application;

import com.ex.mini.common.exception.ErrorCode;
import com.ex.mini.common.exception.ExpectedException;
import com.ex.mini.common.utils.UserUtils;
import com.ex.mini.shop.domain.entity.Order;
import com.ex.mini.shop.domain.entity.OrderStatus;
import com.ex.mini.shop.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final DeliveryService deliveryService;
    private final OrderItemService orderItemService;


    /*
        주문하기
        작업의 단위를 생각해야함, 한꺼번에 묶을
     */
    @Transactional
    public Long saveOrder(Long userId, String address) {
        UserUtils.checkLogin(userId);

        // 배송정보 저장
        Long savedDeliveryId = deliveryService.saveDelivery(address);

        // 주문 생성
        Order order = Order.builder()
                .userId(userId).deliveryId(savedDeliveryId).status(OrderStatus.ORDER).createdAt(LocalDateTime.now())
                .build();
        Long savedOrderId = orderRepository.save(order).getId();
        if (savedOrderId == null) {
            throw new ExpectedException(ErrorCode.FAIL_ORDER);
        }

        // 주문된 아이템들 등록
        orderItemService.saveOrderItem(savedOrderId, userId);

        return savedOrderId;
    }

}

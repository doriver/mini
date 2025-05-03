package com.ex.mini.shop.application;

import com.ex.mini.common.argumentResolver.UserInfo;
import com.ex.mini.common.exception.ErrorCode;
import com.ex.mini.common.exception.ExpectedException;
import com.ex.mini.common.utils.UserUtils;
import com.ex.mini.shop.domain.entity.Order;
import com.ex.mini.shop.domain.entity.OrderStatus;
import com.ex.mini.shop.domain.repository.OrderRepository;
import com.ex.mini.shop.presentation.dto.request.OrderCreateDTO;
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
    private final MoneyService moneyService;
    private final CartService cartService;

    private final CartBeforeOrderService cartBeforeOrderService;

    /*
        주문 절차
        1. 주문자의 장바구니에 있는 상품들 구매할수 있는지 판단
        2. 주문진행
        3. 장바구니 비우기( 비동기로 처리하면 좋을듯 )
     */
    public Long processOrder(OrderCreateDTO orderCreateDTO, UserInfo userInfo) {
        UserUtils.checkLogin(userInfo.getUserId());

        // 구매할수 있는지 판단( 돈, 개수 )
        judgeBuyable(userInfo.getUserId());

        // 주문하기
        Long savedOrderId = createOrder(userInfo.getUserId(), orderCreateDTO.getAddress());

        // 장바구니 비우기
        cartService.emptyCartAfterOrder(userInfo.getUserId());

        return savedOrderId;
    }

    /*
        주문자의 장바구니에 있는 상품들 구매할수 있는지 판단
        1. 돈    2. 상품 개수
     */
    public void judgeBuyable(Long userId) {

        // 총 가격과 있는돈 비교하기
        boolean buyablePrice = cartBeforeOrderService.buyablePrice(userId);
        if (! buyablePrice) {
            throw new ExpectedException(ErrorCode.DONT_BUY_MONEY);
        }

        // Item 개수 확인하기
        String result = cartBeforeOrderService.buyableCount(userId);
        if (! result.equals("ok")) {
            throw new ExpectedException(result);
        }
    }


    /*
        주문하기
        작업의 단위를 생각해야함, 한꺼번에 묶을
        1.배송정보 저장    2.Order생성    3.OrderItem들 저장     4.돈 결제
     */
    @Transactional
    public Long createOrder(Long userId, String address) {

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

        // 구매자 돈 차감 , 마트 장부에 입금 처리
        moneyService.moneyTransaction(userId);

        return savedOrderId;
    }

}

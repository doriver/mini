package com.ex.mini.shop.application;

import com.ex.mini.common.argumentResolver.UserInfo;
import com.ex.mini.common.exception.ErrorCode;
import com.ex.mini.common.exception.ExpectedException;
import com.ex.mini.common.utils.UserUtils;
import com.ex.mini.shop.domain.Cart;
import com.ex.mini.shop.domain.entity.ItemInCart;
import com.ex.mini.shop.domain.entity.Order;
import com.ex.mini.shop.domain.entity.OrderStatus;
import com.ex.mini.shop.domain.repository.OrderRepository;
import com.ex.mini.shop.presentation.dto.request.OrderCreateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderService {


    private final DeliveryService deliveryService;
    private final CartService cartService;
    private final CartBeforeOrderService cartBeforeOrderService;
    private final TransactionService transactionService;

    /*
        주문 절차
        1. 주문자의 장바구니에 있는 상품들 구매할수 있는지 판단
        2. 주문진행
        3. 장바구니 비우기( 비동기로 처리하면 좋을듯 )
     */
    public Long processOrder(OrderCreateDTO orderCreateDTO, UserInfo userInfo) {
        
        UserUtils.checkLogin(userInfo.getUserId());

        // 도메인 객체
        Cart cart = new Cart();
        // 장바구니에 있는 아이템들 도메인 객체에 담기
        cartService.setItemListInCart(userInfo.getUserId(), cart);

        // 구매할수 있는지 판단( 돈, 개수 )
        judgeBuyable(userInfo.getUserId(), cart);

        // 주문하기
        Long savedOrderId = createOrder(userInfo.getUserId(), orderCreateDTO.getAddress(), cart);

        // 장바구니 비우기
        cartService.emptyCartAfterOrder(userInfo.getUserId());

        return savedOrderId;
    }

    /*
        주문자의 장바구니에 있는 상품들 구매할수 있는지 판단
        1. 돈    2. 상품 개수
     */
    public void judgeBuyable(Long userId, Cart cart) {

        // 총 가격과 있는돈 비교하기
        boolean buyablePrice = cartBeforeOrderService.buyablePrice(userId, cart);
        if (! buyablePrice) {
            throw new ExpectedException(ErrorCode.DONT_BUY_MONEY);
        }

        // Item 개수 확인하기
        String result = cartBeforeOrderService.buyableCount(userId, cart);
        if (! result.equals("ok")) {
            throw new ExpectedException(result);
        }
    }


    /*
        주문하기
        1.배송정보 저장    2.Order생성    3.OrderItem들 저장    4.Item들 개수 차감   5.돈 결제
     */
    public Long createOrder(Long userId, String address, Cart cart) {

        // 배송정보 저장
        Long savedDeliveryId = deliveryService.saveDelivery(address);

        // 2 ~ 5
        Long savedOrderId = transactionService.order(userId, savedDeliveryId, cart);

        return savedOrderId;
    }

}

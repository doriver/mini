package com.ex.mini.shop.presentation.api;

import com.ex.mini.common.ApiResponse;
import com.ex.mini.shop.application.CartService;
import com.ex.mini.shop.application.MoneyService;
import com.ex.mini.shop.application.OrderService;
import com.ex.mini.shop.presentation.dto.request.OrderCreateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderService orderService;
    private final CartService cartService;

    /*
        주문하기
        1. 주문자의 장바구니에 있는 상품들 구매할수 있는지 판단
        2. 주문진행
        3. 장바구니 비우기( 비동기로 처리하면 좋을듯 )
     */
    @PostMapping
    public ApiResponse<Long> createOrder(@RequestBody OrderCreateDTO orderCreateDTO) {

        Long userId = 1L; // 나중에 인증 적용시킬꺼임

        // 구매할수 있는지 판단( 돈, 개수 )
        orderService.judgeBuy(userId);

        // 주문하기
        Long savedOrderId = orderService.saveOrder(userId, orderCreateDTO.getAddress());

        // 장바구니 비우기
        try {
            cartService.emptyCart(userId);
        } catch (Exception ignored) { }

        return ApiResponse.success(savedOrderId);
    }
}

package com.ex.mini.shop.presentation.api;

import com.ex.mini.common.ApiResponse;
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

    /*
        주문하기
     */
    @PostMapping
    public ApiResponse<Long> createOrder(@RequestBody OrderCreateDTO orderCreateDTO) {

        Long userId = 1L; // 나중에 인증 적용시킬꺼임

        // 총 가격과 있는돈 비교하기


        // Item 개수 확인하기


        // 주문하기
        Long savedOrderId = orderService.saveOrder(userId, orderCreateDTO.getAddress());

        // 장바구니 비우기

        return ApiResponse.success(savedOrderId);
    }
}

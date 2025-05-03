package com.ex.mini.shop.presentation.api;

import com.ex.mini.common.ApiResponse;
import com.ex.mini.common.argumentResolver.UserInfo;
import com.ex.mini.shop.application.CartService;
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
    public ApiResponse<Long> processOrder(@RequestBody OrderCreateDTO orderCreateDTO, UserInfo userInfo) {
        Long savedOrderId = orderService.processOrder(orderCreateDTO, userInfo);
        return ApiResponse.success(savedOrderId);
    }
}

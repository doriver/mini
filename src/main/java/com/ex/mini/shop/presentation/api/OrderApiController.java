package com.ex.mini.shop.presentation.api;

import com.ex.mini.common.ApiResponse;
import com.ex.mini.common.argumentResolver.UserInfo;
import com.ex.mini.shop.application.CartService;
import com.ex.mini.shop.application.OrderService;
import com.ex.mini.shop.presentation.dto.request.OrderCreateDTO;
import com.ex.mini.shop.presentation.dto.response.OrderReadDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderService orderService;

    /*
        주문하기 API
     */
    @PostMapping
    public ApiResponse<Long> processOrder(@RequestBody OrderCreateDTO orderCreateDTO, UserInfo userInfo) {
        Long savedOrderId = orderService.processOrder(orderCreateDTO, userInfo);
        return ApiResponse.success(savedOrderId);
    }

    /*
        주문내역 조회 API
        요청 데이터 : x
        응답 데이터 : List<Order>
     */
    @GetMapping
    public ApiResponse<List<OrderReadDTO>> readOrders(UserInfo userInfo) {
        
    }


    /*
        주문상세 조회 API
     */
}

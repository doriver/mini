package com.ex.mini.shop.presentation.api;

import com.ex.mini.common.ApiResponse;
import com.ex.mini.shop.application.CartService;
import com.ex.mini.shop.presentation.dto.request.ItemInCartCreateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
public class CartApiController {

    private final CartService cartService;

    /*
        장바구니에 아이템 담기
     */
    @PostMapping
    public ApiResponse<Long> createCart(@RequestBody ItemInCartCreateDTO itemInCartCreateDTO) {

        Long userId = 1L; // 나중에 인증 적용시킬꺼임
        Long savedCartId = cartService.saveCart(itemInCartCreateDTO, userId);

        return ApiResponse.success(savedCartId);
    }
}

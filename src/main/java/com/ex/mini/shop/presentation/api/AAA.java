package com.ex.mini.shop.presentation.api;

import com.ex.mini.common.argumentResolver.UserInfo;
import com.ex.mini.shop.application.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shop/test")
@RequiredArgsConstructor
public class AAA {
    private final CartService cartService;

    @GetMapping("/a1")
    public void asd(UserInfo userInfo) {
        cartService.emptyCartAfterOrder(userInfo.getUserId(), null);
    }
}

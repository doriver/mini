package com.ex.mini.shop.presentation.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemInCartCreateDTO {
    private long itemId;
    private int count;
}

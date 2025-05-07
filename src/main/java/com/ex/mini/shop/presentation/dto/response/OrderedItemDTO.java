package com.ex.mini.shop.presentation.dto.response;

import lombok.Getter;

@Getter
public class OrderedItemDTO {

    private String name;

    private int count;

    private long totalPrice;
}

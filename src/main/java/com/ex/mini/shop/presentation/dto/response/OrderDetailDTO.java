package com.ex.mini.shop.presentation.dto.response;

import com.ex.mini.shop.domain.entity.DeliveryStatus;
import com.ex.mini.shop.domain.entity.OrderStatus;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class OrderDetailDTO {

    private OrderStatus orderStatus;
    private LocalDateTime createdAt;
    private List<OrderedItemDTO> orderedItems;

    private String address;
    private DeliveryStatus deliveryStatus;

    private String totalPrice;
}

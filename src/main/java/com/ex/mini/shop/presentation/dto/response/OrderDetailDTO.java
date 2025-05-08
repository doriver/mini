package com.ex.mini.shop.presentation.dto.response;

import com.ex.mini.shop.domain.entity.DeliveryStatus;
import com.ex.mini.shop.domain.entity.OrderStatus;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class OrderDetailDTO {

    // Order
    private OrderStatus orderStatus;
    private LocalDateTime createdAt;
    
    // OrderItem
    private List<OrderedItemDTO> orderedItems;

    // Delivery
    private String address;
    private DeliveryStatus deliveryStatus;

    // 그 외
    private long totalPrice;

    public OrderDetailDTO(OrderStatus orderStatus, LocalDateTime createdAt, List<OrderedItemDTO> orderedItems, String address, DeliveryStatus deliveryStatus) {
        this.orderStatus = orderStatus;
        this.createdAt = createdAt;
        this.orderedItems = orderedItems;
        this.address = address;
        this.deliveryStatus = deliveryStatus;
    }

    public void calculateAndSetTotalPrice() {
        totalPrice = 0;
        for (OrderedItemDTO orderedItemDTO :orderedItems) {
            totalPrice += orderedItemDTO.getTotalPrice();
        }
    }

}

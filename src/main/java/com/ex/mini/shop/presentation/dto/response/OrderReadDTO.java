package com.ex.mini.shop.presentation.dto.response;

import com.ex.mini.shop.domain.entity.OrderStatus;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter // 이걸로 안되면 , @NoArgsConstructor(access = AccessLevel.PROTECTED) 넣기
public class OrderReadDTO {
    private OrderStatus status;
    private LocalDateTime createdAt;

    public OrderReadDTO(OrderStatus status, LocalDateTime createdAt) {
        this.status = status;
        this.createdAt = createdAt;
    }
}

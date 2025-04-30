package com.ex.mini.shop.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/*
    cart는 redis로 할수도 있음
 */
@Entity
@Table(indexes = {
        @Index(name = "idx_user", columnList = "userId"),
        @Index(name = "idx_item", columnList = "itemId")
})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemInCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long userId;

    @NotNull
    private Long itemId;

    private int count;

    private long totalPrice;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;

    @Builder
    public ItemInCart(Long userId, Long itemId, int count, long totalPrice, LocalDateTime createdAt) {
        this.userId = userId;
        this.itemId = itemId;
        this.count = count;
        this.totalPrice = totalPrice;
        this.createdAt = createdAt;
    }
}

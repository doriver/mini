package com.ex.mini.shop.domain.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long itemId;

    private int count;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;

    @Builder
    public Cart(Long userId, Long itemId, int count, LocalDateTime createdAt) {
        this.userId = userId;
        this.itemId = itemId;
        this.count = count;
        this.createdAt = createdAt;
    }
}

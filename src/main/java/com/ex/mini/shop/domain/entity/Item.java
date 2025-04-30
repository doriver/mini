package com.ex.mini.shop.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/*
    userId, count 추가해서 유저별로 아이템 등록하는거 고려
    아니면, userId 안넣고, 제고관리쪽 이랑 엮을수도 있나?
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int price;

    private long count;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;

    @Builder
    public Item(String name, int price, long count, LocalDateTime createdAt) {
        this.name = name;
        this.price = price;
        this.count = count;
        this.createdAt = createdAt;
    }
}

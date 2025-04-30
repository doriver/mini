package com.ex.mini.user.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(indexes = {
        @Index(name = "idx_user", columnList = "userId")
})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long userId;

    private long money;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime updatedAt;

    @Builder
    public Account(Long userId, long money, LocalDateTime updatedAt) {
        this.userId = userId;
        this.money = money;
        this.updatedAt = updatedAt;
    }
}

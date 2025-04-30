package com.ex.mini.shop.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(indexes = {
        @Index(name = "idx_account", columnList = "accountId")
})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LedgerHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long accountId;

    @Enumerated(EnumType.ORDINAL)
    @NotNull
    private AccountTransfer accountTransfer;

    private long amount;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;

    @Builder
    public LedgerHistory(Long accountId, AccountTransfer accountTransfer, long amount, LocalDateTime createdAt) {
        this.accountId = accountId;
        this.accountTransfer = accountTransfer;
        this.amount = amount;
        this.createdAt = createdAt;
    }
}

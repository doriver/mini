package com.ex.mini.warehouse;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Stock {
    @Id
    private Long id;

    private Long quantity;

    public Stock() {
    }

    public Stock(Long id, Long quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void decrease(Long quantity){
        if(this.quantity - quantity < 0){
            throw new RuntimeException("재고는 0개 미만이 될 수 없습니다.");
        }

        // 현재 수량을 갱신
        this.quantity -= quantity;
    }
}

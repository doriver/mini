package com.ex.mini.shop.application;

import com.ex.mini.common.exception.ErrorCode;
import com.ex.mini.common.exception.ExpectedException;
import com.ex.mini.shop.domain.entity.Delivery;
import com.ex.mini.shop.domain.entity.DeliveryStatus;
import com.ex.mini.shop.domain.repository.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;

    /*
        배송정보 입력
     */
    public Long saveDelivery(String address) {
        Delivery delivery = new Delivery(DeliveryStatus.PREPARING, address, LocalDateTime.now());
        Long savedDeliveryId = null;
        try {
            savedDeliveryId = deliveryRepository.save(delivery).getId();
        } catch (Exception e) {
            throw new ExpectedException(ErrorCode.FAIL_SAVE_DELIVERY);
        }
        return savedDeliveryId;
    }

}

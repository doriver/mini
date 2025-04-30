package com.ex.mini.shop.application;

import com.ex.mini.common.exception.ErrorCode;
import com.ex.mini.common.exception.ExpectedException;
import com.ex.mini.shop.domain.entity.Delivery;
import com.ex.mini.shop.domain.entity.DeliveryStatus;
import com.ex.mini.shop.domain.repository.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;

    /*
        배송정보 입력
     */
    public Long saveDelivery(String address) {
        Delivery delivery = Delivery.builder()
                .status(DeliveryStatus.PREPARING).address(address)
                .build();
        Long savedDeliveryId = deliveryRepository.save(delivery).getId();
        if (savedDeliveryId == null) {
            throw new ExpectedException(ErrorCode.FAIL_SAVE_DELIVERY);
        }
        return savedDeliveryId;
    }

}

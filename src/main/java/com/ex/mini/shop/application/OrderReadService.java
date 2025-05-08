package com.ex.mini.shop.application;

import com.ex.mini.common.exception.ErrorCode;
import com.ex.mini.common.exception.ExpectedException;
import com.ex.mini.common.utils.DtoConvert;
import com.ex.mini.common.utils.UserUtils;
import com.ex.mini.shop.domain.entity.Delivery;
import com.ex.mini.shop.domain.entity.Order;
import com.ex.mini.shop.domain.entity.OrderItem;
import com.ex.mini.shop.domain.repository.DeliveryRepository;
import com.ex.mini.shop.domain.repository.OrderItemRepository;
import com.ex.mini.shop.domain.repository.OrderRepository;
import com.ex.mini.shop.presentation.dto.response.OrderDetailDTO;
import com.ex.mini.shop.presentation.dto.response.OrderReadDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderReadService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final DeliveryRepository deliveryRepository;

    /*
        특정 사용자의 주문목록
     */
    public List<OrderReadDTO> readOrders(Long userId) {
        UserUtils.checkLogin(userId);

        List<Order> orders = orderRepository.findAllByUserId(userId);
        List<OrderReadDTO> orderList = DtoConvert.ordersToOrderReadDTOs(orders);
        return orderList;
    }

    /*
        특정 사용자의 특정 주문상세
     */
    public OrderDetailDTO readOrderDetail(Long userId, Long orderId) {
        UserUtils.checkLogin(userId);

        Order order = orderRepository.findByIdAndUserId(orderId, userId)
                .orElseThrow(() -> new ExpectedException(ErrorCode.NOT_FOUND_ORDER));


        order.getStatus();
        order.getCreatedAt();

        List<OrderItem> OrderItemList = orderItemRepository.findAllByOrderId(orderId);
        if (OrderItemList.isEmpty()) {
            throw new ExpectedException(ErrorCode.NOT_FOUND_ORDERITEM);
        }

        Delivery delivery = deliveryRepository.findById(order.getDeliveryId())
                .orElseThrow(() -> new ExpectedException(ErrorCode.NOT_FOUND_DELIVERY));

    }
}

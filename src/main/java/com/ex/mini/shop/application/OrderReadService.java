package com.ex.mini.shop.application;

import com.ex.mini.common.exception.ErrorCode;
import com.ex.mini.common.exception.Expected4xxException;
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
import com.ex.mini.shop.presentation.dto.response.OrderedItemDTO;
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
//    @Transactional(readOnly = true)
    public OrderDetailDTO readOrderDetail(Long userId, Long orderId) {
        UserUtils.checkLogin(userId);

        // Order
        Order order = orderRepository.findByIdAndUserId(orderId, userId)
                .orElseThrow(() -> new Expected4xxException(ErrorCode.NOT_FOUND_ORDER));

        // OrderItem
        List<OrderItem> orderItems = orderItemRepository.findAllByOrderId(orderId);
        if (orderItems.isEmpty()) {
            throw new Expected4xxException(ErrorCode.NOT_FOUND_ORDERITEM);
        }
        List<OrderedItemDTO> orderedItemList = DtoConvert.orderItemsToDTOs(orderItems);

        // Delivery
        Delivery delivery = deliveryRepository.findById(order.getDeliveryId())
                .orElseThrow(() -> new Expected4xxException(ErrorCode.NOT_FOUND_DELIVERY));

        // DTO
        OrderDetailDTO orderDetailDTO = new OrderDetailDTO(order.getStatus(), order.getCreatedAt(), orderedItemList, delivery.getAddress(), delivery.getStatus());
        orderDetailDTO.calculateAndSetTotalPrice();
        return orderDetailDTO;
    }
}

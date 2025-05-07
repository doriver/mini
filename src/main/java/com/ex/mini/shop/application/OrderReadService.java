package com.ex.mini.shop.application;

import com.ex.mini.common.utils.DtoConvert;
import com.ex.mini.common.utils.UserUtils;
import com.ex.mini.shop.domain.entity.Order;
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

    public List<OrderReadDTO> readOrders(Long userId) {
        UserUtils.checkLogin(userId);

        List<Order> orders = orderRepository.findAllByUserId(userId);
        List<OrderReadDTO> orderList = DtoConvert.ordersToOrderReadDTOs(orders);
        return orderList;
    }


    public OrderDetailDTO readOrderDetail(Long userId, Long orderId) {

    }
}

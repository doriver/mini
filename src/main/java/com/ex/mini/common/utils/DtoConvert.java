package com.ex.mini.common.utils;

import com.ex.mini.shop.domain.entity.Order;
import com.ex.mini.shop.domain.entity.OrderItem;
import com.ex.mini.shop.presentation.dto.response.OrderReadDTO;
import com.ex.mini.shop.presentation.dto.response.OrderedItemDTO;

import java.util.ArrayList;
import java.util.List;

public class DtoConvert {

    public static List<OrderReadDTO> ordersToOrderReadDTOs(List<Order> orders) {
        List<OrderReadDTO> orderList = new ArrayList<>();
        for (Order order :orders) {
            OrderReadDTO orderReadDTO = new OrderReadDTO(order.getId(), order.getStatus(), order.getCreatedAt());
            orderList.add(orderReadDTO);
        }
        return orderList;
    }

    public static List<OrderedItemDTO> orderItemsToDTOs(List<OrderItem> orderItems) {
        List<OrderedItemDTO> orderedItemList = new ArrayList<>();
        for (OrderItem orderItem :orderItems) {
            OrderedItemDTO orderedItemDTO = new OrderedItemDTO(orderItem.getName(), orderItem.getCount(), orderItem.getTotalPrice());
            orderedItemList.add(orderedItemDTO);
        }
        return orderedItemList;
    }
}

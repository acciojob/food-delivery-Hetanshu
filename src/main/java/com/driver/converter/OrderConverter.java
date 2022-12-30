package com.driver.converter;

import com.driver.io.entity.OrderEntity;
import com.driver.model.request.OrderDetailsRequestModel;
import com.driver.model.response.OrderDetailsResponse;
import com.driver.shared.dto.OrderDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class OrderConverter {
    public static OrderDetailsResponse dtoToResponse(OrderDto orderDto) {
        return OrderDetailsResponse.builder()
                .orderId(orderDto.getOrderId())
                .cost(orderDto.getCost())
                .items(orderDto.getItems())
                .userId(orderDto.getUserId())
                .status(orderDto.isStatus()).build();
    }

    public static OrderDto entityTodto(OrderEntity orderEntity) {
        return OrderDto.builder().
                id(orderEntity.getId())
                .orderId(orderEntity.getOrderId())
                .cost(orderEntity.getCost())
                .items(orderEntity.getItems())
                .userId(orderEntity.getUserId())
                .status(orderEntity.isStatus())
                .build();
    }

    public static OrderDto requestTodto(OrderDetailsRequestModel order) {
        return OrderDto.builder()
                .cost(order.getCost())
                .items(order.getItems())
                .userId(order.getUserId())
                .build();
    }

    public static OrderEntity dtoToentity(OrderDto order) {
        return OrderEntity.builder()
                .orderId(order.getOrderId())
                .cost(order.getCost())
                .items(order.getItems())
                .userId(order.getUserId())
                .status(order.isStatus()).build();
    }
}

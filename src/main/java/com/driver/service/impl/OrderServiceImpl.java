package com.driver.service.impl;

import com.driver.converter.OrderConverter;
import com.driver.io.entity.OrderEntity;
import com.driver.io.repository.OrderRepository;
import com.driver.service.OrderService;
import com.driver.shared.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Override
    public OrderDto createOrder(OrderDto order) {
        OrderEntity orderEntity=OrderConverter.dtoToentity(order);
        orderRepository.save(orderEntity);
        return order;
    }

    @Override
    public OrderDto getOrderById(String orderId) throws Exception {
        OrderEntity orderEntity=orderRepository.findByOrderId(orderId);
        return OrderConverter.entityTodto(orderEntity);
    }

    @Override
    public OrderDto updateOrderDetails(String orderId, OrderDto order) throws Exception {
        long id=Long.parseLong(orderId);
        OrderEntity orderEntity=orderRepository.findById(id).get();
        OrderEntity newEntity=OrderEntity.builder().id(orderEntity.getId()).orderId(order.getOrderId()).cost(order.getCost())
                .items(order.getItems()).userId(order.getUserId()).status(order.isStatus()).build();
        orderRepository.save(newEntity);
        return OrderConverter.entityTodto(newEntity);
    }

    @Override
    public void deleteOrder(String orderId) throws Exception {
        long id=Long.parseLong(orderId);
        orderRepository.deleteById(id);
    }

    @Override
    public List<OrderDto> getOrders() {
        List<OrderEntity> orderEntityList= (List<OrderEntity>) orderRepository.findAll();
        List<OrderDto> orderDtoList=new ArrayList<>();
        for(OrderEntity orderEntity:orderEntityList)
            orderDtoList.add(OrderConverter.entityTodto(orderEntity));
        return orderDtoList;
    }
}
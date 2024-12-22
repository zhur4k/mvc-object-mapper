package com.mvc.service.impl;

import com.mvc.dto.OrderCreateDto;
import com.mvc.dto.OrderUpdateDto;
import com.mvc.dto.mapper.OrderCreateMapper;
import com.mvc.dto.mapper.OrderUpdateMapper;
import com.mvc.model.Order;
import com.mvc.repository.OrderRepository;
import com.mvc.service.OrderService;

import java.util.List;

public class OrdersServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    private final OrderCreateMapper orderCreateMapper;

    private final OrderUpdateMapper orderUpdateMapper;

    public OrdersServiceImpl(OrderRepository orderRepository, OrderCreateMapper orderCreateMapper, OrderUpdateMapper orderUpdateMapper) {
        this.orderRepository = orderRepository;
        this.orderCreateMapper = orderCreateMapper;
        this.orderUpdateMapper = orderUpdateMapper;
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public void create(OrderCreateDto order) {
        orderRepository.save(orderCreateMapper.apply(order));
    }

    @Override
    public void update(OrderUpdateDto order) {
        orderRepository.save(orderUpdateMapper.apply(order));
    }

    @Override
    public void delete(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(RuntimeException::new);
        orderRepository.delete(order);
    }
}

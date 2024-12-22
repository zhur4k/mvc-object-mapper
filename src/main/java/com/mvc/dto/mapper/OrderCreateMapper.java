package com.mvc.dto.mapper;

import com.mvc.dto.OrderCreateDto;
import com.mvc.model.Order;

import java.util.function.Function;

public class OrderCreateMapper implements Function<OrderCreateDto, Order> {
    @Override
    public Order apply(OrderCreateDto orderCreateDto) {
        Order order = new Order();
        order.setAddress(orderCreateDto.address());
        order.setCustomer(orderCreateDto.customer());
        order.setPrice(orderCreateDto.price());
        order.setStatus(orderCreateDto.status());
        order.setOrderProducts(orderCreateDto.orderProducts());
        return order;
    }
}

package com.mvc.dto.mapper;

import com.mvc.dto.OrderUpdateDto;
import com.mvc.model.Order;

import java.util.function.Function;

public class OrderUpdateMapper implements Function<OrderUpdateDto, Order> {
    @Override
    public Order apply(OrderUpdateDto orderUpdateDto) {
        Order order = new Order();
        order.setId(orderUpdateDto.id());
        order.setAddress(orderUpdateDto.address());
        order.setPrice(orderUpdateDto.price());
        order.setStatus(orderUpdateDto.status());
        order.setOrderProducts(orderUpdateDto.orderProducts());
        return order;
    }
}

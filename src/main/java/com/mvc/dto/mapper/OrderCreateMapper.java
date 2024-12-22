package com.mvc.dto.mapper;

import com.mvc.dto.OrderCreateDto;
import com.mvc.model.Order;

import java.util.function.Function;

public class OrderCreateMapper implements Function<OrderCreateDto, Order> {
    @Override
    public Order apply(OrderCreateDto orderCreateDto) {
        Order order = new Order();
        order.setCustomer(orderCreateDto.customer());
        order.setOrderDate(orderCreateDto.orderDate());
        order.setTotalPrice(orderCreateDto.totalPrice());
        order.setOrderStatus(orderCreateDto.orderStatus());
        order.setShippingAddress(orderCreateDto.shippingAddress());
        return order;
    }
}

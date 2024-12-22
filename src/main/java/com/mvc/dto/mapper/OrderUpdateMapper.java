package com.mvc.dto.mapper;

import com.mvc.dto.OrderUpdateDto;
import com.mvc.model.Order;

import java.util.function.Function;

public class OrderUpdateMapper implements Function<OrderUpdateDto, Order> {
    @Override
    public Order apply(OrderUpdateDto orderUpdateDto) {
        Order order = new Order();
        order.setOrderId(orderUpdateDto.orderId());
        order.setCustomer(orderUpdateDto.customer());
        order.setOrderDate(orderUpdateDto.orderDate());
        order.setTotalPrice(orderUpdateDto.totalPrice());
        order.setOrderStatus(orderUpdateDto.orderStatus());
        order.setShippingAddress(orderUpdateDto.shippingAddress());
        return order;
    }
}

package com.mvc.service;

import com.mvc.dto.OrderCreateDto;
import com.mvc.dto.OrderUpdateDto;
import com.mvc.model.Order;

import java.util.List;

public interface OrderService {
    
    List<Order> findAll();

    void create(OrderCreateDto order);

    void update(OrderUpdateDto order);

    void delete(Long id);
}

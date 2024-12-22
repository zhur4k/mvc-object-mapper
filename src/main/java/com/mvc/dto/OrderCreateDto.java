package com.mvc.dto;

import com.mvc.model.Customer;
import com.mvc.model.OrderProduct;
import com.mvc.model.Status;

import java.math.BigDecimal;
import java.util.Set;

public record OrderCreateDto(

        String address,

        BigDecimal price,

        Status status,

        Set<OrderProduct> orderProducts,

        Customer customer
) {
}

package com.mvc.dto;

import com.mvc.model.Customer;
import com.mvc.model.Product;
import com.mvc.model.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderCreateDto(

        Status orderStatus,

        LocalDateTime orderDate,

        BigDecimal totalPrice,

        String shippingAddress,

        List<Product> products,

        Customer customer
) {
}

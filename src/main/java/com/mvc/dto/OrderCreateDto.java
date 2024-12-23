package com.mvc.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mvc.model.Customer;
import com.mvc.model.Product;
import com.mvc.model.Status;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderCreateDto(

        @NotNull(message = "Order status cannot be null")
        Status orderStatus,

        @NotNull(message = "Order date cannot be null")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSS")
        LocalDateTime orderDate,

        @NotNull(message = "Total price cannot be null")
        @DecimalMin(value = "0.0", inclusive = false, message = "Total price must be greater than 0")
        BigDecimal totalPrice,

        @NotEmpty(message = "Shipping address cannot be empty")
        @Size(max = 255, message = "Shipping address must not exceed 255 characters")
        String shippingAddress,

        @NotEmpty(message = "Product list cannot be empty")
        List<@NotNull(message = "Product cannot be null") Product> products,

        @NotNull(message = "Customer cannot be null")
        Customer customer
) {
}
package com.mvc.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record ProductUpdateDto(

        @NotNull(message = "Product ID cannot be null")
        Long productId,

        @NotEmpty(message = "Product name cannot be empty")
        @Size(max = 100, message = "Product name must not exceed 100 characters")
        String name,

        @NotEmpty(message = "Product description cannot be empty")
        @Size(max = 500, message = "Product description must not exceed 500 characters")
        String description,

        @NotNull(message = "Price cannot be null")
        @DecimalMin(value = "0.01", inclusive = true, message = "Price must be greater than or equal to 0.01")
        BigDecimal price,

        @DecimalMin(value = "0", inclusive = true, message = "Quantity in stock must be greater than or equal to 0")
        int quantityInStock
) {
}

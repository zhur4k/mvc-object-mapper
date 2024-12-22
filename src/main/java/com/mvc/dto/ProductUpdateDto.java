package com.mvc.dto;

import java.math.BigDecimal;

public record ProductUpdateDto(
        Long productId,

        String name,

        String description,

        BigDecimal price,

        int quantityInStock
) {
}

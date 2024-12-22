package com.mvc.dto;

import java.math.BigDecimal;

public record OrderUpdateDto(
        Long id,

        String name,

        String description,

        BigDecimal price,

        int stock
) {
}

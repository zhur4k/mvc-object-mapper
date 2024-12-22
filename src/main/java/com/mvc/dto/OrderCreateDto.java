package com.mvc.dto;

import java.math.BigDecimal;

public record OrderCreateDto(
        String name,

        String description,

        BigDecimal price,

        int stock
) {
}

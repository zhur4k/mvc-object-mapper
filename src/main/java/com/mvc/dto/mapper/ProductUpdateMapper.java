package com.mvc.dto.mapper;

import com.mvc.dto.ProductUpdateDto;
import com.mvc.model.Product;

import java.util.function.Function;

public class ProductUpdateMapper implements Function<ProductUpdateDto, Product> {
    @Override
    public Product apply(ProductUpdateDto productUpdateDto) {
        Product product = new Product();
        product.setId(productUpdateDto.id());
        product.setDescription(productUpdateDto.description());
        product.setName(productUpdateDto.name());
        product.setPrice(productUpdateDto.price());
        product.setStock(productUpdateDto.stock());
        return product;
    }
}
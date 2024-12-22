package com.mvc.dto.mapper;

import com.mvc.dto.ProductCreateDto;
import com.mvc.model.Product;

import java.util.function.Function;

public class ProductCreateMapper implements Function<ProductCreateDto, Product> {
    @Override
    public Product apply(ProductCreateDto productCreateDto) {
        Product product = new Product();
        product.setDescription(productCreateDto.description());
        product.setName(productCreateDto.name());
        product.setPrice(productCreateDto.price());
        product.setQuantityInStock(productCreateDto.quantityInStock());
        return product;
    }
}

package com.mvc.service;

import com.mvc.dto.ProductCreateDto;
import com.mvc.dto.ProductUpdateDto;
import com.mvc.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAll();

    public Product findById(Long id);

    void create(ProductCreateDto product);

    void update(ProductUpdateDto product);

    void delete(Long id);
}

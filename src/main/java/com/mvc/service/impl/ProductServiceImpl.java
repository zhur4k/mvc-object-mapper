package com.mvc.service.impl;

import com.mvc.dto.ProductCreateDto;
import com.mvc.dto.ProductUpdateDto;
import com.mvc.dto.mapper.ProductCreateMapper;
import com.mvc.dto.mapper.ProductUpdateMapper;
import com.mvc.model.Product;
import com.mvc.repository.ProductRepository;
import com.mvc.service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    private final ProductCreateMapper productCreateMapper;

    private final ProductUpdateMapper productUpdateMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductCreateMapper productCreateMapper, ProductUpdateMapper productUpdateMapper) {
        this.productRepository = productRepository;
        this.productCreateMapper = productCreateMapper;
        this.productUpdateMapper = productUpdateMapper;
    }


    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public void create(ProductCreateDto product) {
        productRepository.save(productCreateMapper.apply(product));
    }

    @Override
    public void update(ProductUpdateDto product) {
        productRepository.save(productUpdateMapper.apply(product));
    }

    @Override
    public void delete(Long id) {
        Product product = productRepository.findById(id).orElseThrow(RuntimeException::new);
        productRepository.delete(product);
    }
}

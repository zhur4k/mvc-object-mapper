package com.mvc.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mvc.dto.ProductCreateDto;
import com.mvc.dto.ProductUpdateDto;
import com.mvc.service.ProductService;
import jakarta.validation.Validator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/products")
public class ProductController {
    private final ProductService productService;

    private final ObjectMapper objectMapper;

    private final Validator validator;

    public ProductController(ProductService productService, ObjectMapper objectMapper, Validator validator) {
        this.productService = productService;
        this.objectMapper = objectMapper;
        this.validator = validator;
    }

    @GetMapping
    public ResponseEntity<?> getAllProducts() throws Exception {
        return ResponseEntity.ok(objectMapper.writeValueAsString(productService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(objectMapper.writeValueAsString(productService.findById(id)));
    }
    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@RequestBody String productCreateDtoJson) throws JsonProcessingException {
        ProductCreateDto productCreateDto = objectMapper.readValue(productCreateDtoJson, ProductCreateDto.class);
        var violation = validator.validate(productCreateDto);
        if(violation.isEmpty()){
            throw new IllegalArgumentException();
        }
        productService.create(productCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateProduct(@RequestBody String productUpdateDtoJson) throws JsonProcessingException {
        ProductUpdateDto productUpdateDto = objectMapper.readValue(productUpdateDtoJson, ProductUpdateDto.class);
        var violation = validator.validate(productUpdateDto);
        if(violation.isEmpty()){
            throw new IllegalArgumentException();
        }
        productService.update(productUpdateDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.ok().build();
    }
}

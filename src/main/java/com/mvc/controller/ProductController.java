package com.mvc.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mvc.dto.ProductCreateDto;
import com.mvc.dto.ProductUpdateDto;
import com.mvc.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/products")
public class ProductController {
    private final ProductService productService;

    private final ObjectMapper objectMapper;

    public ProductController(ProductService productService, ObjectMapper objectMapper) {
        this.productService = productService;
        this.objectMapper = objectMapper;
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
        productService.create(objectMapper.readValue(productCreateDtoJson, ProductCreateDto.class));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateProduct(@RequestBody String productUpdateDtoJson) throws JsonProcessingException {
        productService.update(objectMapper.readValue(productUpdateDtoJson, ProductUpdateDto.class));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.ok().build();
    }
}

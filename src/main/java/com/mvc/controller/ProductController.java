package com.mvc.controller;

import com.mvc.dto.ProductCreateDto;
import com.mvc.dto.ProductUpdateDto;
import com.mvc.model.Product;
import com.mvc.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.findAll());
    }

    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@RequestBody ProductCreateDto productCreateDto) {
        productService.create(productCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateProduct(@RequestBody ProductUpdateDto productUpdateDto) {
        productService.update(productUpdateDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.ok().build();
    }
}
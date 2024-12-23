package com.mvc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mvc.dto.ProductCreateDto;
import com.mvc.dto.ProductUpdateDto;
import com.mvc.model.Product;
import com.mvc.service.ProductService;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private Validator validator;

    @Test
    void getAllProductsSuccess() throws Exception {
        List<Product> Products = new ArrayList<>();

        Product product1 = new Product();
        product1.setProductId(1L);
        product1.setName("Product 1");
        product1.setDescription("Description of Product 1");
        product1.setPrice(BigDecimal.valueOf(100.00));
        product1.setQuantityInStock(50);

        Product product2 = new Product();
        product2.setProductId(2L);
        product2.setName("Product 2");
        product2.setDescription("Description of Product 2");
        product2.setPrice(BigDecimal.valueOf(150.00));
        product2.setQuantityInStock(30);

        List<Product> products = List.of(product1, product2);
        when(productService.findAll()).thenReturn(products);

        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(products)));
    }

    @Test
    void getAllProductsException() throws Exception {
        when(productService.findAll()).thenThrow(new RuntimeException());

        mockMvc.perform(get("/api/products"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void getProductByIdSuccess() throws Exception {
        Product product = new Product();
        product.setProductId(1L);
        product.setName("Product 1");
        product.setDescription("Description of Product 1");
        product.setPrice(BigDecimal.valueOf(100.00));
        product.setQuantityInStock(50);

        when(productService.findById(1L)).thenReturn(product);

        mockMvc.perform(get("/api/products/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(product)));
    }

    @Test
    void getProductByIdException() throws Exception {
        when(productService.findById(1L)).thenThrow(new RuntimeException());

        mockMvc.perform(get("/api/products/{id}", 1L))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void createProductSuccess() throws Exception {
        ProductCreateDto product = new ProductCreateDto(
                "Laptop",
                "A high-performance laptop with 16GB RAM and 512GB SSD",
                new BigDecimal("999.99"),
                50
        );

        mockMvc.perform(post("/api/products/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isCreated());
    }

    @Test
    void createProductException() throws Exception {
        ProductCreateDto product = new ProductCreateDto(
                "Laptop",
                "A high-performance laptop with 16GB RAM and 512GB SSD",
                new BigDecimal("999.99"),
                50
        );

        doThrow(new RuntimeException()).when(productService).create(any(ProductCreateDto.class));

        mockMvc.perform(post("/api/products/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void updateProductSuccess() throws Exception {
        ProductUpdateDto product = new ProductUpdateDto(
                1L,
                "Laptop",
                "A high-performance laptop with 16GB RAM and 512GB SSD",
                new BigDecimal("999.99"),
                50
        );

        doNothing().when(productService).update(product);
        mockMvc.perform(put("/api/products/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isOk());
    }

    @Test
    void updateProductException() throws Exception {
        ProductUpdateDto product = new ProductUpdateDto(
                1L,
                "Laptop",
                "A high-performance laptop with 16GB RAM and 512GB SSD",
                new BigDecimal("999.99"),
                50
        );
        doThrow(new RuntimeException()).when(productService).update(product);

        mockMvc.perform(put("/api/products/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void deleteProductSuccess() throws Exception {
        mockMvc.perform(delete("/api/products/delete/{id}", 1L))
                .andExpect(status().isOk());

        verify(productService,times(1)).delete(1L);
    }

    @Test
    void deleteProductException() throws Exception {
        doThrow(new RuntimeException()).when(productService).delete(1L);

        mockMvc.perform(delete("/api/products/delete/{id}", 1L))
                .andExpect(status().isInternalServerError());
    }
}
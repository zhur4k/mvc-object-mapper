package com.mvc.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mvc.dto.OrderCreateDto;
import com.mvc.dto.OrderUpdateDto;
import com.mvc.service.OrderService;
import jakarta.validation.Validator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/orders")
public class OrderController {
    private final OrderService orderService;

    private final ObjectMapper objectMapper;

    private final Validator validator;

    public OrderController(OrderService orderService, ObjectMapper objectMapper, Validator validator) {
        this.orderService = orderService;
        this.objectMapper = objectMapper;
        this.validator = validator;
    }

    @GetMapping
    public ResponseEntity<?> getAllOrders() throws Exception {
        return ResponseEntity.ok(objectMapper.writeValueAsString(orderService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(objectMapper.writeValueAsString(orderService.findById(id)));
    }

    @PostMapping("/create")
    public ResponseEntity<?> createOrder(@RequestBody String orderCreateDtoJson) throws Exception {
        OrderCreateDto orderCreateDto = objectMapper.readValue(orderCreateDtoJson, OrderCreateDto.class);
        var violations = validator.validate(orderCreateDto);
        if (!violations.isEmpty()) {
            throw new IllegalArgumentException();
        }
        orderService.create(orderCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateOrder(@RequestBody String orderUpdateDtoJson) throws Exception {
        OrderUpdateDto orderUpdateDto = objectMapper.readValue(orderUpdateDtoJson, OrderUpdateDto.class);
        var violations = validator.validate(orderUpdateDto);
        if (!violations.isEmpty()) {
            throw new IllegalArgumentException();
        }
        orderService.update(orderUpdateDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        orderService.delete(id);
        return ResponseEntity.ok().build();
    }
}

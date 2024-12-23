package com.mvc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mvc.dto.OrderCreateDto;
import com.mvc.dto.OrderUpdateDto;
import com.mvc.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/orders")
public class OrderController {
    private final OrderService orderService;

    private final ObjectMapper objectMapper;

    public OrderController(OrderService orderService, ObjectMapper objectMapper) {
        this.orderService = orderService;
        this.objectMapper = objectMapper;
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
    public ResponseEntity<?> createOrder(@RequestBody String orderCreateDtoJson) {
        orderService.create(objectMapper.convertValue(orderCreateDtoJson, OrderCreateDto.class));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateOrder(@RequestBody String orderUpdateDtoJson) {
        orderService.update(objectMapper.convertValue(orderUpdateDtoJson, OrderUpdateDto.class));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        orderService.delete(id);
        return ResponseEntity.ok().build();
    }
}

package com.mvc.controller;

import com.mvc.dto.OrderCreateDto;
import com.mvc.dto.OrderUpdateDto;
import com.mvc.model.Order;
import com.mvc.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.findAll());
    }

    @PostMapping("/create")
    public ResponseEntity<?> createOrder(@RequestBody OrderCreateDto orderCreateDto) {
        orderService.create(orderCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateOrder(@RequestBody OrderUpdateDto orderUpdateDto) {
        orderService.update(orderUpdateDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        orderService.delete(id);
        return ResponseEntity.ok().build();
    }
}
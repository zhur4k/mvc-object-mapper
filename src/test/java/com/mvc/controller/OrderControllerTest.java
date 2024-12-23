package com.mvc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mvc.dto.OrderCreateDto;
import com.mvc.dto.OrderUpdateDto;
import com.mvc.model.Customer;
import com.mvc.model.Order;
import com.mvc.model.Product;
import com.mvc.model.Status;
import com.mvc.service.OrderService;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrderController.class)
class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private OrderService orderService;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private Validator validator;

    @Test
    void getAllOrdersSuccess() throws Exception {
        List<Order> orders = new ArrayList<>();

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

        Customer customer1 = new Customer();
        customer1.setCustomerId(1L);
        customer1.setFirstName("John");
        customer1.setLastName("Doe");
        customer1.setEmail("john.doe@example.com");
        customer1.setContactNumber("1234567890");

        Customer customer2 = new Customer();
        customer2.setCustomerId(2L);
        customer2.setFirstName("Jane");
        customer2.setLastName("Smith");
        customer2.setEmail("jane.smith@example.com");
        customer2.setContactNumber("0987654321");

        Order order1 = new Order();
        order1.setOrderId(1L);
        order1.setOrderStatus(Status.COMPLETED);
        order1.setOrderDate(LocalDateTime.now().minusDays(1));
        order1.setTotalPrice(BigDecimal.valueOf(250.00));
        order1.setShippingAddress("123 Main Street, Cityville");
        order1.setProducts(List.of(product1, product2));
        order1.setCustomer(customer1);

        Order order2 = new Order();
        order2.setOrderId(2L);
        order2.setOrderStatus(Status.FAILED);
        order2.setOrderDate(LocalDateTime.now().minusDays(2));
        order2.setTotalPrice(BigDecimal.valueOf(300.00));
        order2.setShippingAddress("456 Oak Street, Townsville");
        order2.setProducts(List.of(product2));
        order2.setCustomer(customer2);

        orders.add(order1);
        orders.add(order2);

        when(orderService.findAll()).thenReturn(orders);

        mockMvc.perform(get("/api/orders"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(orders)));
    }

    @Test
    void getAllOrdersException() throws Exception {
        when(orderService.findAll()).thenThrow(new RuntimeException());

        mockMvc.perform(get("/api/orders"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void getOrderByIdSuccess() throws Exception {
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

        Customer customer = new Customer();
        customer.setCustomerId(1L);
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("john.doe@example.com");
        customer.setContactNumber("1234567890");

        Order order = new Order();
        order.setOrderId(1L);
        order.setOrderStatus(Status.COMPLETED);
        order.setOrderDate(LocalDateTime.now().minusDays(1));
        order.setTotalPrice(BigDecimal.valueOf(250.00));
        order.setShippingAddress("123 Main Street, Cityville");
        order.setProducts(List.of(product1, product2));
        order.setCustomer(customer);

        when(orderService.findById(1L)).thenReturn(order);

        mockMvc.perform(get("/api/orders/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(order)));
    }

    @Test
    void getOrderByIdException() throws Exception {
        when(orderService.findById(1L)).thenThrow(new RuntimeException());

        mockMvc.perform(get("/api/orders/{id}", 1L))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void createOrderSuccess() throws Exception {
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

        Customer customer = new Customer();
        customer.setCustomerId(1L);
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("john.doe@example.com");
        customer.setContactNumber("1234567890");

        OrderCreateDto orderCreateDto = new OrderCreateDto(
                Status.COMPLETED,
                LocalDateTime.now(),
                new BigDecimal("250.00"),
                "123 Main Street, Cityville",
                List.of(product1, product2),
                customer
        );

        mockMvc.perform(post("/api/orders/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderCreateDto)))
                .andExpect(status().isCreated());
    }

    @Test
    void createOrderException() throws Exception {
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

        Customer customer = new Customer();
        customer.setCustomerId(1L);
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("john.doe@example.com");
        customer.setContactNumber("1234567890");

        OrderCreateDto orderCreateDto = new OrderCreateDto(
                Status.COMPLETED,
                LocalDateTime.now(),
                new BigDecimal("250.00"),
                "123 Main Street, Cityville",
                List.of(product1, product2),
                customer
        );

        doThrow(new RuntimeException()).when(orderService).create(any(OrderCreateDto.class));

        mockMvc.perform(post("/api/orders/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderCreateDto)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void updateOrderSuccess() throws Exception {
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

        Customer customer = new Customer();
        customer.setCustomerId(1L);
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("john.doe@example.com");
        customer.setContactNumber("1234567890");

        OrderUpdateDto orderUpdateDto = new OrderUpdateDto(
                1L,
                Status.COMPLETED,
                LocalDateTime.now(),
                new BigDecimal("250.00"),
                "123 Main Street, Cityville",
                List.of(product1, product2),
                customer
        );

        doNothing().when(orderService).update(orderUpdateDto);
        mockMvc.perform(put("/api/orders/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderUpdateDto)))
                .andExpect(status().isOk());
    }

    @Test
    void updateOrderException() throws Exception {
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

        Customer customer = new Customer();
        customer.setCustomerId(1L);
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("john.doe@example.com");
        customer.setContactNumber("1234567890");

        OrderUpdateDto orderUpdateDto = new OrderUpdateDto(
                1L,
                Status.COMPLETED,
                LocalDateTime.now(),
                new BigDecimal("250.00"),
                "123 Main Street, Cityville",
                List.of(product1, product2),
                customer
        );

        doThrow(new RuntimeException()).when(orderService).update(any(OrderUpdateDto.class));

        mockMvc.perform(put("/api/orders/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderUpdateDto)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void deleteOrderSuccess() throws Exception {
        mockMvc.perform(delete("/api/orders/delete/{id}", 1L))
                .andExpect(status().isOk());

        verify(orderService,times(1)).delete(1L);
    }

    @Test
    void deleteOrderException() throws Exception {
        doThrow(new RuntimeException()).when(orderService).delete(1L);

        mockMvc.perform(delete("/api/orders/delete/{id}", 1L))
                .andExpect(status().isInternalServerError());
    }
}
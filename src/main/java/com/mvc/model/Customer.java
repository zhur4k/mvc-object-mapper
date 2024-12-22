package com.mvc.model;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String phone;
}

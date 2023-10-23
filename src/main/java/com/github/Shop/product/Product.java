package com.github.Shop.product;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Builder
@Data
@Table(name = "PRODUCTS")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final UUID uuid;
    private final String name;
    private final String category;
    private final BigDecimal price;
    private final String currency;
    private final String description;
}

package com.semicolon.Ecommmerce.models;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID Id;
    private String productName;
    private BigDecimal productPrice;
    private String productDescription;
    private ProductCategory productCategory;
}

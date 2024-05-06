package com.semicolon.Ecommmerce.models;


import com.semicolon.Ecommmerce.attributeConverter.ProductAttributeConverter;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "store-Items")
public class Item {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    @Convert(converter = ProductAttributeConverter.class)
    private List<Product> products;
    private int numberOfProduct;
 }




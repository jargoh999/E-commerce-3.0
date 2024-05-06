package com.semicolon.Ecommmerce.models;
import com.semicolon.Ecommmerce.attributeConverter.ItemAttributeConverter;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Data
@Entity
@Table(name = "carts")
public class ShoppingCart {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long id;
    @Convert(converter = ItemAttributeConverter.class)
    @OneToMany(fetch = FetchType.EAGER)
    private List<Item> items;
    private int numberOfItems;
    private String cartOwner;
}


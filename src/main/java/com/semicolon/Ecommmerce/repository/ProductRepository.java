package com.semicolon.Ecommmerce.repository;


import com.semicolon.Ecommmerce.models.Product;
import com.semicolon.Ecommmerce.models.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product,Long> {
    Product findProductByProductCategory(ProductCategory productCategory);
    Product findProductByProductPrice(BigDecimal price);
    Product findProductByProductName(String name);
    Product findProductByProductDescription(String Description);
    Product findProductById(UUID id);
}

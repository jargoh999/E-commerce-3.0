package com.semicolon.Ecommmerce.repository;


import com.semicolon.Ecommmerce.models.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Long> {
    ShoppingCart findShoppingCartByNumberOfItems(int numberOfItems);

    ShoppingCart findShoppingCartById(Long id);

    ShoppingCart findShoppingCartByCartOwner(String owner);
}

package com.semicolon.Ecommmerce.repository;


import com.semicolon.Ecommmerce.models.Item;
import com.semicolon.Ecommmerce.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item,Long> {
    Item findItemById(Long id);
    Item findItemByNumberOfProduct(int numberOfProduct);

}

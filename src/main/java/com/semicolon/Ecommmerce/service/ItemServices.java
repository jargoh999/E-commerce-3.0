package com.semicolon.Ecommmerce.service;

import com.semicolon.Ecommmerce.dto.RequestAndResponse;
import com.semicolon.Ecommmerce.models.Item;
import com.semicolon.Ecommmerce.models.Product;
import com.semicolon.Ecommmerce.repository.ItemRepository;
import com.semicolon.Ecommmerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ItemServices {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ProductRepository productRepository;

    public Item findItemById(Long id) {
        return itemRepository.findItemById(id);
    }

    public Item addProductAsItem(RequestAndResponse requestAndResponse) {
                   List<Product> products = new ArrayList<>();
                  Item item = new Item();
                  final Product product =productRepository.findProductById(requestAndResponse.getProductId());
                  for (int productCounter = 1; productCounter<=requestAndResponse.getNoOfProduct(); productCounter++) {
                           products.add(product);
                  }
                  item.setProducts(products);
                  item.setNumberOfProduct(requestAndResponse.getNoOfProduct());
                  return itemRepository.save(item);
    }

    public Item removeProductFromItem(RequestAndResponse requestAndResponse){
        Item item = itemRepository.findItemById(requestAndResponse.getItemId());
        List<Product> products2 = item.getProducts();
        final Product product =productRepository.findProductById(requestAndResponse.getProductId());
        for (int productCounter = 1; productCounter<=requestAndResponse.getNoOfProduct(); productCounter++) {
            products2.remove(product);
        }
        item.setProducts(products2);
        item.setNumberOfProduct(item.getNumberOfProduct()-requestAndResponse.getNoOfProduct());
        return itemRepository.save(item);
    }


    public List<Item> findAllItems() {
        return itemRepository.findAll();
    }
}

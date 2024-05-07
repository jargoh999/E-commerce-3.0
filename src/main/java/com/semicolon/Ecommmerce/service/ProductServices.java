package com.semicolon.Ecommmerce.service;

import com.semicolon.Ecommmerce.dto.RequestAndResponse;
import com.semicolon.Ecommmerce.models.Product;
import com.semicolon.Ecommmerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;


@Service
public class ProductServices {
    @Autowired
    private ProductRepository productRepository;

   public List<Product> findAllProducts(){
     return productRepository.findAll();
   }

   public RequestAndResponse addProduct(Product  product){
        RequestAndResponse response = new RequestAndResponse();
        var theProduct =productRepository.save(product);
        response.setProductName(product.getProductName());
        response.setProductId(theProduct.getId());
        response.setProductPrice(Double.parseDouble(String.valueOf(theProduct.getProductPrice())));
        response.setProductDescription(theProduct.getProductDescription());
        response.setProductCategory(theProduct.getProductCategory());
        return response;
   }

   public Product findProductsById(UUID id){
        return productRepository.findProductById(id);
   }
}

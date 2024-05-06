package com.semicolon.Ecommmerce.attributeConverter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.semicolon.Ecommmerce.models.Product;
import jakarta.persistence.AttributeConverter;


public class ProductAttributeConverter implements AttributeConverter<Product,String> {
   private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Product product) {
        try {
            return objectMapper.writeValueAsString(product);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product convertToEntityAttribute(String value) {
        try {
            return objectMapper.readValue(value, Product.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

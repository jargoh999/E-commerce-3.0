package com.semicolon.Ecommmerce.attributeConverter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.semicolon.Ecommmerce.models.Item;
import jakarta.persistence.AttributeConverter;


public class ItemAttributeConverter implements AttributeConverter<Item,String> {
    private static final ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public String convertToDatabaseColumn(Item item) {
        try {
            return objectMapper.writeValueAsString(item);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Item convertToEntityAttribute(String value) {
        try {
            return objectMapper.readValue(value, Item.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

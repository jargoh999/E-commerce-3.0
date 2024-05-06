package com.semicolon.Ecommmerce.attributeConverter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.semicolon.Ecommmerce.models.Address;
import jakarta.persistence.AttributeConverter;


public class AddressAttributeConverter implements AttributeConverter<Address, String> {
  private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Address address) {
        try {
            return objectMapper.writeValueAsString(address);
        }catch (JsonProcessingException jsonProcessingException){
                return jsonProcessingException.getMessage();
        }
    }

    @Override
    public Address convertToEntityAttribute(String value) {
            try{
                return objectMapper.readValue(value, Address.class);
            }catch (JsonProcessingException jsonProcessingException){
                      return null;
            }
    }
}

package com.semicolon.Ecommmerce.attributeConverter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.semicolon.Ecommmerce.models.CreditCardInfo;
import jakarta.persistence.AttributeConverter;

public class CreditCardInfoAttributeConverter implements AttributeConverter<CreditCardInfo,String> {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(CreditCardInfo creditCardInfo) {
        try {
            return objectMapper.writeValueAsString(creditCardInfo);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CreditCardInfo convertToEntityAttribute(String value) {
        try {
            return objectMapper.readValue(value, CreditCardInfo.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

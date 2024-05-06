package com.semicolon.Ecommmerce.attributeConverter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.semicolon.Ecommmerce.models.BillingInformation;
import jakarta.persistence.AttributeConverter;

public class BillingInformationConverter implements AttributeConverter<BillingInformation,String> {
     private static  final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(BillingInformation billingInformation) {
                  try{
                      return objectMapper.writeValueAsString(billingInformation);
                  }catch (JsonProcessingException jsonProcessingException){
                           return jsonProcessingException.getMessage();
                  }
    }

    @Override
    public BillingInformation convertToEntityAttribute(String value) {
           try {
                return objectMapper.readValue(value, BillingInformation.class);
           }catch (JsonProcessingException jsonProcessingException){
                   return null;
           }
    }
}

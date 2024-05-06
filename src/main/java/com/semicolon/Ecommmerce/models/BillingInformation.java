package com.semicolon.Ecommmerce.models;

import com.semicolon.Ecommmerce.attributeConverter.AddressAttributeConverter;
import com.semicolon.Ecommmerce.attributeConverter.CreditCardInfoAttributeConverter;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class BillingInformation {
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  private Long Id;
  private String phoneNumber;
  @Convert(converter = AddressAttributeConverter.class)
  private Address deliveryAddress;
  @Convert(converter = CreditCardInfoAttributeConverter.class)
  private CreditCardInfo cardInfo;
}


package com.semicolon.Ecommmerce.models;
import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "card-info's")
public class CreditCardInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String cardCvv;
    private String expirationDate;
    private String cardNumber;
    private String nameOnCard;
    private CardType card;
}
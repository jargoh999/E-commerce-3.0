package com.semicolon.Ecommmerce.repository;


import com.semicolon.Ecommmerce.models.CardType;
import com.semicolon.Ecommmerce.models.CreditCardInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardInfoRepository extends JpaRepository<CreditCardInfo,Long> {
     CreditCardInfo findCreditCardInfoByCard(CardType cardType);
     CreditCardInfo findCreditCardInfoByCardCvv(String cvv);
     CreditCardInfo findCreditCardInfoByNameOnCard(String nameOnCard);
}

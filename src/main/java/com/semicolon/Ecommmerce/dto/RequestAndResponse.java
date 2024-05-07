package com.semicolon.Ecommmerce.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.semicolon.Ecommmerce.models.OurUsers;
import com.semicolon.Ecommmerce.models.Product;
import com.semicolon.Ecommmerce.models.ProductCategory;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestAndResponse {
    private int statusCode;
    private String error;
    private String message;
    private String token;
    private String refreshToken;
    private String expirationTime;
    private String email;
    private String role;
    private String password;
    private List<Product> products;
    private String storeUser;
    private String productName;
    private Double productPrice;
    private String productDescription;
    private ProductCategory productCategory;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int noOfProduct;
    private UUID productId;
    private Long itemId;
}

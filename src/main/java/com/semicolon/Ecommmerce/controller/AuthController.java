package com.semicolon.Ecommmerce.controller;

import com.semicolon.Ecommmerce.exceptions.E_commerceExceptions;
import com.semicolon.Ecommmerce.models.Item;
import com.semicolon.Ecommmerce.models.ShoppingCart;
import com.semicolon.Ecommmerce.service.AuthService;
import com.semicolon.Ecommmerce.dto.RequestAndResponse;
import com.semicolon.Ecommmerce.service.ItemServices;
import com.semicolon.Ecommmerce.service.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;
    @Autowired
    private ProductServices productServices;
    @Autowired
    private ItemServices itemServices;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody RequestAndResponse signUpRequest){
       try {
           return new ResponseEntity<>(authService.signUp(signUpRequest), CREATED);
       }catch (E_commerceExceptions exceptions){
            return new ResponseEntity<>(exceptions.getMessage(), BAD_REQUEST);
       }
       }

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody RequestAndResponse signInRequest){
        try {
            return new ResponseEntity<>(authService.signIn(signInRequest), OK);
        }catch (E_commerceExceptions exceptions){
           return new ResponseEntity<>(exceptions.getMessage(),BAD_REQUEST);
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<RequestAndResponse> refreshToken(@RequestBody RequestAndResponse refreshTokenRequest){
        return ResponseEntity.ok(authService.refreshToken(refreshTokenRequest));
    }

    @GetMapping("/view-all-product")
    public ResponseEntity<?> getAllProduct(){
        return ResponseEntity.ok(productServices.findAllProducts());
    }


    @GetMapping("/view-all-items")
    public List<Item> getAllItems(){
        return itemServices.findAllItems();
    }

    @GetMapping("/view/{owner}/cart")
    public ResponseEntity<?> viewCart(@PathVariable("owner") String owner){
        try{
            return new ResponseEntity<>(authService.findCartByUserName(owner), FOUND);
        }catch (E_commerceExceptions exceptions){
            return new ResponseEntity<>(exceptions.getMessage(),FORBIDDEN);
        }
    }
}

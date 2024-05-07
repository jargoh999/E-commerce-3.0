package com.semicolon.Ecommmerce.controller;

import com.semicolon.Ecommmerce.dto.RequestAndResponse;
import com.semicolon.Ecommmerce.exceptions.E_commerceExceptions;
import com.semicolon.Ecommmerce.models.Product;
import com.semicolon.Ecommmerce.models.ShoppingCart;
import com.semicolon.Ecommmerce.service.AuthService;
import com.semicolon.Ecommmerce.service.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.*;

@RestController
public class AdminUsers {

    @Autowired
    private ProductServices productRepo;
    @Autowired
    private AuthService authService;

    @GetMapping("public/product")
    public ResponseEntity<Object> getAllProducts(){
        return ResponseEntity.ok(productRepo.findAllProducts());
    }

    @PostMapping("public/save-product")
    public ResponseEntity<?> signUp(@RequestBody RequestAndResponse productRequest){
        try{
              return new ResponseEntity<>(authService.addProduct(productRequest),OK);
        }catch (E_commerceExceptions exceptions){
              return new ResponseEntity<>(exceptions.getMessage(),BAD_REQUEST);
        }

    }


    @PostMapping("public/add-item-to-cart")
    public ResponseEntity<?> addItemToCart(@RequestBody RequestAndResponse requestAndResponse){
        try {
            return new ResponseEntity<>(authService.addProductToCart(requestAndResponse), OK);
        }catch (E_commerceExceptions exceptions){
            return new ResponseEntity<>(exceptions.getMessage(), BAD_REQUEST);
        }
    }


    @PostMapping("public/remove-item-from-cart")
    public ResponseEntity<?> removeItemFromCart(@RequestBody RequestAndResponse requestAndResponse){
        try {
            return new ResponseEntity<>(authService.removeItemFromCart(requestAndResponse), OK);
        }catch (E_commerceExceptions exceptions){
            return new ResponseEntity<>(exceptions.getMessage(), BAD_REQUEST);
        }
    }




    @PutMapping("public/assign-cart-to/{username}")
    public ResponseEntity<?> selectItems(@PathVariable("username") String username){
        try {
            return new ResponseEntity<>(authService.assignCartToUser(username), OK);
        }catch (E_commerceExceptions exceptions){
            return new ResponseEntity<>(exceptions.getMessage(), BAD_REQUEST);
        }
    }

    @GetMapping("public/find-all-carts")
    public List<ShoppingCart> findAllCarts(){
        return authService.findAllCarts();
    }

    @PostMapping("public/add-product-as-item")
    public ResponseEntity<?> addProduct(@RequestBody RequestAndResponse requestAndResponse){
        try {
            return new ResponseEntity<>(authService.addProductAsItem(requestAndResponse), OK);
        }catch (E_commerceExceptions exceptions){
            return new ResponseEntity<>(exceptions.getMessage(), BAD_REQUEST);
        }
    }


    @GetMapping("public/find-products/{id}")
    public Product findProducts(@PathVariable("id")UUID id){
        return productRepo.findProductsById(id);
    }


    @PostMapping("public/remove-product")
    public ResponseEntity<?> removeProduct(@RequestBody RequestAndResponse requestAndResponse){
        try {
            return new ResponseEntity<>(authService.removeProductFromCart(requestAndResponse), OK);
        }catch (E_commerceExceptions exceptions){
            return new ResponseEntity<>(exceptions.getMessage(), BAD_REQUEST);
        }
    }
    @GetMapping("public/view/{owner}/cart")
    public ResponseEntity<?> viewCart(@PathVariable("owner") String owner){
        try{
            return new ResponseEntity<>(authService.findCartByUserName(owner), FOUND);
        }catch (E_commerceExceptions exceptions){
            return new ResponseEntity<>(exceptions.getMessage(),FORBIDDEN);
        }
    }
}

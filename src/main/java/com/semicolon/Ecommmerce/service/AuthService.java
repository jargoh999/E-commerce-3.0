package com.semicolon.Ecommmerce.service;

import com.semicolon.Ecommmerce.exceptions.UserAlreadyExistException;
import com.semicolon.Ecommmerce.models.Item;
import com.semicolon.Ecommmerce.models.Product;
import com.semicolon.Ecommmerce.models.ShoppingCart;
import com.semicolon.Ecommmerce.repository.OurUserRepo;
import com.semicolon.Ecommmerce.dto.RequestAndResponse;
import com.semicolon.Ecommmerce.models.OurUsers;
import com.semicolon.Ecommmerce.utils.Mappers;
import org.hibernate.service.UnknownUnwrapTypeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private OurUserRepo ourUserRepo;
    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private ProductServices productServices;
    @Autowired
    private CartServices cartServices;
    @Autowired
    private ItemServices itemServices;
    public RequestAndResponse signUp(RequestAndResponse registrationRequest){
        RequestAndResponse resp = new RequestAndResponse();
        if(ourUserRepo.findByEmail(registrationRequest.getEmail()).isPresent())throw new UserAlreadyExistException("user already exists");
        OurUsers ourUsers = new OurUsers();
        Mappers.mapRequestToUser(ourUsers, registrationRequest);
        ourUsers.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        OurUsers ourUserResult = ourUserRepo.save(ourUsers);
        Mappers.mapUserToResponse(resp, ourUserResult);
        return resp;
    }

    public RequestAndResponse signIn(RequestAndResponse signinRequest){
        RequestAndResponse response = new RequestAndResponse();
        var user = ourUserRepo.findByEmail(signinRequest.getEmail());
        if(user.isEmpty())throw new UserAlreadyExistException("user not found");
        if(!passwordEncoder.matches(signinRequest.getPassword(),user.get().getPassword()))throw new UserAlreadyExistException("something went wrong");
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getEmail(),signinRequest.getPassword()));
            var jwt = jwtUtils.generateToken(user.get());
            var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user.get());
            Mappers.mapUserToLogInResponse(response, jwt, refreshToken);
            return response;
    }

    public RequestAndResponse refreshToken(RequestAndResponse refreshTokenRequest){
        RequestAndResponse response = new RequestAndResponse();
        String ourEmail = jwtUtils.extractUsername(refreshTokenRequest.getToken());
        OurUsers users = ourUserRepo.findByEmail(ourEmail).orElseThrow();
        if (jwtUtils.isTokenValid(refreshTokenRequest.getToken(), users)) {
            var jwt = jwtUtils.generateToken(users);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRefreshToken(refreshTokenRequest.getToken());
            response.setExpirationTime("24Hr");
            response.setMessage("Successfully Refreshed Token");

        }
        response.setStatusCode(500);
        return response;
    }

    public RequestAndResponse addProduct(RequestAndResponse productRequest){
        Product product = new Product();
        product.setProductName(productRequest.getProductName());
        product.setProductCategory(productRequest.getProductCategory());
        product.setProductPrice(BigDecimal.valueOf(productRequest.getProductPrice()));
        product.setProductDescription(productRequest.getProductDescription());
        var productResponse =productServices.addProduct(product);
        productResponse.setStatusCode(200);
        return productResponse;
    }


    public ShoppingCart addProductToCart(RequestAndResponse requestAndResponse){
        if(cartServices.findShoppingCartByOwner(requestAndResponse.getStoreUser())==null)
            throw new UserAlreadyExistException("you are not a user");
        return cartServices.addItemToCart(requestAndResponse);
    }


    public ShoppingCart removeItemFromCart(RequestAndResponse requestAndResponse){
        if(cartServices.findShoppingCartByOwner(requestAndResponse.getStoreUser())==null)
            throw new UserAlreadyExistException("you are not a user");
        return cartServices.removeItemFromCart(requestAndResponse);
    }

    public ShoppingCart removeProductFromCart(RequestAndResponse requestAndResponse){
        if(cartServices.findShoppingCartByOwner(requestAndResponse.getStoreUser())==null)
            throw new UserAlreadyExistException("you are not a user");
        return cartServices.RemoveProductFromCart(requestAndResponse);
    }

    public ShoppingCart assignCartToUser(String username){
         return cartServices.assignCart(username);
    }

    public List<ShoppingCart> findAllCarts() {
        return cartServices.findAllShoppingCart();
    }

    public Item addProductAsItem(RequestAndResponse requestAndResponse){
        return itemServices.addProductAsItem(requestAndResponse);
    }

    public ShoppingCart findCartByUserName(String userName){
        return cartServices.findShoppingCartByOwner(userName);
    }



    public Product findProductById(UUID id){
        return productServices.findProductsById(id);
    }
}

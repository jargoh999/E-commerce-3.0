package com.semicolon.Ecommmerce.utils;

import com.semicolon.Ecommmerce.dto.RequestAndResponse;
import com.semicolon.Ecommmerce.models.OurUsers;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


public class Mappers {


    public static void mapRequestToUser(OurUsers ourUsers, RequestAndResponse registerRequest){
                          ourUsers.setRole(registerRequest.getRole());
                          ourUsers.setPassword(registerRequest.getPassword());
                          ourUsers.setEmail(registerRequest.getEmail());
    }
    public static void mapUserToResponse( RequestAndResponse response,OurUsers ourUserResult) {
        if (ourUserResult != null && ourUserResult.getId()>0) {
            response.setStoreUser(ourUserResult.getUsername());
            response.setMessage("User Saved Successfully");
            response.setStatusCode(201);
        }

    }


    public static void mapUserToLogInResponse( RequestAndResponse response,String jwt,String refreshToken) {
        response.setStatusCode(201);
        response.setToken(jwt);
        response.setRefreshToken(refreshToken);
        response.setExpirationTime("24Hr");
        response.setMessage("Successfully generated Token");
    }

    }

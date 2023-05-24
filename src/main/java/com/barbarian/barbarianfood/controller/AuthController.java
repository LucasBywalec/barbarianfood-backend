package com.barbarian.barbarianfood.controller;

import com.barbarian.barbarianfood.service.AuthService;
import com.zaiapi.openapi.api.AuthApi;
import com.zaiapi.openapi.model.SignInRequest;
import com.zaiapi.openapi.model.SignUpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController implements AuthApi {
    @Autowired
    AuthService authService;


    @Override
    public ResponseEntity<Object> signIn(SignInRequest signInRequest) {
        return authService.authenticateUser(signInRequest);
    }

    @Override
    public ResponseEntity<Object> signUp(SignUpRequest signUpRequest) {
        return authService.createCustomer(signUpRequest);
    }
}

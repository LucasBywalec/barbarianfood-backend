package com.barbarian.barbarianfood.controller;

import com.barbarian.barbarianfood.service.AuthService;
import com.zaiapi.openapi.api.AuthApi;
import com.zaiapi.openapi.model.DefaultResponse;
import com.zaiapi.openapi.model.SignInRequest;
import com.zaiapi.openapi.model.SignInResponse;
import com.zaiapi.openapi.model.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController implements AuthApi {
    private final AuthService authService;

    @Override
    public ResponseEntity<SignInResponse> signIn(final SignInRequest signInRequest) {
        return authService.authenticateUser(signInRequest);
    }

    @Override
    public ResponseEntity<DefaultResponse> signUp(final SignUpRequest signUpRequest) {
        return authService.createCustomer(signUpRequest);
    }
}

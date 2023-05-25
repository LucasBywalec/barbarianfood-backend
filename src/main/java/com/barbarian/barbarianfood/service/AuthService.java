package com.barbarian.barbarianfood.service;

import com.barbarian.barbarianfood.authentication.JwtAuth;
import com.barbarian.barbarianfood.entity.CustomerBase;
import com.barbarian.barbarianfood.repository.CustomerRepository;
import com.barbarian.barbarianfood.service.converters.AuthServiceConverter;
import com.barbarian.barbarianfood.service.validator.AuthServiceValidator;
import com.zaiapi.openapi.model.DefaultResponse;
import com.zaiapi.openapi.model.SignInRequest;
import com.zaiapi.openapi.model.SignInResponse;
import com.zaiapi.openapi.model.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final CustomerRepository customerRepository;
    private final JwtAuth jwtAuth;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<DefaultResponse> createCustomer(final SignUpRequest request) {
        if(!AuthServiceValidator.isSignUpRequestValid(request)){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
        }

        if(customerRepository.findByEmail(request.getEmail()).isPresent()){
            return ResponseEntity.status(HttpStatus.valueOf(422)).body(null);
        }

        customerRepository.save(AuthServiceConverter.SignUpRequestIntoCustomerBase(request, passwordEncoder));

        return ResponseEntity.ok(null);
    }

    public ResponseEntity<SignInResponse> authenticateUser(final SignInRequest signInRequest) {
        Optional<CustomerBase> user = customerRepository.findByEmail(signInRequest.getEmail());
        if(user.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        if(!AuthServiceValidator.isPasswordMatching(
                signInRequest.getPassword(),
                user.get().getPassword(),
                passwordEncoder)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        var response = new SignInResponse();
        response.setToken(jwtAuth.generateToken(user.get().getId()));

        return ResponseEntity.ok(response);
    }
}

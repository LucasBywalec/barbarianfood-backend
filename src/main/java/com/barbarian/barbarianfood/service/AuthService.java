package com.barbarian.barbarianfood.service;

import com.barbarian.barbarianfood.repository.CustomerRepository;
import com.barbarian.barbarianfood.service.converters.AuthServiceConverter;
import com.barbarian.barbarianfood.service.validator.AuthServiceValidator;
import com.zaiapi.openapi.model.SignInRequest;
import com.zaiapi.openapi.model.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    @Autowired
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<Object> createCustomer(final SignUpRequest request) {
        if(!AuthServiceValidator.isSignUpRequestValid(request)){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Invalid data provided");
        }

        if(customerRepository.findByEmail(request.getEmail()).isPresent()){
            return ResponseEntity.status(HttpStatus.valueOf(422)).body("Account with such email already exists");
        }

        customerRepository.save(AuthServiceConverter.SignUpRequestIntoCustomerBase(request, passwordEncoder));

        return ResponseEntity.ok("Success");
    }

    public ResponseEntity<Object> authenticateUser(final SignInRequest signInRequest) {
        var user = customerRepository.findByEmail(signInRequest.getEmail());
        if(user.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Given credentials do not match any account");
        }

        if(!AuthServiceValidator.isPasswordMatching(
                signInRequest.getPassword(),
                user.get().getPassword(),
                passwordEncoder)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Given credentials do not match any account");
        }

        return ResponseEntity.ok("Success");
    }
}

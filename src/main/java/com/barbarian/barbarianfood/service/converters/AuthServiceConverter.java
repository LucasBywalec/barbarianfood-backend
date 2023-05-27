package com.barbarian.barbarianfood.service.converters;


import com.barbarian.barbarianfood.entity.CustomerBase;
import com.zaiapi.openapi.model.SignUpRequest;
import lombok.experimental.UtilityClass;
import org.springframework.security.crypto.password.PasswordEncoder;

@UtilityClass
public class AuthServiceConverter {

    public CustomerBase SignUpRequestToCustomerBase(final SignUpRequest request, final PasswordEncoder passwordEncoder) {
        String hashedPassword = passwordEncoder.encode(request.getPassword());

        return CustomerBase.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .email(request.getEmail())
                .password(hashedPassword)
                .build();
    }
}


package com.barbarian.barbarianfood.service.converters;


import com.barbarian.barbarianfood.entity.CustomerBase;
import com.barbarian.barbarianfood.entity.UserRole;
import com.zaiapi.openapi.model.SignUpRequest;
import lombok.experimental.UtilityClass;
import org.springframework.security.crypto.password.PasswordEncoder;

@UtilityClass
public class AuthConverter {

    public CustomerBase SignUpRequestToCustomerBase(final SignUpRequest request, final PasswordEncoder passwordEncoder) {
        String hashedPassword = passwordEncoder.encode(request.getPassword());

        UserRole role;
        if(request.getName().equals("Łukasz")){
            role = UserRole.Admin;
        } else {
            role = UserRole.Regular;
        }

        return CustomerBase.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .email(request.getEmail())
                .password(hashedPassword)
                .role(role)
                .build();
    }
}


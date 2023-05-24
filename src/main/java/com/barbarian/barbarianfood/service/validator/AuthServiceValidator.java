package com.barbarian.barbarianfood.service.validator;

import com.zaiapi.openapi.model.SignUpRequest;
import lombok.experimental.UtilityClass;
import org.springframework.security.crypto.password.PasswordEncoder;

@UtilityClass
public class AuthServiceValidator {
    public boolean validateSignUpRequest(final SignUpRequest request){
        String email = request.getEmail();
        if(email == null || email.isBlank() || !isEmailValid(email)){
            return false;
        }
        String name = request.getName();
        if(name == null || name.isBlank()){
            return false;
        }
        String surname = request.getSurname();
        if(surname == null || surname.isBlank()){
            return false;
        }
        String password = request.getPassword();
        if(password == null || password.isBlank() || !isPasswordValid(password)){
            return false;
        }
        return true;
    }

    public boolean isPasswordMatching(
            final String givenPassword, final String originalPassword, final PasswordEncoder passwordEncoder){

        return passwordEncoder.matches(givenPassword, originalPassword);
    }

    private boolean isPasswordValid(final String password) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        return password.matches(regex);
    }

    private boolean isEmailValid(final String email) {
        String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        return email.matches(regex);
    }


}

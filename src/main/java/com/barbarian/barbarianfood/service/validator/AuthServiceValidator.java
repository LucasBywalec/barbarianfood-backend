package com.barbarian.barbarianfood.service.validator;

import com.zaiapi.openapi.model.SignUpRequest;
import lombok.experimental.UtilityClass;
import org.springframework.security.crypto.password.PasswordEncoder;

@UtilityClass
public class AuthServiceValidator {
    public boolean validateSignUpRequest(final SignUpRequest request){
        String email = request.getEmail();
        if(email == null || email.isEmpty() || isEmailValid(email)){
            return false;
        }
        String name = request.getName();
        if(name == null || name.isEmpty()){
            return false;
        }
        String surname = request.getSurname();
        if(surname == null || surname.isEmpty()){
            return false;
        }
        String password = request.getPassword();
        if(password == null || password.isEmpty() || isPasswordValid(password)){
            return false;
        }
        return true;
    }

    public boolean isPasswordMatching(
            final String givenPassword, final String originalPassword, final PasswordEncoder passwordEncoder){
        String hashedPassword = passwordEncoder.encode(givenPassword);

        System.out.println(hashedPassword + " " + originalPassword);

        return hashedPassword.equals(originalPassword);
    }

    private boolean isPasswordValid(final String password) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$";
        return password.matches(regex);
    }

    private boolean isEmailValid(final String email) {
        String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        return email.matches(regex);
    }


}

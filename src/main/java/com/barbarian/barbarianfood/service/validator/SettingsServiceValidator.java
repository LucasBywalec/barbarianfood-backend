package com.barbarian.barbarianfood.service.validator;

import com.zaiapi.openapi.model.AddressSettingsRequest;
import com.zaiapi.openapi.model.PaymentSettingsRequest;
import com.zaiapi.openapi.model.ProfileSettingsRequest;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;

@UtilityClass
public class SettingsServiceValidator {
    public static boolean isAddressValid(AddressSettingsRequest address) {
        if(address.getStreet().isBlank()){
            return false;
        }
        if(address.getBuildingNumber() < 0){
            return false;
        }
        if(address.getPostalCode().isBlank()){
            return false;
        }
        if(address.getVoivodeship().isBlank()){
            return false;
        }
        if(address.getCity().isBlank()){
            return false;
        }
        if(address.getPhoneNumber().isBlank() || address.getPhoneNumber().length() < 9){
            return false;
        }

        return true;
    }

    public static boolean isPaymentValid(PaymentSettingsRequest request) {
        if(request.getCreditCardNumber().length() != 12){
            return false;
        }
        if(request.getCreditCardExpDate().isBefore(LocalDate.now())){
            return false;
        }
        if(request.getCreditCardSecret().length() != 3){
            return false;
        }
        if(!request.getCreditCardSecret().matches("\\p{L}+\\s+\\p{L}+")){
            return false;
        }
        return true;
    }

    public static boolean isProfileSettingsRequestValid(ProfileSettingsRequest request) {
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

    private boolean isPasswordValid(final String password) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        return password.matches(regex);
    }

    private boolean isEmailValid(final String email) {
        String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        return email.matches(regex);
    }
}

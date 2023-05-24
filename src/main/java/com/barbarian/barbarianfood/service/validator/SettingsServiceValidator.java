package com.barbarian.barbarianfood.service.validator;

import com.zaiapi.openapi.model.AddressSettingsRequest;
import com.zaiapi.openapi.model.PaymentSettingsRequest;
import lombok.experimental.UtilityClass;

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
        return true; //TODO
    }
}

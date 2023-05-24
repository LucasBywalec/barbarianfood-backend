package com.barbarian.barbarianfood.service.converters;

import com.barbarian.barbarianfood.entity.CustomerAddress;
import com.barbarian.barbarianfood.entity.CustomerPayment;
import com.zaiapi.openapi.model.AddressSettingsRequest;
import com.zaiapi.openapi.model.PaymentSettingsRequest;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SettingsServiceConverter {

    public static CustomerAddress addressSettingsRequestToCustomerAddress(AddressSettingsRequest address) {
        return CustomerAddress.builder()
                .city(address.getCity())
                .buildingNumber(address.getBuildingNumber())
                .phoneNumber(address.getPhoneNumber())
                .street(address.getStreet())
                .voivodeship(address.getVoivodeship())
                .postalCode(address.getPostalCode())
                .build();
    }

    public static CustomerPayment paymentSettingsRequestToCustomerPayment(PaymentSettingsRequest request) {
        return CustomerPayment.builder()
                .creditCardNumber(request.getCreditCardNumber())
                .creditCardExpDate(request.getCreditCardExpDate())
                .creditCardOwner(request.getCreditCardOwner())
                .creditCardSecret(request.getCreditCardSecret())
                .build();
    }
}

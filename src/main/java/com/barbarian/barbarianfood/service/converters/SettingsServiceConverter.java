package com.barbarian.barbarianfood.service.converters;

import com.barbarian.barbarianfood.entity.CustomerAddress;
import com.barbarian.barbarianfood.entity.CustomerBase;
import com.barbarian.barbarianfood.entity.CustomerPayment;
import com.zaiapi.openapi.model.AddressSettingsRequest;
import com.zaiapi.openapi.model.PaymentSettingsRequest;
import com.zaiapi.openapi.model.ProfileSettingsRequest;
import lombok.experimental.UtilityClass;
import org.springframework.security.crypto.password.PasswordEncoder;

@UtilityClass
public class SettingsServiceConverter {

    public static CustomerAddress addressSettingsRequestToCustomerAddress(
            AddressSettingsRequest address, CustomerBase customerBase) {
        return CustomerAddress.builder()
                .city(address.getCity())
                .buildingNumber(address.getBuildingNumber())
                .phoneNumber(address.getPhoneNumber())
                .street(address.getStreet())
                .voivodeship(address.getVoivodeship())
                .postalCode(address.getPostalCode())
                .customerBase(customerBase)
                .build();
    }

    public static CustomerPayment paymentSettingsRequestToCustomerPayment(
            PaymentSettingsRequest request, CustomerBase customerBase) {
        return CustomerPayment.builder()
                .creditCardNumber(request.getCreditCardNumber())
                .creditCardExpDate(request.getCreditCardExpDate())
                .creditCardOwner(request.getCreditCardOwner())
                .creditCardSecret(request.getCreditCardSecret())
                .customerBase(customerBase)
                .build();
    }

    public static CustomerBase profileSettingsRequestToCustomerBase(
            ProfileSettingsRequest request, CustomerBase customerBase, PasswordEncoder passwordEncoder) {
        return CustomerBase.builder()
                .address(customerBase.getAddress())
                .payments(customerBase.getPayments())
                .id(customerBase.getId())
                .name(request.getName())
                .surname(request.getSurname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
    }
}

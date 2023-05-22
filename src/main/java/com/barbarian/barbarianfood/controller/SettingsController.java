package com.barbarian.barbarianfood.controller;

import com.zaiapi.openapi.api.SettingsApi;
import com.zaiapi.openapi.model.AddressSettingsRequest;
import com.zaiapi.openapi.model.PaymentSettingsRequest;
import com.zaiapi.openapi.model.ProfileSettingsRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SettingsController implements SettingsApi {
    @Override
    public ResponseEntity<Object> editAddressInformation(AddressSettingsRequest addressSettingsRequest) {
        return null;
    }

    @Override
    public ResponseEntity<Object> editPaymentInformation(PaymentSettingsRequest paymentSettingsRequest) {
        return null;
    }

    @Override
    public ResponseEntity<Object> editProfileInformation(ProfileSettingsRequest profileSettingsRequest) {
        return null;
    }
}

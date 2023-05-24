package com.barbarian.barbarianfood.controller;

import com.barbarian.barbarianfood.service.SettingsService;
import com.zaiapi.openapi.api.SettingsApi;
import com.zaiapi.openapi.model.AddressSettingsRequest;
import com.zaiapi.openapi.model.PaymentSettingsRequest;
import com.zaiapi.openapi.model.ProfileSettingsRequest;
import com.zaiapi.openapi.model.SignUpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SettingsController implements SettingsApi {
    @Autowired
    SettingsService settingsService;

    @Override
    public ResponseEntity<Object> editAddressInformation(AddressSettingsRequest addressSettingsRequest) {
        return settingsService.editAddressInformation(addressSettingsRequest);
    }

    @Override
    public ResponseEntity<Object> editPaymentInformation(PaymentSettingsRequest paymentSettingsRequest) {
        return settingsService.editPaymentInformation(paymentSettingsRequest);
    }

    @Override
    public ResponseEntity<Object> editProfileInformation(ProfileSettingsRequest profileSettingsRequest) {
        return settingsService.editProfileInformation(profileSettingsRequest);
    }
}

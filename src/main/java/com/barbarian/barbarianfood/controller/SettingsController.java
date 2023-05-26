package com.barbarian.barbarianfood.controller;

import com.barbarian.barbarianfood.service.SettingsService;
import com.zaiapi.openapi.api.SettingsApi;
import com.zaiapi.openapi.model.AddressSettingsRequest;
import com.zaiapi.openapi.model.DefaultResponse;
import com.zaiapi.openapi.model.PaymentSettingsRequest;
import com.zaiapi.openapi.model.ProfileSettingsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SettingsController implements SettingsApi {
    private final SettingsService settingsService;

    @Override
    public ResponseEntity<DefaultResponse> editAddressInformation(final AddressSettingsRequest addressSettingsRequest) {
        return settingsService.editAddressInformation(addressSettingsRequest);
    }

    @Override
    public ResponseEntity<DefaultResponse> editPaymentInformation(final PaymentSettingsRequest paymentSettingsRequest) {
        return settingsService.editPaymentInformation(paymentSettingsRequest);
    }

    @Override
    public ResponseEntity<DefaultResponse> editProfileInformation(final ProfileSettingsRequest profileSettingsRequest) {
        return settingsService.editProfileInformation(profileSettingsRequest);
    }
}

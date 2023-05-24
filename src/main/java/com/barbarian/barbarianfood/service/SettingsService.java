package com.barbarian.barbarianfood.service;

import com.barbarian.barbarianfood.repository.AddressRepository;
import com.barbarian.barbarianfood.repository.PaymentRepository;
import com.barbarian.barbarianfood.service.converters.SettingsServiceConverter;
import com.barbarian.barbarianfood.service.validator.SettingsServiceValidator;
import com.zaiapi.openapi.model.AddressSettingsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SettingsService {
    @Autowired
    private final AddressRepository addressRepository;
    @Autowired
    private final PaymentRepository paymentRepository;

    public ResponseEntity<Object> editAddressInformation(AddressSettingsRequest address){
        if(!SettingsServiceValidator.isAddressValid(address)){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Invalid data provided");
        }

        addressRepository.save(SettingsServiceConverter.addressSettingsRequestToCustomerAddress(address));

        return ResponseEntity.ok("Success");
    }
}

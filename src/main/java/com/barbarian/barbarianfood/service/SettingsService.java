package com.barbarian.barbarianfood.service;

import com.barbarian.barbarianfood.repository.AddressRepository;
import com.barbarian.barbarianfood.repository.CustomerRepository;
import com.barbarian.barbarianfood.repository.PaymentRepository;
import com.barbarian.barbarianfood.service.converters.SettingsServiceConverter;
import com.barbarian.barbarianfood.service.validator.AuthServiceValidator;
import com.barbarian.barbarianfood.service.validator.SettingsServiceValidator;
import com.zaiapi.openapi.model.AddressSettingsRequest;
import com.zaiapi.openapi.model.PaymentSettingsRequest;
import com.zaiapi.openapi.model.ProfileSettingsRequest;
import com.zaiapi.openapi.model.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
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
    @Autowired
    private final CustomerRepository customerRepository;

    public ResponseEntity<Object> editAddressInformation(AddressSettingsRequest request){
        if(!SettingsServiceValidator.isAddressValid(request)){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Invalid data provided");
        }

        addressRepository.save(SettingsServiceConverter.addressSettingsRequestToCustomerAddress(request));

        return ResponseEntity.ok("Success");
    }

    public ResponseEntity<Object> editPaymentInformation(PaymentSettingsRequest request){
        if(!SettingsServiceValidator.isPaymentValid(request)){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Invalid data provided");
        }

        paymentRepository.save(SettingsServiceConverter.paymentSettingsRequestToCustomerPayment(request));

        return ResponseEntity.ok("Success");
    }

    public ResponseEntity<Object> editProfileInformation(SignUpRequest request){
        if(!AuthServiceValidator.isSignUpRequestValid(request)){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Invalid data provided");
        }

        //update user based on id

        return ResponseEntity.ok("Success");
    }
}

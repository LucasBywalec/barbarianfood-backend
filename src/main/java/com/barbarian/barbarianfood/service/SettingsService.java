package com.barbarian.barbarianfood.service;

import com.barbarian.barbarianfood.authentication.JwtAuth;
import com.barbarian.barbarianfood.repository.AddressRepository;
import com.barbarian.barbarianfood.repository.CustomerRepository;
import com.barbarian.barbarianfood.repository.PaymentRepository;
import com.barbarian.barbarianfood.service.converters.SettingsServiceConverter;
import com.barbarian.barbarianfood.service.validator.SettingsServiceValidator;
import com.zaiapi.openapi.model.AddressSettingsRequest;
import com.zaiapi.openapi.model.PaymentSettingsRequest;
import com.zaiapi.openapi.model.ProfileSettingsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class SettingsService {
    @Autowired
    private final AddressRepository addressRepository;
    @Autowired
    private final PaymentRepository paymentRepository;
    @Autowired
    private final CustomerRepository customerRepository;
    @Autowired
    private final JwtAuth jwtAuth;
    private final PasswordEncoder passwordEncoder;

    //TODO take the token from header, not from request - change API first

    public ResponseEntity<Object> editAddressInformation(AddressSettingsRequest request){
        if(!SettingsServiceValidator.isAddressValid(request)){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Invalid data provided");
        }

        if(!jwtAuth.getExpirationDateFromToken(request.getToken()).after(new Date())){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Token has expired");
        }

        var customerBase = customerRepository.findById(jwtAuth.getIdFromToken(request.getToken()));
        if(customerBase.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User is non existent");
        }

        addressRepository.save(
                SettingsServiceConverter.addressSettingsRequestToCustomerAddress(
                        request, customerBase.get()));

        return ResponseEntity.ok("Success");
    }

    public ResponseEntity<Object> editPaymentInformation(PaymentSettingsRequest request){
        if(!SettingsServiceValidator.isPaymentValid(request)){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Invalid data provided");
        }

        if(!jwtAuth.getExpirationDateFromToken(request.getToken()).after(new Date())){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Token has expired");
        }

        var customerBase = customerRepository.findById(jwtAuth.getIdFromToken(request.getToken()));
        if(customerBase.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User is non existent");
        }

        paymentRepository.save(
                SettingsServiceConverter.paymentSettingsRequestToCustomerPayment(
                        request, customerBase.get()));

        return ResponseEntity.ok("Success");
    }

    public ResponseEntity<Object> editProfileInformation(ProfileSettingsRequest request){
        if(!SettingsServiceValidator.isProfileSettingsRequestValid(request)){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Invalid data provided");
        }

        if(!jwtAuth.getExpirationDateFromToken(request.getToken()).after(new Date())){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Token has expired");
        }

        var customerBase = customerRepository.findById(jwtAuth.getIdFromToken(request.getToken()));
        if(customerBase.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User is non existent");
        }

        customerRepository.save(
                SettingsServiceConverter.profileSettingsRequestToCustomerBase(
                        request, customerBase.get(), passwordEncoder));

        return ResponseEntity.ok("Success");
    }
}

package com.barbarian.barbarianfood.service;

import com.barbarian.barbarianfood.authentication.JwtAuth;
import com.barbarian.barbarianfood.repository.AddressRepository;
import com.barbarian.barbarianfood.repository.CustomerRepository;
import com.barbarian.barbarianfood.repository.PaymentRepository;
import com.barbarian.barbarianfood.service.converters.SettingsConverter;
import com.barbarian.barbarianfood.service.validator.SettingsServiceValidator;
import com.zaiapi.openapi.model.AddressSettingsRequest;
import com.zaiapi.openapi.model.DefaultResponse;
import com.zaiapi.openapi.model.PaymentSettingsRequest;
import com.zaiapi.openapi.model.ProfileSettingsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class SettingsService {
    private final AddressRepository addressRepository;
    private final PaymentRepository paymentRepository;
    private final CustomerRepository customerRepository;
    private final JwtAuth jwtAuth;
    private final PasswordEncoder passwordEncoder;

    //TODO take the token from header, not from request - change API first

    public ResponseEntity<DefaultResponse> editAddressInformation(final AddressSettingsRequest request){
        if(!SettingsServiceValidator.isAddressValid(request)){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
        }

        if(jwtAuth.isTokenExpired(request.getToken())){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }

        var customerBase = customerRepository.findById(jwtAuth.getIdFromToken(request.getToken()));
        if(customerBase.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        var possibleAddress = addressRepository.findByCustomerBase(customerBase.get());
        if(possibleAddress.isEmpty()) {
            addressRepository.save(
                    SettingsConverter.addressSettingsRequestToCustomerAddress(
                            request, customerBase.get()));
        }
        else {
            var address = possibleAddress.get();
            address.setBuildingNumber(request.getBuildingNumber());
            address.setCity(address.getCity());
            address.setPhoneNumber(address.getPhoneNumber());
            address.setStreet(address.getStreet());
            address.setVoivodeship(address.getVoivodeship());
            address.setPostalCode(address.getPostalCode());
            addressRepository.save(address);
        }

        return ResponseEntity.ok(null);
    }

    public ResponseEntity<DefaultResponse> editPaymentInformation(final PaymentSettingsRequest request){
        if(!SettingsServiceValidator.isPaymentValid(request)){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
        }

        if(jwtAuth.isTokenExpired(request.getToken())){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }

        var customerBase = customerRepository.findById(jwtAuth.getIdFromToken(request.getToken()));
        if(customerBase.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        var possiblePayment = paymentRepository.findByCustomerBase(customerBase.get());
        if(possiblePayment.isEmpty()) {
            paymentRepository.save(
                    SettingsConverter.paymentSettingsRequestToCustomerPayment(
                            request, customerBase.get()));
        }
        else {
            var payment = possiblePayment.get();
            payment.setCreditCardNumber(request.getCreditCardNumber().toString());
            payment.setCreditCardExpDate(LocalDate.parse(request.getCreditCardExpDate()));
            payment.setCreditCardOwner(request.getCreditCardOwner());
            payment.setCreditCardSecret(request.getCreditCardSecret());
            paymentRepository.save(payment);
        }

        return ResponseEntity.ok(null);
    }

    public ResponseEntity<DefaultResponse> editProfileInformation(final ProfileSettingsRequest request){
        if(!SettingsServiceValidator.isProfileSettingsRequestValid(request)){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
        }

        if(jwtAuth.isTokenExpired(request.getToken())){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }

        var customerBase = customerRepository.findById(jwtAuth.getIdFromToken(request.getToken()));
        if(customerBase.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        customerRepository.save(
                SettingsConverter.profileSettingsRequestToCustomerBase(
                        request, customerBase.get(), passwordEncoder));

        return ResponseEntity.ok(null);
    }
}

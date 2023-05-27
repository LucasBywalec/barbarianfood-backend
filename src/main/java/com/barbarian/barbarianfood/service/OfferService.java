package com.barbarian.barbarianfood.service;

import com.barbarian.barbarianfood.authentication.JwtAuth;
import com.barbarian.barbarianfood.entity.CustomerBase;
import com.barbarian.barbarianfood.entity.Offer;
import com.barbarian.barbarianfood.repository.CustomerRepository;
import com.barbarian.barbarianfood.repository.OfferRepository;
import com.barbarian.barbarianfood.service.converters.OfferServiceConverter;
import com.barbarian.barbarianfood.service.validator.OfferServiceValidator;
import com.zaiapi.openapi.model.DefaultResponse;
import com.zaiapi.openapi.model.GetOfferDetailsResponse;
import com.zaiapi.openapi.model.GetOfferResponse;
import com.zaiapi.openapi.model.SubscribeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OfferService {
    private final OfferRepository offerRepository;
    private final CustomerRepository customerRepository;
    private final JwtAuth jwtAuth;
    public ResponseEntity<GetOfferResponse> getOffer() {
        GetOfferResponse response = new GetOfferResponse();
        response.setOfferList(offerRepository.findAll().stream().map(
                OfferServiceConverter::OfferToOfferItem
        ).toList());

        return ResponseEntity.ok(response);
    }

    public ResponseEntity<GetOfferDetailsResponse> getOfferDetails(final String offerId) {
        Optional<Offer> offer = offerRepository.findById(offerId);

        return offer.map(
                value -> ResponseEntity.ok(OfferServiceConverter.OfferToOfferDetailsResponse(value)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    public ResponseEntity<DefaultResponse> subscribeToOffer(final String offerId, final SubscribeRequest request) {
        if(!OfferServiceValidator.validateSubscribeRequest(request)){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
        }

        if(jwtAuth.isTokenExpired(request.getToken())){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }

        Optional<Offer> offer = offerRepository.findById(offerId);
        if(offer.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        Optional<CustomerBase> customer = customerRepository.findById(jwtAuth.getIdFromToken(request.getToken()));
        customer.get().setOffer(offer.get());

        return ResponseEntity.ok(null);
    }
}

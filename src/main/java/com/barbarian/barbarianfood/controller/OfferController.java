package com.barbarian.barbarianfood.controller;

import com.barbarian.barbarianfood.service.OfferService;
import com.zaiapi.openapi.api.OfferApi;
import com.zaiapi.openapi.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OfferController implements OfferApi {
    private final OfferService offerService;

    @Override
    public ResponseEntity<GetOfferResponse> getOffer() {
        return null;
    }

    @Override
    public ResponseEntity<GetOfferDetailsResponse> getOfferDetails(String offerId) {
        return null;
    }

    @Override
    public ResponseEntity<DefaultResponse> subscribeToOffer(String offerId, SubscribeRequest subscribeRequest) {
        return null;
    }
}

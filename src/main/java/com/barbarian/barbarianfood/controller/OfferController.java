package com.barbarian.barbarianfood.controller;

import com.barbarian.barbarianfood.service.OfferService;
import com.zaiapi.openapi.api.OfferApi;
import com.zaiapi.openapi.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OfferController implements OfferApi {
    private final OfferService offerService;

    @Override
    @CrossOrigin(origins = "*")
    public ResponseEntity<GetOfferResponse> getOffer() {
        return offerService.getOffer();
    }

    @Override
    @CrossOrigin(origins = "*")
    public ResponseEntity<GetOfferDetailsResponse> getOfferDetails(final String offerId) {
        return offerService.getOfferDetails(offerId);
    }

    @Override
    @CrossOrigin(origins = "*")
    public ResponseEntity<DefaultResponse> subscribeToOffer(final String offerId, final SubscribeRequest subscribeRequest) {
        return offerService.subscribeToOffer(offerId, subscribeRequest);
    }
}

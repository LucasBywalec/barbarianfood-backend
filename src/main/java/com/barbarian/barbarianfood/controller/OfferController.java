package com.barbarian.barbarianfood.controller;

import com.zaiapi.openapi.api.OfferApi;
import com.zaiapi.openapi.model.GetOfferDetailsResponse;
import com.zaiapi.openapi.model.GetOfferResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OfferController implements OfferApi {
    @Override
    public ResponseEntity<GetOfferResponse> getOffer() {
        return null;
    }

    @Override
    public ResponseEntity<GetOfferDetailsResponse> getOfferDetails(String s) {
        return null;
    }

    @Override
    public ResponseEntity<Object> subscribeToOffer(String s) {
        return null;
    }
}

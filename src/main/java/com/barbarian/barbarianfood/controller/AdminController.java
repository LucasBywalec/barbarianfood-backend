package com.barbarian.barbarianfood.controller;

import com.zaiapi.openapi.api.AdminApi;
import com.zaiapi.openapi.model.AddNewOfferRequest;
import com.zaiapi.openapi.model.ListAllOffersResponse;
import com.zaiapi.openapi.model.ListAllUsersResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController implements AdminApi {
    @Override
    public ResponseEntity<Object> addNewOffer(AddNewOfferRequest addNewOfferRequest) {
        return null;
    }

    @Override
    public ResponseEntity<ListAllOffersResponse> listAllOffers() {
        return null;
    }

    @Override
    public ResponseEntity<ListAllUsersResponse> listAllUsers() {
        return null;
    }
}
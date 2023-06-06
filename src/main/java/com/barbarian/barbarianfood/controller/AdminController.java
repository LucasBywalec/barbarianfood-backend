package com.barbarian.barbarianfood.controller;

import com.barbarian.barbarianfood.service.AdminService;
import com.zaiapi.openapi.api.AdminApi;
import com.zaiapi.openapi.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AdminController implements AdminApi {
    private final AdminService adminService;
    @Override
    @CrossOrigin(origins = "*")
    public ResponseEntity<DefaultResponse> addNewOffer(final AddNewOfferRequest request) {
        return adminService.addNewOffer(request);
    }

    @Override
    @CrossOrigin(origins = "*")
    public ResponseEntity<GetOfferResponse> listAllOffers() {
        return adminService.listAllOffers();
    }

    @Override
    @CrossOrigin(origins = "*")
    public ResponseEntity<ListAllUsersResponse> listAllUsers() {
        return adminService.listAllUsers();
    }
}

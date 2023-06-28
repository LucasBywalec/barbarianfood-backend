package com.barbarian.barbarianfood.service;

import com.barbarian.barbarianfood.repository.CustomerRepository;
import com.barbarian.barbarianfood.repository.OfferRepository;
import com.barbarian.barbarianfood.service.converters.AdminConverter;
import com.barbarian.barbarianfood.service.converters.OfferConverter;
import com.barbarian.barbarianfood.service.validator.AdminServiceValidator;
import com.zaiapi.openapi.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final OfferRepository offerRepository;
    private final CustomerRepository customerRepository;
    //TODO Implement role validation later
    public ResponseEntity<DefaultResponse> addNewOffer(final AddNewOfferRequest request) {
        if(!AdminServiceValidator.isAddNewOfferRequestValid(request)){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
        }

        offerRepository.save(AdminConverter.addNewOfferRequestToOffer(request));

        return ResponseEntity.ok(null);
    }

    public ResponseEntity<GetOfferResponse> listAllOffers() {
        return ResponseEntity.ok().body(
                new GetOfferResponse()
                        .offerList(
                                offerRepository.findAll()
                                        .stream()
                                        .map(OfferConverter::OfferToOfferItem)
                                        .toList()
                        )
        );
    }

    public ResponseEntity<ListAllUsersResponse> listAllUsers() {
        return ResponseEntity.ok().body(
                new ListAllUsersResponse()
                        .usersList(
                                customerRepository.findAll()
                                        .stream()
                                        .map(AdminConverter::customerBaseToUserItem)
                                        .toList()
                        )
        );
    }

    public void deleteUser(String id) {
        customerRepository.deleteById(id);
    }

    public void deleteOffer(String id) {
        offerRepository.deleteById(id);
    }
}

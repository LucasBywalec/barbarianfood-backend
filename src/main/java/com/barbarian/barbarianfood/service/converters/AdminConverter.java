package com.barbarian.barbarianfood.service.converters;

import com.barbarian.barbarianfood.entity.CustomerBase;
import com.barbarian.barbarianfood.entity.Offer;
import com.barbarian.barbarianfood.entity.OfferPeriod;
import com.zaiapi.openapi.model.AddNewOfferRequest;
import com.zaiapi.openapi.model.UserItem;

public class AdminConverter {
    public static Offer addNewOfferRequestToOffer(AddNewOfferRequest request) {
        return Offer.builder()
                .title(request.getTitle())
                .contraindications(request.getContraindications())
                .cost(request.getCost())
                .kcalRangeBottom(request.getKcalRangeBottom())
                .kcalRangeTop(request.getKcalRangeTop())
                .period(OfferPeriod.valueOf(request.getPeriod().name()))
                .build();
    }

    public static UserItem customerBaseToUserItem(CustomerBase customer) {
        UserItem user = new UserItem();
        user.setId(customer.getId());
        user.setName(customer.getName());
        user.setSurname(customer.getSurname());
        user.setEmail(customer.getEmail());
        user.setActiveSubscription(customer.getOffer().getTitle());

        return user;
    }
}

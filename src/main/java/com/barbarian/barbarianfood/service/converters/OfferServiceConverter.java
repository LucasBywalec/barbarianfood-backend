package com.barbarian.barbarianfood.service.converters;

import com.barbarian.barbarianfood.entity.Offer;
import com.zaiapi.openapi.model.GetOfferDetailsResponse;
import com.zaiapi.openapi.model.OfferItem;
import lombok.experimental.UtilityClass;

@UtilityClass
public class OfferServiceConverter {

    public OfferItem OfferToOfferItem(final Offer offer){
        OfferItem item = new OfferItem();
        item.setId(offer.getId());
        item.setTitle(offer.getTitle());
        item.setCost(offer.getCost());
        item.setKcalRangeBottom(offer.getKcalRangeBottom());
        item.setKcalRangeTop(offer.getKcalRangeTop());
        return item;
    }

    public GetOfferDetailsResponse OfferToOfferDetailsResponse(final Offer offer){
        GetOfferDetailsResponse item = new GetOfferDetailsResponse();
        item.setId(offer.getId());
        item.setTitle(offer.getTitle());
        item.setFor(GetOfferDetailsResponse.ForEnum.valueOf(offer.getOfferFor().name()));
        item.setKcalRangeBottom(offer.getKcalRangeBottom());
        item.setKcalRangeTop(offer.getKcalRangeTop());
        item.setPeriod(GetOfferDetailsResponse.PeriodEnum.valueOf(offer.getPeriod().name()));
        item.setContraindications(offer.getContraindications());
        item.setCost(offer.getCost());
        return item;
    }
}

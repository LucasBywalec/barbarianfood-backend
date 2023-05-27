package com.barbarian.barbarianfood.service.validator;

import com.zaiapi.openapi.model.SubscribeRequest;
import lombok.experimental.UtilityClass;

@UtilityClass
public class OfferServiceValidator {
    public static boolean isSubscribeRequestValid(final SubscribeRequest request) {
        return !request.getPeriodEnd().isBefore(request.getPeriodStart());
    }
}

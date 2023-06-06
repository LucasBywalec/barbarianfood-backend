package com.barbarian.barbarianfood.service.validator;

import com.zaiapi.openapi.model.AddNewOfferRequest;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;

@UtilityClass
public class AdminServiceValidator {
    public static boolean isAddNewOfferRequestValid(final AddNewOfferRequest request) {
        String title = request.getTitle();
        if(title == null || title.isBlank()){
            return false;
        }
        Integer kcalBottom = request.getKcalRangeBottom();
        if(kcalBottom == null || kcalBottom < 1){
            return false;
        }
        Integer kcalTop = request.getKcalRangeTop();
        if(kcalTop == null || kcalTop < 1 || kcalTop < kcalBottom){
            return false;
        }
        BigDecimal cost = request.getCost();
        return cost != null && !(cost.floatValue() < 0);
    }
}

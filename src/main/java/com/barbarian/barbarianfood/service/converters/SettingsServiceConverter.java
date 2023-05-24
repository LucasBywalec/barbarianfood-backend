package com.barbarian.barbarianfood.service.converters;

import com.barbarian.barbarianfood.entity.CustomerAddress;
import com.zaiapi.openapi.model.AddressSettingsRequest;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SettingsServiceConverter {

    public static CustomerAddress addressSettingsRequestToCustomerAddress(AddressSettingsRequest address) {
        return CustomerAddress.builder()
                .city(address.getCity())
                .buildingNumber(address.getBuildingNumber())
                .phoneNumber(address.getPhoneNumber())
                .street(address.getStreet())
                .voivodeship(address.getVoivodeship())
                .postalCode(address.getPostalCode())
                .build();
    }
}

package com.expedia.eps.property.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Property {

    private String providerPropertyId;
    private String provider;
    private Integer expediaId;
    private String name;
    private String latitude;
    private String longitude;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private String createdUtc;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private String modifiedUtc;

    private String providerPropertyUrl;
    private StructureType structureType;
    private String currencyCode;
    private String billingCurrencyCode;
    private String timeZone;
    private List<Address> addresses;
    private List<PropertyRating> ratings;
    private PropertyContacts contacts;
    private List<Airport> airports;
    private List<PropertyContent> contents;
    private List<PropertyCollectedMandatoryFee> propertyCollectedMandatoryFees;
    private List<Tax> taxes;
    private List<Policy> policies;
    private InventorySettings inventorySettings;
    private List<PropertyAttribute> attributes;
    private Link status;
    private List<RoomType> roomTypes;

}

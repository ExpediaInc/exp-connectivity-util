package com.expedia.eps.property.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PropertyContacts {

    @JsonProperty("Property")
    public Contact property;
    @JsonProperty("ReservationManager")
    public Contact reservationManager;
    @JsonProperty("AlternateReservationManager")
    public Contact alternateReservationManager;
    @JsonProperty("GeneralManager")
    public Contact generalManager;

    // private Contact propertyExtranetUser;

}


package com.expedia.eps.product.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PropertyAddress {

    private String line1;
    private String line2;
    private String city;
    private String state;
    private String postalCode;
    private String countryCode;
}

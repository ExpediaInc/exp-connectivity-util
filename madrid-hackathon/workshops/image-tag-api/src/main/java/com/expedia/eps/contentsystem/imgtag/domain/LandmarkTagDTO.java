package com.expedia.eps.contentsystem.imgtag.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(suppressConstructorProperties = true)
@NoArgsConstructor
public class LandmarkTagDTO extends LabelTagDTO {
    private String lat;
    private String lng;
    private String value;
    private Address address;

    public LandmarkTagDTO(String source, String text, float score, String lat, String lng, String value, Address address) {
        super(source, text, score);
        this.lat = lat;
        this.lng = lng;
        this.value = value;
        this.address = address;
    }

}

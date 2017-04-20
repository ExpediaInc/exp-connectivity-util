package com.expedia.eps.contentsystem.imgtag.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(suppressConstructorProperties = true)
@NoArgsConstructor
public class SafeSearchTagDTO extends TagDTO {
    private double confidence;

    public SafeSearchTagDTO(String source, String type, double confidence) {
        super(source, type);
        this.confidence = confidence;
    }

}

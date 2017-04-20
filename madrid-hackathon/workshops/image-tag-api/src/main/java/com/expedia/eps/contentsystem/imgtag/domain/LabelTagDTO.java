package com.expedia.eps.contentsystem.imgtag.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(suppressConstructorProperties = true)
@NoArgsConstructor
public class LabelTagDTO extends TagDTO {
    private double confidence;

    public LabelTagDTO(String source, String label, double confidence) {
        super(source, label);
        this.confidence = confidence;
    }

}

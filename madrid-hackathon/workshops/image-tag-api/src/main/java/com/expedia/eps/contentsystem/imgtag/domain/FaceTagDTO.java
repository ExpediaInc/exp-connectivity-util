package com.expedia.eps.contentsystem.imgtag.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(suppressConstructorProperties = true)
@NoArgsConstructor
public class FaceTagDTO extends TagDTO {
    private Integer age;
    private String gender;
    private double confidence;
    private FaceRectangle faceRectangle;

    public FaceTagDTO(String source, Integer age, String gender, double confidence, FaceRectangle faceRectangle) {
        super(source, "face");
        this.age = age;
        this.gender = gender;
        this.confidence = confidence;
        this.faceRectangle = faceRectangle;
    }

}

package com.expedia.eps.contentsystem.imgtag.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(suppressConstructorProperties = true)
@NoArgsConstructor
public class TextTagDTO extends TagDTO {

    private String value;

    public TextTagDTO(String source, String text, String value) {
        super(source, text);
        this.value = value;
    }

}

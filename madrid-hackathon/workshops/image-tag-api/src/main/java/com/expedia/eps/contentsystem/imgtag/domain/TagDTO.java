package com.expedia.eps.contentsystem.imgtag.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor(suppressConstructorProperties = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TagDTO {

    protected String source;

    protected String name;

}

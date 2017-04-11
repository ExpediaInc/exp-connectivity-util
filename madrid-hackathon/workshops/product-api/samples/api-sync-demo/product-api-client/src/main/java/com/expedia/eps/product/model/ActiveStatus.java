package com.expedia.eps.product.model;

import com.fasterxml.jackson.annotation.JsonValue;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ActiveStatus {

    ACTIVE("Active"),
    INACTIVE("Inactive");

    @Getter(onMethod = @__(@JsonValue))
    private String value;
}

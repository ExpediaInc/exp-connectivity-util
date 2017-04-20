package com.expedia.eps.contentsystem.tcs.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor(suppressConstructorProperties = true)
public class AuditDTO {
    private String source;
}

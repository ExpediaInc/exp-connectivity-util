package com.expedia.eps.contentsystem.tcs.domain;

import java.util.HashMap;
import java.util.Map;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(suppressConstructorProperties = true)
public class ImageDTO {

    private String alt;
    private String caption;
    private boolean hero;
    private String id;
    private ImageMetadataDTO imageMetadataDTO;
    private String url;
    private AuditDTO audit = new AuditDTO();

    @Data
    @NoArgsConstructor
    @AllArgsConstructor(suppressConstructorProperties = true)
    public static class ImageMetadataDTO {
        private Map<String, String> metadata = new HashMap<>();
    }
}

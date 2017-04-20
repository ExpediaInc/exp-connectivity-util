package com.expedia.eps.contentsystem.tcs.domain;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(suppressConstructorProperties = true)
public class GeneratedContentDTO {

    public enum Level {
        Global, Entity
    }

    private Map<String, Content> generatedContent = new HashMap<>();

    @Data
    @NoArgsConstructor
    @AllArgsConstructor(suppressConstructorProperties = true)
    public static class Content {
        private String value;
    }
}

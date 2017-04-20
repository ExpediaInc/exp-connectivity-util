package com.expedia.eps.contentsystem.tcs.domain;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(suppressConstructorProperties = true)
public class PoiDTO implements SectionDTO {

    @Singular("data")
    private List<Poi> data;
    private Map<String, GeneratedContentDTO.Content> generatedContent;
    private String creationDate;

    public List<Poi> getPois(Integer limit) {
        if (data.isEmpty()) {
            return Collections.emptyList();
        }

        if (limit != null) {
            return data.stream().limit(limit).collect(Collectors.toList());
        }

        return data;
    }

    @Override
    public int getSize() {
        return this.getData() == null ? 0 : this.getData().size();
    }

    @Override
    public String getSectionName() {
        return Section.POI.name();
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(suppressConstructorProperties = true)
    public static class Poi {
        private Geo geo;
        private AdditionalInfo additionalInfo;
        private Description description;
        private Tags tags;
        @Singular
        private List<ImageDTO> images;

        public List<ImageDTO> getImages(Integer limit) {
            if (images.isEmpty()) {
                return Collections.emptyList();
            }

            if (limit != null) {
                return images.stream().limit(limit).collect(Collectors.toList());
            }

            return images;
        }
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(suppressConstructorProperties = true)
    public static class Tags {
        private List<Tag> data;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(suppressConstructorProperties = true)
    public static class Tag {
        private String category;
        private String id;
        private String name;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(suppressConstructorProperties = true)
    public static class Geo {
        private String id;
        private double latitude;
        private double longitude;
        private String name;
        private AuditDTO audit = new AuditDTO();
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(suppressConstructorProperties = true)
    public static class AdditionalInfo {
        private String address;
        private String phone;
        private String hours;
        private int reviewCount;
        private double starRating;
        private String url;
        private String website;
        private String reviewSnippet;
        private Review review;
        private AuditDTO audit = new AuditDTO();
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(suppressConstructorProperties = true)
    public static class Description {
        private String id;
        private String value;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(suppressConstructorProperties = true)
    public static class Review {
        private String content;
        private String url;
        private String author;

        private Map<String, GeneratedContentDTO.Content> generatedContent;

        private AuditDTO audit = new AuditDTO();
    }
}

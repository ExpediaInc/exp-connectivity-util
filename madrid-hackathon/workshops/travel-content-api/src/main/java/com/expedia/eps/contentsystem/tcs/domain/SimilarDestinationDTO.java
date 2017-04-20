package com.expedia.eps.contentsystem.tcs.domain;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(suppressConstructorProperties = true)
public class SimilarDestinationDTO implements SectionDTO {
    private String creationDate;
    private Map<String, GeneratedContentDTO.Content> generatedContent;
    private List<SimilarDestination> data;

    public List<SimilarDestination> getSimilarDestinations(Integer limit) {
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
        return Section.SIMILAR_DESTINATION.name();
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(suppressConstructorProperties = true)
    public static class SimilarDestination {
        private Geo geo;
        private Tags tags;

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
            private String id;
            private String displayedName;
        }

        @Data
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor(suppressConstructorProperties = true)
        public static class Geo {
            private String country;
            private String name;
            private String regionId;
            private AuditDTO audit = new AuditDTO();
        }
    }

}

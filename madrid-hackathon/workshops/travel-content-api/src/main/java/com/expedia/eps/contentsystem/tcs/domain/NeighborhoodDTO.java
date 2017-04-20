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
public class NeighborhoodDTO implements SectionDTO {
    private String creationDate;
    private Map<String, GeneratedContentDTO.Content> generatedContent;
    private List<Neighborhood> data;

    public List<Neighborhood> getNeighborhood(Integer limit) {
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
        return Section.NEIGHBORHOOD.name();
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(suppressConstructorProperties = true)
    public static class Neighborhood {
        private Geo geo;
        private Description description;
        private Tags tags;
        private double score;
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
            private String name;
            private String regionId;
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
    }

}

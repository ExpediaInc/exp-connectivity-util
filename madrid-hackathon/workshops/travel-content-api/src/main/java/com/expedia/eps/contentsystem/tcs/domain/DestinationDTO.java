package com.expedia.eps.contentsystem.tcs.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(suppressConstructorProperties = true)
public class DestinationDTO implements SectionDTO {
    private Affinity affinity;
    private String creationDate;
    private Description description;
    private Geo geo;
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
    public static class Geo {
        private String country;
        private double latitude;
        private double longitude;
        private String name;
        private String regionId;
        private AuditDTO audit = new AuditDTO();
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(suppressConstructorProperties = true)
    public static class Description {
        private DescriptionItem small;
        private DescriptionItem medium;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(suppressConstructorProperties = true)
    public static class DescriptionItem {
        private String id;
        private String value;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(suppressConstructorProperties = true)
    public static class Affinity {
        private String id;
        private String displayedName;
    }

    @Override
    public int getSize() {
        return this.getGeo() != null && this.getGeo().getRegionId() != null ? 1 : 0;
    }

    @Override
    public String getSectionName() {
        return Section.DESTINATION.name();
    }
}

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
public class AffinityDTO implements SectionDTO {
    private String creationDate;
    private Map<String, GeneratedContentDTO.Content> generatedContent;
    private List<Affinity> data;

    public List<Affinity> getAffinities(Integer limit) {
        if (data.isEmpty()) {
            return Collections.emptyList();
        }

        if (limit != null) {
            return data.stream().limit(limit).collect(Collectors.toList());
        }

        return data;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(suppressConstructorProperties = true)
    public static class Affinity {
        private Map<String, GeneratedContentDTO.Content> generatedContent;
        private String id;
        private int hotelCount;
        private String name;
        private int order;
        private int postStayEndorsements;
        private int reviewCount;
        private double score;
        private ImageDTO image;
        private AuditDTO audit = new AuditDTO();
    }

    @Override
    public int getSize() {
        return this.getData() == null ? 0 : this.getData().size();
    }

    @Override
    public String getSectionName() {
        return Section.AFFINITY.name();
    }
}

package com.expedia.eps.contentsystem.tcs.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(suppressConstructorProperties = true)
public class TcsDTO {
    private String requestId;
    private String creationDate;
    private Sections sections = new Sections();

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(suppressConstructorProperties = true)
    public static class Sections {
        private ActivityDTO activity;
        private AffinityDTO affinity;
        private ArticleDTO article;
        private DestinationDTO destination;
        private NeighborhoodDTO neighborhood;
        private PoiDTO poi;
        private SimilarDestinationDTO similarDestination;
        private TipDTO tip;
    }
}

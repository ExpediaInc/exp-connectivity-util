package com.expedia.eps.contentsystem.tcs.domain;

import java.util.Collections;
import java.util.Date;
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
public class HotelDTO implements SectionDTO {
    private String creationDate;
    private Map<String, GeneratedContentDTO.Content> generatedContent;

    @Singular("data")
    private List<Hotel> data;

    public List<Hotel> getHotels(Integer limit) {
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
    public static class Hotel {
        private Destination destination;
        private String hotelId;
        private PeriodDTO period;
        private PriceDTO price;
        private int recommendationScore;
        private Map<String, GeneratedContentDTO.Content> generatedContent;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(suppressConstructorProperties = true)
    public static class Destination {
        private Description description;
        private Geo geo;
        private Description title;

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
            private double latitude;
            private double longitude;
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

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(suppressConstructorProperties = true)
    public static class PriceDTO {
        private float amount;
        private String currency;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(suppressConstructorProperties = true)
    public static class PeriodDTO {
        private Date end;
        private Date start;
        private AuditDTO audit;
    }

    @Override
    public int getSize() {
        return this.getData() == null ? 0 : this.getData().size();
    }

    @Override
    public String getSectionName() {
        return "hotel";
    }
}

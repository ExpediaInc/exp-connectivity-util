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
public class TipDTO implements SectionDTO {
    private String creationDate;
    private Map<String, GeneratedContentDTO.Content> generatedContent;
    private List<Tip> data;

    public List<Tip> getTips(Integer limit) {
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
    public static class Tip {
        private Author author;
        private String body;
        private Geo geo;
        private String id;
        private ImageDTO image;
        private String name;
        private Tags tags;
        private AuditDTO audit = new AuditDTO();

        @Data
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor(suppressConstructorProperties = true)
        public static class Author {
            private String id;
            private String username;
            private String name;
            private String firstName;
            private String lastName;
            private String description;
            private String community;
            private String avatar;
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
            private String id;
            private String type;
        }
    }

    @Override
    public int getSize() {
        return this.getData() == null ? 0 : this.getData().size();
    }

    @Override
    public String getSectionName() {
        return Section.TIP.name();
    }

}

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
public class ArticleDTO implements SectionDTO {
    private String creationDate;
    private List<Article> data;
    private Map<String, GeneratedContentDTO.Content> generatedContent;

    public List<Article> getArticles(Integer limit) {
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
    public static class Article {
        private Author author;
        private String content;
        private String excerpt;
        private String id;
        private String publicationDate;
        private String title;
        private String url;
        private List<ImageDTO> images;
        private AuditDTO audit = new AuditDTO();

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
    public static class Author {
        private String avatar;
        private String name;
        private String url;
    }

    @Override
    public int getSize() {
        return this.getData() == null ? 0 : this.getData().size();
    }

    @Override
    public String getSectionName() {
        return Section.ARTICLE.name();
    }
}

package com.expedia.eps.contentsystem.imgtag.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(suppressConstructorProperties = true)
@Builder
public class MediaTagResponse {
    private String mediaUrl;
    private String filename;
    private List<LabelTagDTO> label;
    private List<SafeSearchTagDTO> safesearch;
    private List<LandmarkTagDTO> landmark;
    private List<TextTagDTO> text;
    private List<FaceTagDTO> face;

}

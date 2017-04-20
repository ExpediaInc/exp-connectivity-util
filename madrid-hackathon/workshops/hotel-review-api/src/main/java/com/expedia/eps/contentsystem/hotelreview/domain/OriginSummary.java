package com.expedia.eps.contentsystem.hotelreview.domain;

import java.util.Map;

import lombok.Data;

@Data
public class OriginSummary {

    private String origin;
    private Integer reviewCnt;
    private Float recommendedPercent;
    private Float avgOverallRating;
    private Float cleanliness;
    private Float serviceAndStaff;
    private Float roomComfort;
    private Float hotelCondition;
    private Float convenienceOfLocation;
    private Float neighborhoodSatisfaction;

    private Map<String, Integer> categoryCounts;
    private Map<String, Integer> languageCounts;


}

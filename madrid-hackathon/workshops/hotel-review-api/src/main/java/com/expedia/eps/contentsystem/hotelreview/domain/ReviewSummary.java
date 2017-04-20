package com.expedia.eps.contentsystem.hotelreview.domain;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class ReviewSummary {

    private String hotelId;
    private Integer totalReviewCnt;
    private Integer numPhotos;
    private Float avgOverallRating;
    private Float cleanliness;
    private Float serviceAndStaff;
    private Float roomComfort;
    private Float hotelCondition;
    private Float convenienceOfLocation;
    private Float neighborhoodSatisfaction;
    private Float recommendedPercent;
    private Float valueForMoney;

    private List<OriginSummary> originSummary;
    private Map<String, Integer> categoryCounts;
    private Map<String, Integer> languageCounts;

    private Review pinnedHelpfulReview;
}

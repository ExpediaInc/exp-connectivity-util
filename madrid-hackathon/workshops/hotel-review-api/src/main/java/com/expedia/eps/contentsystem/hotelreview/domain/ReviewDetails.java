package com.expedia.eps.contentsystem.hotelreview.domain;

import lombok.Data;

@Data
public class ReviewDetails {

    private Integer startIndex;
    private Integer numberOfReviewsInThisPage;
    private ReviewSummaryCollection reviewSummaryCollection;
    private ReviewCollection reviewCollection;
}

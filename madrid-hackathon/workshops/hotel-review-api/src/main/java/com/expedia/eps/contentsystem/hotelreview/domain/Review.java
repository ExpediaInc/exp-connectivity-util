package com.expedia.eps.contentsystem.hotelreview.domain;

import java.util.List;

import lombok.Data;

@Data
public class Review {

    private String reviewId;
    private Integer hotelId;
    private Integer roomTypeId;

    private String title;
    private String reviewText;
    private String contentLocale;
    private String brandType;
    private String moderationStatus;

    private String reviewSubmissionTime;

    private String isRecommended;
    private Boolean isFlaggable;
    private Boolean isUnverified;

    private Integer ratingOverall;
    private Integer ratingRoomCleanliness;
    private Integer ratingHotelCondition;
    private Integer ratingService;
    private Integer ratingRoomComfort;

    private List<ReviewerCategory> reviewerCategories;
}

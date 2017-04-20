package com.expedia.eps.contentsystem.hotelreview.domain;

import lombok.Data;

@Data
public class UserReviewResponse {

    private Boolean success;
    private ReviewDetails details;

}


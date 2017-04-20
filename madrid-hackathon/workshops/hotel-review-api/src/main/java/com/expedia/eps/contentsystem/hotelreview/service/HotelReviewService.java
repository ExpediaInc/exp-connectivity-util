package com.expedia.eps.contentsystem.hotelreview.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.expedia.eps.contentsystem.hotelreview.domain.HotelReviewAnalysisResponse;

@FeignClient(value = "hotelReviewService")
public interface HotelReviewService {

    @RequestMapping(value = {"/hotel-review/service/v1/analyze"}, method = RequestMethod.POST)
    HotelReviewAnalysisResponse analyze(@RequestBody String text);

}

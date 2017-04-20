package com.expedia.eps.contentsystem.hotelreview.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.expedia.eps.contentsystem.hotelreview.domain.HotelReviewAnalysisResponse;
import com.expedia.eps.contentsystem.hotelreview.domain.UserReviewResponse;

@FeignClient(value = "hotelReviewService")
public interface HotelReviewService {

    @RequestMapping(value = {"/hotel-review/service/v1/analyze"}, method = RequestMethod.POST)
    HotelReviewAnalysisResponse analyze(@RequestBody String text);

    @RequestMapping(value = "/hotel-review/service/v1/retrieve/{hotelId}", produces = "application/json", method = RequestMethod.GET)
    UserReviewResponse reviews(@PathVariable("hotelId") String hotelId,
            @RequestParam(value = "from", defaultValue = "0") Integer from,
            @RequestParam(value = "nb", defaultValue = "10") Integer nb);

}

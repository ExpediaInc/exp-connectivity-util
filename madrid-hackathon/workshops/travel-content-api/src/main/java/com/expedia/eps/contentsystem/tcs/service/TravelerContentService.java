package com.expedia.eps.contentsystem.tcs.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.expedia.eps.contentsystem.tcs.domain.TcsDTO;

@FeignClient(value = "travelerContentService")
public interface TravelerContentService {

    @RequestMapping(value = {"/travel-content/service/travel/regionId/{regionId}"}, method = RequestMethod.GET)
    TcsDTO getContentForRegion(@PathVariable("regionId") String regionId,
            @RequestParam("langId") String langId,
            @RequestParam("sections") List<String> sections,
            @RequestHeader(value="Accept-Encoding") String userAgent);

    @RequestMapping(value = {"/travel-content/service/travel/regionId/{regionId}"}, method = RequestMethod.GET)
    TcsDTO getContentForRegionAndTag(@PathVariable("regionId") String regionId,
            @RequestParam("tag") String tag,
            @RequestParam("langId") String langId,
            @RequestParam("sections") List<String> sections,
            @RequestHeader(value="Accept-Encoding") String userAgent);

}

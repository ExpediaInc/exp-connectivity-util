package com.expedia.eps.contentsystem.imgtag.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.expedia.eps.contentsystem.imgtag.domain.MediaTagResponse;

@FeignClient(value = "imageTaggingService")
public interface ImageTaggingService {

    @RequestMapping(value = {"/image-tag/v1"}, method = RequestMethod.POST)
    MediaTagResponse tagImage(@RequestParam("mediaUrl") String mediaURL);

    @RequestMapping(value = {"/image-tag/v2"}, method = RequestMethod.POST)
    MediaTagResponse tagImageIncludingPL(@RequestParam("mediaUrl") String mediaURL);

}

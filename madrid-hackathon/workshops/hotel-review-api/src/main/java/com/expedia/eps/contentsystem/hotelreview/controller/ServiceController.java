package com.expedia.eps.contentsystem.hotelreview.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.expedia.eps.contentsystem.hotelreview.domain.HotelReviewAnalysisResponse;
import com.expedia.eps.contentsystem.hotelreview.service.HotelReviewService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/service")
@Api("Service Description goes here")
public class ServiceController {

    private HotelReviewService reviewService;

    public ServiceController(HotelReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @ApiOperation(value = "Endpoint Summary Description goes here",
            notes = "Endpoint Complete Description goes here",
            response = Response.class)
    @RequestMapping(value = "/dosomething", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Response> doSomething(@RequestBody String text) throws Exception {

        // Start coding service implementation here..
        //
        // .. this is just a dummy example that calls the HotelReviewAnalyzer service
        HotelReviewAnalysisResponse response = reviewService.analyze(text);
        //
        //

        // return response
        return ResponseEntity.ok(new Response(response.isSuccess(), text));
    }


    @ApiModel(value = "Response", description = "Service Response")
    public static class Response {
        @ApiModelProperty(value = "Success of the service", allowableValues = "true,false", example = "true", required = true)
        private boolean success;

        private String text;

        public Response(boolean success, String text) {
            this.success = success;
            this.text = text;
        }

        public boolean isSuccess() {
            return success;
        }

        public String getText() {
            return text;
        }
    }

}

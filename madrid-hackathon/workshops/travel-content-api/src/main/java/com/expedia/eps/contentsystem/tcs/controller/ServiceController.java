package com.expedia.eps.contentsystem.tcs.controller;

import java.util.EnumSet;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.expedia.eps.contentsystem.tcs.domain.Section;
import com.expedia.eps.contentsystem.tcs.domain.TcsDTO;
import com.expedia.eps.contentsystem.tcs.service.TravelerContentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/service")
@Api("Service Description goes here")
public class ServiceController {

    private TravelerContentService tcs;

    public ServiceController(TravelerContentService tcs) {
        this.tcs = tcs;
    }

    @ApiOperation(value = "Endpoint Summary Description goes here",
            notes = "Endpoint Complete Description goes here",
            response = Response.class)
    @RequestMapping(value = "/dosomething/{regionId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Response> doSomething(@PathVariable("regionId") String regionId) throws Exception {

        // Start coding service implementation here..
        //
        // .. this is just a dummy example that calls TCS
        EnumSet<Section> sections = EnumSet.of(Section.ACTIVITY, Section.AFFINITY);
        TcsDTO response = tcs.getContentForRegion(regionId, "EN", Section.toList(sections), "gzip, deflate");
        //
        //

        // return response
        return ResponseEntity.ok(new Response(true, response.getSections().getActivity().getSectionName()));
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

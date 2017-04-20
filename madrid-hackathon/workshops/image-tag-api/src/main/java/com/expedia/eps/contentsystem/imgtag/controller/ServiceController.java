package com.expedia.eps.contentsystem.imgtag.controller;

import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.expedia.eps.contentsystem.imgtag.domain.MediaTagResponse;
import com.expedia.eps.contentsystem.imgtag.domain.TagDTO;
import com.expedia.eps.contentsystem.imgtag.service.ImageTaggingService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/service")
@Api("Service Description goes here")
public class ServiceController {

    private ImageTaggingService imageTaggingService;

    public ServiceController(ImageTaggingService imageTaggingService) {
        this.imageTaggingService = imageTaggingService;
    }

    @ApiOperation(value = "Endpoint Summary Description goes here",
            notes = "Endpoint Complete Description goes here",
            response = Response.class)
    @RequestMapping(value = "/dosomething", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Response> doSomething() throws Exception {

        // Start coding service implementation here..
        //
        // .. this is just a dummy example that calls ImageTagging
        MediaTagResponse response = imageTaggingService.tagImage("http://www.spain.info/export/sites/spaininfo/comun/carrusel-recursos/madrid/dp_madrid_dg_im_03.jpg_369272544.jpg");
        //
        //

        // return response
        return ResponseEntity.ok(new Response(true, response.getLabel().stream().map(TagDTO::getName).collect(Collectors.joining(","))));
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

package com.expedia.eps.contentsystem.imgtag.controller;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.cloud.netflix.feign.FeignContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ServiceController.class)
public class ServiceControllerTest {

    @Autowired
    private MockMvc mvc;

//    @Test
//    public void test200Basic() throws Exception {
//        this.mvc.perform(post("/service/dosomething").accept(MediaType.APPLICATION_JSON).content("I love this hotel!"))
//                .andExpect(status().isOk()).andExpect(content().string("{}"));
//    }

    @Test
    public void test404Basic() throws Exception {

        this.mvc.perform(post("/wrong-url").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Configuration
    public static class FeignConfig {

        @Bean
        public FeignContext generateFeignContext() {
            return new FeignContext();
        }

    }

}

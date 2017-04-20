package com.expedia.eps.contentsystem.tcs.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Logger;
import feign.RequestInterceptor;
import feign.auth.BasicAuthRequestInterceptor;

@Configuration
public class ApplicationConfig {

    @Value("${hackathon.username}")
    private String username;
    @Value("${hackathon.password}")
    private String password;

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    RequestInterceptor feignRequestInterceptor() {
        return new BasicAuthRequestInterceptor(username, password);
    }

}

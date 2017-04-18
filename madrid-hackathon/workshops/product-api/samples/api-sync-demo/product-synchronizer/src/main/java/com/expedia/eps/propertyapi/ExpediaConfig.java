package com.expedia.eps.propertyapi;

import com.expedia.eps.ExpediaAuthenticationInterceptor;
import com.expedia.eps.ExpediaCredentials;
import com.expedia.eps.image.ImageApi;
import com.expedia.eps.product.ProductApi;
import com.expedia.eps.property.PropertyApi;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixThreadPoolKey;
import feign.Logger;
import feign.hystrix.HystrixFeign;
import feign.hystrix.SetterFactory;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.slf4j.Slf4jLogger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES;
import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Configuration
public class ExpediaConfig {

    @Bean
    @ConditionalOnMissingBean
    public SetterFactory buildHystrixSetter() {
        return (target, method) -> HystrixCommand.Setter
            .withGroupKey(HystrixCommandGroupKey.Factory.asKey(target.type().getSimpleName()))
            .andCommandKey(HystrixCommandKey.Factory.asKey(method.getName()))
            .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey(target.type().getSimpleName()));
    }

    @Bean
    @ConditionalOnMissingBean
    public ExpediaCredentials expediaCredentials(@Value("${expedia.credentials.username}") String username,
                                                 @Value("${expedia.credentials.password}") String password) {
        return ExpediaCredentials.builder()
            .username(username)
            .password(password)
            .build();
    }

    @Bean
    @ConditionalOnMissingBean
    public ExpediaAuthenticationInterceptor expediaAuthenticator(ExpediaCredentials credentials) {
        return ExpediaAuthenticationInterceptor.builder()
            .credentials(credentials)
            .build();
    }

    @Bean
    @Scope(SCOPE_PROTOTYPE) // one instance per API client
    public ExpediaRetryer expediaRetryer(@Value("${expedia.retry.maxAttempts}") Integer maxRetryAttempts,
                                         @Value("${expedia.retry.intervalInMillis}") Long intervalInMillis) {
        return ExpediaRetryer.builder()
            .maxAttempts(maxRetryAttempts)
            .intervalInMillis(intervalInMillis)
            .build();
    }

    private ObjectMapper customObjectMapper() {
        return new ObjectMapper()
            .configure(FAIL_ON_IGNORED_PROPERTIES, false)
            .registerModule(new JavaTimeModule())
            .setSerializationInclusion(NON_NULL);
    }

    @Bean
    public JacksonEncoder jsonEncoder() {
        return new JacksonEncoder(customObjectMapper());
    }

    @Bean
    public JacksonDecoder jsonDecoder() {
        return new JacksonDecoder(customObjectMapper());
    }

    @Bean
    public ImageApi imageApi(@Value("${expedia.imageApi.url}") String imageApiUrl,
                             SetterFactory customHystrixSetter,
                             ExpediaAuthenticationInterceptor authenticationInterceptor,
                             ExpediaRetryer expediaRetryer,
                             JacksonEncoder jsonEncoder,
                             JacksonDecoder jsonDecoder) {
        return HystrixFeign.builder()
            .setterFactory(customHystrixSetter)
            .decoder(jsonDecoder)
            .encoder(jsonEncoder)
            .logger(new Slf4jLogger(ImageApi.class))
            .logLevel(Logger.Level.BASIC)
            .retryer(expediaRetryer)
            .requestInterceptor(authenticationInterceptor)
            .target(ImageApi.class, imageApiUrl);
    }

    @Bean
    public ProductApi productApi(@Value("${expedia.productApi.url}") String productApiUrl,
                                 SetterFactory customHystrixSetter,
                                 ExpediaAuthenticationInterceptor authenticationInterceptor,
                                 ExpediaRetryer expediaRetryer,
                                 JacksonEncoder jsonEncoder,
                                 JacksonDecoder jsonDecoder) {
        return HystrixFeign.builder()
            .setterFactory(customHystrixSetter)
            .decoder(jsonDecoder)
            .encoder(jsonEncoder)
            .logger(new Slf4jLogger(ProductApi.class))
            .logLevel(Logger.Level.BASIC)
            .retryer(expediaRetryer)
            .requestInterceptor(authenticationInterceptor)
            .target(ProductApi.class, productApiUrl);
    }

    @Bean
    public PropertyApi propertyApi(@Value("${expedia.propertyApi.url}") String propertyApiUrl,
                                   SetterFactory customHystrixSetter,
                                   ExpediaAuthenticationInterceptor authenticationInterceptor,
                                   ExpediaRetryer expediaRetryer,
                                   JacksonEncoder jsonEncoder,
                                   JacksonDecoder jsonDecoder) {
        return HystrixFeign.builder()
                .setterFactory(customHystrixSetter)
                .decoder(jsonDecoder)
                .encoder(jsonEncoder)
                .logger(new Slf4jLogger(PropertyApi.class))
                .logLevel(Logger.Level.BASIC)
                .retryer(expediaRetryer)
                .requestInterceptor(authenticationInterceptor)
                .target(PropertyApi.class, propertyApiUrl);
    }
}

package com.expedia.eps.contentsystem.imgtag.config.swagger;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;
import com.google.common.collect.Sets;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * This class will generate the Swagger documentation for your application.
 */
@EnableSwagger2
@EnableConfigurationProperties(SwaggerProperties.class)
@Configuration
public class SwaggerConfiguration {

    @Autowired
    private SwaggerProperties swaggerProperties;

    @Bean
    public Docket documentation() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(paths())
                .build()
                .pathMapping("/")
                .protocols(Sets.newHashSet("http", "https"))
                .apiInfo(apiInfo());

    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(swaggerProperties.getTitle())
                .description(swaggerProperties.getDescription())
                .termsOfServiceUrl(swaggerProperties.getTermsPath())
                .contact(new Contact("", "", swaggerProperties.getEmail()))
                .license(swaggerProperties.getLicenceType())
                .licenseUrl(swaggerProperties.getLicencePath())
                .version(swaggerProperties.getVersion())
                .build();
    }

    /**
     * @return the predicate used to check if the path should be included or not.
     */
    private Predicate<String> paths() {
        return or(regex("/service.*"));
    }

}

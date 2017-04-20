package com.expedia.eps.contentsystem.hotelreview.config.swagger;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "swagger.service")
public class SwaggerProperties {

    private String version;
    private String title;
    private String description;
    private String termsPath;
    private String email;
    private String licenceType;
    private String licencePath;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTermsPath() {
        return termsPath;
    }

    public void setTermsPath(String termsPath) {
        this.termsPath = termsPath;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLicenceType() {
        return licenceType;
    }

    public void setLicenceType(String licenceType) {
        this.licenceType = licenceType;
    }

    public String getLicencePath() {
        return licencePath;
    }

    public void setLicencePath(String licencePath) {
        this.licencePath = licencePath;
    }
}

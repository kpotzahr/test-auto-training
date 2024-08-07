package com.devonfw.mts.api.config;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.http.HttpHeaders;

import com.devonfw.mts.shared.TestConfiguration;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;

public class RestassuredConnectionSetup implements BeforeAllCallback {

    @Override
    public void beforeAll(ExtensionContext extensionContext) {
        RestAssured.baseURI = TestConfiguration.getApiUrl();
        RestAssured.config = RestAssuredConfig.config().objectMapperConfig(
                ObjectMapperConfig.objectMapperConfig().jackson2ObjectMapperFactory((type, s) -> {
                    ObjectMapper objectMapper = new ObjectMapper();
                    objectMapper.registerModule(new JavaTimeModule());
                    objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
                    return objectMapper;
                })
        );
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .build().header(HttpHeaders.CONTENT_TYPE, "application/json");
    }

}
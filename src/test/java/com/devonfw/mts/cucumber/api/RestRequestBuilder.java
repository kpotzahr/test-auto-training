package com.devonfw.mts.cucumber.api;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import com.devonfw.mts.api.data.User;
import com.devonfw.mts.shared.TestConfiguration;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

@Component
public class RestRequestBuilder {
    private static final String API_LOGIN_URL = TestConfiguration.getApiPath() + "/login";

    private final String accessToken;

    public RestRequestBuilder() {
        RestAssured.baseURI = TestConfiguration.getApiUrl();
        accessToken = RestAssured.with().body(User.validUser())
                .post(API_LOGIN_URL).header(HttpHeaders.AUTHORIZATION);
    }

    public RequestSpecification request() {
        return RestAssured
                .with().contentType("application/json").header("Authorization", "Bearer " + accessToken);
    }

}

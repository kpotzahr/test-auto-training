package com.devonfw.mts.api;


import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.http.HttpHeaders;

import com.devonfw.mts.api.config.RestassuredConnectionSetup;
import com.devonfw.mts.api.data.User;
import com.devonfw.mts.shared.TestConfiguration;

@ExtendWith(RestassuredConnectionSetup.class)
class LoginRestAssuredApiTest {
    private static final String API_LOGIN_URL = TestConfiguration.getApiPath() + "/login";

    @Test
    void loginSuccessful() {
        given().body(User.validUser())
                .when()
                .post(API_LOGIN_URL)
                .then().statusCode(200)
                .header(HttpHeaders.AUTHORIZATION, notNullValue());

    }

    @ParameterizedTest
    @CsvSource({
            "waiter, unknown",
            "unknown,waiter",
            ","})
    void loginUnsuccessful(String username, String password) {
        given().body(new User(username, password))
                .when()
                .post(API_LOGIN_URL)
                .then().statusCode(401)
                .header(HttpHeaders.AUTHORIZATION, nullValue());
    }
}

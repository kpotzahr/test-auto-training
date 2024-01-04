package com.devonfw.mts.api;

import static com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.devonfw.mts.api.config.LoggedInRequestSetup;
import com.devonfw.mts.api.config.WiremockSetup;
import com.devonfw.mts.api.data.BookingWrapper;
import com.devonfw.mts.api.data.SearchCriteria;
import com.devonfw.mts.shared.TestConfiguration;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.verification.LoggedRequest;
import io.restassured.specification.RequestSpecification;

@ExtendWith(LoggedInRequestSetup.class)
@ExtendWith(WiremockSetup.class)
class BookingManagementConfirmationApiTest {
    private static final String BOOKING_BASE_PATH = TestConfiguration.getApiPath() + "/services/rest/bookingmanagement/v1";
    private static final String BOOKING_CREATE_PATH = BOOKING_BASE_PATH + "/booking/";
    private static final String BOOKING_SEARCH_PATH = BOOKING_BASE_PATH + "/booking/search";

    private RequestSpecification given;

    @BeforeEach
    public void setup(RequestSpecification given) {
        this.given = given;
        WireMock.resetAllRequests();
    }

    @Test
    void createSuccessfulBookingAndCheckConfirmationEmail() {
        BookingWrapper booking = BookingWrapper.defaultValidBooking();
        given.body(booking)
                .when()
                .post(BOOKING_CREATE_PATH)
                .then().statusCode(200);
        List<LoggedRequest> requests = WireMock.findAll(postRequestedFor(urlEqualTo("/mail")));
        assertThat(requests).hasSize(1);
        assertThat(new String(requests.get(0).getBody())).contains(booking.getBooking().getEmail());
    }

    @Test
    void bookingNotSuccessfulForNotWorkingEmail() {
        final String errorEmail = "myveryspecialemailforerror@error.de";

        WireMock.stubFor(post(urlEqualTo("/mail"))
                .withRequestBody(matchingJsonPath("recipient", equalTo(errorEmail)))
                .withRequestBody(matchingJsonPath("subject", equalTo("Booking confirmation")))
                .withRequestBody(matchingJsonPath("text", containing(errorEmail)))
                .willReturn(aResponse().withStatus(400)));


        BookingWrapper booking = BookingWrapper.defaultValidBooking();
        booking.getBooking().setEmail(errorEmail);

        given.body(booking)
                .when()
                .post(BOOKING_CREATE_PATH)
                .then().statusCode(500);

        List<LoggedRequest> requests = WireMock.findAll(postRequestedFor(urlEqualTo("/mail")));
        assertThat(requests).hasSize(1);

        given.body(new SearchCriteria().withEmail(booking.getBooking().getEmail())).
                when().post(BOOKING_SEARCH_PATH).
                then().statusCode(204);
    }

}

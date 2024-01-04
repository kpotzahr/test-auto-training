package com.devonfw.mts.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.devonfw.mts.api.config.LoggedInRequestSetup;
import com.devonfw.mts.shared.TestConfiguration;
import io.restassured.specification.RequestSpecification;

@ExtendWith(LoggedInRequestSetup.class)
class BookingManagementCreateApiTest {
    private static final String BOOKING_BASE_PATH = TestConfiguration.getApiPath() + "/services/rest/bookingmanagement/v1";
    private static final String BOOKING_CREATE_PATH = BOOKING_BASE_PATH + "/booking/";
    private static final String BOOKING_SEARCH_PATH = BOOKING_BASE_PATH + "/booking/search";


    private RequestSpecification given;

    @BeforeEach
    public void setup(RequestSpecification given) {
        this.given = given;
    }

    @Test
    void createSuccessfulBookingAndCheckReturnedData() {

    }



}

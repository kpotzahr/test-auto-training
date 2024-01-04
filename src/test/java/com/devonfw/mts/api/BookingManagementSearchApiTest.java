package com.devonfw.mts.api;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.devonfw.mts.api.config.LoggedInRequestSetup;
import com.devonfw.mts.api.data.SearchCriteria;
import com.devonfw.mts.shared.TestConfiguration;
import com.devonfw.mts.shared.TestDataPoolContent;
import io.restassured.specification.RequestSpecification;

@ExtendWith(LoggedInRequestSetup.class)
class BookingManagementSearchApiTest {
    private static final String BOOKING_BASE_PATH = TestConfiguration.getApiPath() + "/services/rest/bookingmanagement/v1";
    private static final String BOOKING_SEARCH_PATH = BOOKING_BASE_PATH + "/booking/search";


    @Test
    void searchByUnknownEmail(RequestSpecification given) {
        given.body(new SearchCriteria().withEmail("unknown@Invalid,com")).
                when().post(BOOKING_SEARCH_PATH).
                then().statusCode(204);
    }

    @Test
    void searchByKnownEmail(RequestSpecification given) {
        String bookingEmail = TestDataPoolContent.instance().getUniqueBookingEmail();
        given.body(new SearchCriteria().withEmail(bookingEmail)).
                when().post(BOOKING_SEARCH_PATH).
                then().statusCode(200).
                body("content", hasSize(1)).
                body("content[0].booking.email", equalTo(bookingEmail)).
                body("content[0].booking.name", equalTo(TestDataPoolContent.instance().getUniqueBookingUser()));
    }

}

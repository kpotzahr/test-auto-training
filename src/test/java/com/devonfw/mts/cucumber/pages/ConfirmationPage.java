package com.devonfw.mts.cucumber.pages;

import java.time.Instant;

import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.devonfw.mts.shared.CustomBy;
import com.devonfw.mts.shared.DateTimeUtils;

@Component
public class ConfirmationPage {
    private static final By BOOKING_DATE_TIME_SELECTOR = CustomBy.testId("date-value");
    private static final By NAME_SELECTOR = CustomBy.testId("name-value");
    private static final By EMAIL_SELECTOR = CustomBy.testId("email-value");
    private static final By GUESTS_SELECTOR = CustomBy.testId("guests-value");

    @Autowired
    private WidgetHelper helper;

    public String getEmail() {
        return helper.widget(EMAIL_SELECTOR).getText();
    }

    public String getName() {
        return helper.widget(NAME_SELECTOR).getText();
    }

    public int getNumberOfGuests() {
        return Integer.parseInt(helper.widget(GUESTS_SELECTOR).getText());
    }

    public Instant getBookingDateTime() {
        return DateTimeUtils.parseUiDateTime(helper.widget(BOOKING_DATE_TIME_SELECTOR).getText());
    }
}

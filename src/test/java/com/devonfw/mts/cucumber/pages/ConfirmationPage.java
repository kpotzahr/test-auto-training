package com.devonfw.mts.cucumber.pages;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.devonfw.mts.shared.CustomBy;

@Component
public class ConfirmationPage {
    private static final By BOOKING_DATE_TIME_SELECTOR = CustomBy.testId("date-value");
    private static final By NAME_SELECTOR = CustomBy.testId("name-value");
    private static final By EMAIL_SELECTOR = CustomBy.testId("email-value");
    private static final By GUESTS_SELECTOR = CustomBy.testId("guests-value");
    private static final By CONFIRM_SELECTOR = CustomBy.testId("booking-send-button");

    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("MM/dd/yyyy, h:mm a")
                    .withLocale(Locale.getDefault())
            .withZone(ZoneId.systemDefault());

    @Autowired
    private WidgetHelper helper;

    @Autowired
    private BrowserAccess browserAccess;

    public void waitUntilActive() {
        (new WebDriverWait(browserAccess.webDriver(), Duration.ofSeconds(10))).
                until(ExpectedConditions.elementToBeClickable(CONFIRM_SELECTOR));
    }

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
        return DATE_TIME_FORMATTER.parse(helper.widget(BOOKING_DATE_TIME_SELECTOR).getText(), Instant::from);
    }
}

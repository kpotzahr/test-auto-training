package com.devonfw.mts.selenium.tests;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import com.devonfw.mts.selenium.pages.BookTablePage;

/**
 * Example solution for exercise 3 and 4 - Book Table Page and Common Page class
 */
final class BookTableTest {
    private static final String SUCCESSFUL_BOOKING = "Table succesfully booked\nOK";

    @Test
    void testBookTable() {
        BookTablePage.navigateTo();
        BookTablePage.bookTable("Tester One", "testerOne@test.com", "2", "8/8/2024, 8:00 PM", true);
        assertEquals(SUCCESSFUL_BOOKING, BookTablePage.getPopupMessage());
    }
}

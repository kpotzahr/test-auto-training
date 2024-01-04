package com.devonfw.mts.selenium.tests;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import com.devonfw.mts.selenium.pages.BookTablePage;

/** Example solution for exercise 3 and 4 - Book Table Page and Common Page class */
final class BookTableTest {
	private static final String ERROR_BOOKING = "Error booking, please try again later\nOK";
	
	@Test
	void testBookTable() {
		BookTablePage.navigateTo();
		BookTablePage.bookTable("Tester One", "testerOne@test.com", "2", true);
		assertEquals(ERROR_BOOKING, BookTablePage.getPopupMessage());
	}
}

package com.devonfw.mts.selenium.tests;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import com.devonfw.mts.selenium.pages.MenuPage;

/** Example solution for exercise 5 - Filtering by slider */
final class MenuTest {
    @Test
    void testFilterMenuByPrice() throws Throwable {
        MenuPage.navigateTo();
        MenuPage.setPriceFilter(10);
        assertTrue(MenuPage.checkAllPricesAreBelowOrEqual(10));
    }
}
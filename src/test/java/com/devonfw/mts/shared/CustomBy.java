package com.devonfw.mts.shared;


import org.openqa.selenium.By;

public abstract class CustomBy extends By {

    public static By testId(String testId) {
        return By.cssSelector("[data-testid='" + testId + "']");
    }

}

package com.devonfw.mts.shared;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverProvider {
    public static WebDriver provideNewWebDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-search-engine-choice-screen");
        options.addArguments("--lang=en");
        return new ChromeDriver(options);
    }
}

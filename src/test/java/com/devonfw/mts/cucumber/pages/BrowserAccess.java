package com.devonfw.mts.cucumber.pages;

import java.time.Duration;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Component;

import com.devonfw.mts.shared.WebDriverProvider;

@Component
public class BrowserAccess {
    private WebDriver webDriver;

    public WebDriver webDriver() {
        if (null == webDriver) {
            openBrowser();
        }

        return webDriver;
    }

    public void openBrowser() {
        if (null != webDriver) {
            return;
        }
        webDriver = WebDriverProvider.provideNewWebDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public boolean isBrowserOpen() {
        return null != webDriver;
    }

    public void closeBrowser() {
        if (null != webDriver) {
            try {
                webDriver.close();
                webDriver.quit();
            } finally {
                webDriver = null;
            }
        }
    }

    public byte[] takeScreenshot() {
        if (null != webDriver) {
            return ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
        } else {
            return null;
        }
    }
}

package com.devonfw.mts.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public final class LoginPage extends Page {

    /** Private page related static constants */
    private static final By sLoginButton = By.name("login");
    private static final By sUsernameField = By.name("username");
    private static final By sPasswordField = By.name("password");
    private static final By sSubmitButton = By.name("submitLogin");
    private static final By sCancelButton = By.name("cancelLogin");
    private static final By errorPanel = By.cssSelector("simple-snack-bar");

    /**
     * Clicks the login button, enters user name, password and clicks login.
     * 
     * @param user     user id
     * @param password password
     */
    public static void login(String user, String password) {
        loginButton().click();

        fillUsername(user);
        fillPassword(password);

        submitLoginButton().click();
    }

    public static String getLoginMessage() {
        return sDriverWait.until(f -> f.findElement(errorPanel)).getText();
    }

    public static void cancelLogin(String user, String password) {
        loginButton().click();

        fillUsername(user);
        fillPassword(password);

        cancelLoginButton().click();
    }

    /** Private page access */
    private static WebElement loginButton() {
        return sDriver.findElement(sLoginButton);
    }

    private static WebElement cancelLoginButton() {
        return sDriver.findElement(sCancelButton);
    }

    private static void fillUsername(String name) {
        WebElement usernameField = sDriver.findElement(sUsernameField);
        usernameField.sendKeys(name);
    }

    private static void fillPassword(String password) {
        WebElement passwordField = sDriver.findElement(sPasswordField);
        passwordField.sendKeys(password);
    }

    private static WebElement submitLoginButton() {
        return sDriver.findElement(sSubmitButton);
    }
}

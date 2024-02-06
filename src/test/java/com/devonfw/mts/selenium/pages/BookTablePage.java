package com.devonfw.mts.selenium.pages;

import com.devonfw.mts.shared.CustomBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/** Please fill this class with meaningful Page Object content. */
public final class BookTablePage {
	// TODO : eventually move WebDriver to parent class 
	private static WebDriver sDriver = new ChromeDriver();

	public static final String BOOK_TABLE = "http://localhost:8081/bookTable";
	
	private static By submitBookingDateButton = By.xpath("/html/body/div[3]/div[2]/div/owl-dialog-container/owl-date-time-container/div[2]/div/button[2]");
	private static By dateInput = CustomBy.testId("booking-date-input");
	// TODO : implement further locators
	
	private static WebElement getDateField() {
		return sDriver.findElement(dateInput);
	}
	
	private static WebElement getSubmitBookingDateButton() {
		return sDriver.findElement(submitBookingDateButton);
	}
	
	private static void clickBookingDate() {
		getDateField().click();
	}
	
	private static void clickSubmitBookingDate() {
		getSubmitBookingDateButton().click();
	}
	
	// TODO : implement getters and setters as shown above
	
	public static void bookTable(/** think of parameters for booking a table*/) {
		clickBookingDate();
		//TODO : provide implementation for booking of a table by using the setters above
	}
}

package com.project.e2e;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddStopPage {
	private WebDriver driver;

	@FindBy(xpath = "//map[contains(@name,'gmimap')]")
	private List<WebElement> markers;

	@FindBy(id = "add_station")
	private WebElement addButton;

	public AddStopPage(WebDriver driver){
		this.driver = driver;
	}

	public List<WebElement> getMarkers() {
		return markers;
	}

	public WebElement getAddButton() {
		return addButton;
	}
	
	public void ensureIsDisplayed() {
		(new WebDriverWait(driver, 10))
		.until(ExpectedConditions.presenceOfElementLocated(By.id("add_station")));
	}
}

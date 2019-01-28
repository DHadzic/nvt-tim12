package com.project.e2e;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddLinePage {
	private WebDriver driver;

	@FindBy(xpath = "//map[contains(@name,'gmimap')]/area")
	private List<WebElement> markers;
	
	@FindBy(xpath = "//ol[@class='list-group']/li")
	private List<WebElement> listItems;

	@FindBy(id = "add")
	private WebElement addButton;

	@FindBy(id = "show")
	private WebElement showButton;

	@FindBy(id = "name")
	private WebElement nameInput;

	@FindBy(className = "alert-warning")
	private List<WebElement> errorMessages;

	public AddLinePage(WebDriver driver){
		this.driver = driver;
	}

	public List<WebElement> getMarkers() {
		return markers;
	}
	
	public List<WebElement> getListItems() {
		return listItems;
	}

	public WebElement getAddButton() {
		return addButton;
	}

	public WebElement getShowButton() {
		return showButton;
	}

	public WebElement getNameInput() {
		return nameInput;
	}

	public List<WebElement> getErrorMessages() {
		return errorMessages;
	}

	public void ensureIsDisplayed() {
		(new WebDriverWait(driver, 10))
			.until(ExpectedConditions.elementToBeClickable(addButton));
		(new WebDriverWait(driver, 10))
			.until(ExpectedConditions.elementToBeClickable(showButton));
	}
	
	public void ensureErrorIsDisplayed() {
		(new WebDriverWait(driver, 10))
		.until(ExpectedConditions.elementToBeClickable(errorMessages.get(0)));		
	}

}

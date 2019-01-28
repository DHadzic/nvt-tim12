package com.project.e2e;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DeleteLinePage {
	private WebDriver driver;

	@FindBy(xpath = "//div[@class='list-group']/button")
	private List<WebElement> listItems;
	
	@FindBy(id = "delete_line")
	private WebElement deleteButton;
	
	public DeleteLinePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public List<WebElement> getListItems() {
		return listItems;
	}

	public WebElement getDeleteButton() {
		return deleteButton;
	}



	public void ensureIsDisplayed() {
		(new WebDriverWait(driver, 10))
		.until(ExpectedConditions.presenceOfElementLocated(By.id("delete_line")));
	}

}

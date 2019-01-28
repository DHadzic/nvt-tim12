package com.project.e2e;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
	private WebDriver driver;

	@FindBy(id = "main_page_link")
	private WebElement mainPageLink;

	@FindBy(id = "add_stop_link")
	private WebElement addStopLink;
	
	@FindBy(id = "add_line_link")
	private WebElement addLineLink;

	@FindBy(id = "delte_stop_link")
	private WebElement deleteStopLink;

	@FindBy(id = "delete_line_link")
	private WebElement deleteLineLink;

	@FindBy(id = "logout_btn")
	private WebElement logoutBtn;

	@FindBy(id = "login_link")
	private WebElement loginLink;

	@FindBy(id = "trans_type")
	private WebElement typeSelect;

	@FindBy(id = "bus_line_select")
	private WebElement busLineSelect;

	@FindBy(id = "tram_line_select")
	private WebElement tramLineSelect;

	@FindBy(id = "show")
	private WebElement showButton;

	public MainPage(WebDriver driver) {
		this.driver = driver;
	}

	
	public WebDriver getDriver() {
		return driver;
	}


	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}


	public WebElement getMainPageLink() {
		return mainPageLink;
	}


	public void setMainPageLink(WebElement mainPageLink) {
		this.mainPageLink = mainPageLink;
	}


	public WebElement getAddStopLink() {
		return addStopLink;
	}


	public void setAddStopLink(WebElement addStopLink) {
		this.addStopLink = addStopLink;
	}


	public WebElement getAddLineLink() {
		return addLineLink;
	}


	public void setAddLineLink(WebElement addLineLink) {
		this.addLineLink = addLineLink;
	}


	public WebElement getDeleteStopLink() {
		return deleteStopLink;
	}


	public void setDeleteStopLink(WebElement deleteStopLink) {
		this.deleteStopLink = deleteStopLink;
	}


	public WebElement getDeleteLineLink() {
		return deleteLineLink;
	}


	public void setDeleteLineLink(WebElement deleteLineLink) {
		this.deleteLineLink = deleteLineLink;
	}


	public WebElement getLogoutBtn() {
		return logoutBtn;
	}


	public void setLogoutBtn(WebElement logoutBtn) {
		this.logoutBtn = logoutBtn;
	}


	public WebElement getLoginLink() {
		return loginLink;
	}


	public void setLoginLink(WebElement loginLink) {
		this.loginLink = loginLink;
	}

	public WebElement getTypeSelect() {
		return typeSelect;
	}


	public WebElement getBusLineSelect() {
		return busLineSelect;
	}


	public WebElement getTramLineSelect() {
		return tramLineSelect;
	}

	public WebElement getShowButton() {
		return showButton;
	}

	public void ensureIsDisplayed() {
		(new WebDriverWait(driver, 10))
			.until(ExpectedConditions.elementToBeClickable(mainPageLink));
	}
	
	public void ensureAdminLinksAreDisplayed() {
		(new WebDriverWait(driver, 10))
		.until(ExpectedConditions.elementToBeClickable(addLineLink));
		(new WebDriverWait(driver, 10))
		.until(ExpectedConditions.elementToBeClickable(addStopLink));
		(new WebDriverWait(driver, 10))
		.until(ExpectedConditions.elementToBeClickable(deleteLineLink));
		(new WebDriverWait(driver, 10))
		.until(ExpectedConditions.elementToBeClickable(deleteStopLink));		
	}
	
	public void ensureLoginIsDisplayed() {
		(new WebDriverWait(driver, 10))
		.until(ExpectedConditions.elementToBeClickable(loginLink));		
	}

	public void ensureLogoutIsDisplayed() {
		(new WebDriverWait(driver, 10))
		.until(ExpectedConditions.elementToBeClickable(logoutBtn));		
	}
	
	public void ensureSelectsAreDisplayed() {
		(new WebDriverWait(driver, 10))
			.until(ExpectedConditions.presenceOfElementLocated(By.id("trans_type")));
		(new WebDriverWait(driver, 10))
			.until(ExpectedConditions.presenceOfElementLocated(By.id("bus_line_select")));
	}

}

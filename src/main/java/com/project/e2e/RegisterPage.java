package com.project.e2e;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage {
	private WebDriver driver;

	@FindBy(id = "username")
	private WebElement usernameInput;

	@FindBy(id = "password")
	private WebElement passwordInput;

	@FindBy(id = "r-password")
	private WebElement repeatPasswordInput;

	@FindBy(id = "name")
	private WebElement nameInput;

	@FindBy(id = "surname")
	private WebElement surnameInput;

	@FindBy(id = "type")
	private WebElement typeInput;

	@FindBy(id = "birthDate")
	private WebElement dateInput;

	@FindBy(id = "register")
	private WebElement registerButton;

	@FindBy(className = "alert-warning")
	private List<WebElement> errorMessages;
	
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getUsernameInput() {
		return usernameInput;
	}

	public void setUsernameInput(WebElement usernameInput) {
		this.usernameInput = usernameInput;
	}

	public WebElement getPasswordInput() {
		return passwordInput;
	}

	public void setPasswordInput(WebElement passwordInput) {
		this.passwordInput = passwordInput;
	}

	public WebElement getRepeatPasswordInput() {
		return repeatPasswordInput;
	}

	public void setRepeatPasswordInput(WebElement repeatPasswordInput) {
		this.repeatPasswordInput = repeatPasswordInput;
	}

	public WebElement getNameInput() {
		return nameInput;
	}

	public void setNameInput(WebElement nameInput) {
		this.nameInput = nameInput;
	}

	public WebElement getSurnameInput() {
		return surnameInput;
	}

	public void setSurnameInput(WebElement surnameInput) {
		this.surnameInput = surnameInput;
	}

	public WebElement getTypeInput() {
		return typeInput;
	}

	public void setTypeInput(WebElement typeInput) {
		this.typeInput = typeInput;
	}

	public WebElement getDateInput() {
		return dateInput;
	}

	public void setDateInput(WebElement dateInput) {
		this.dateInput = dateInput;
	}

	public WebElement getRegisterButton() {
		return registerButton;
	}

	public void setRegisterButton(WebElement registerButton) {
		this.registerButton = registerButton;
	}

	public List<WebElement> getErrorMessages() {
		return errorMessages;
	}

	public void setErrorMessages(List<WebElement> errorMessages) {
		this.errorMessages = errorMessages;
	}
	
	public void ensureIsDisplayed() {
		(new WebDriverWait(driver, 10))
			.until(ExpectedConditions.elementToBeClickable(registerButton));
		(new WebDriverWait(driver, 10))
			.until(ExpectedConditions.presenceOfElementLocated(By.id("type")));
		(new WebDriverWait(driver, 10))
			.until(ExpectedConditions.presenceOfElementLocated(By.id("birthDate")));
	}
	
	public void ensureAllFormatErrorsAreDisplayed() {
		(new WebDriverWait(driver, 10))
			.until(ExpectedConditions.elementToBeClickable(errorMessages.get(0)));
		(new WebDriverWait(driver, 10))
			.until(ExpectedConditions.elementToBeClickable(errorMessages.get(1)));
		(new WebDriverWait(driver, 10))
			.until(ExpectedConditions.elementToBeClickable(errorMessages.get(2)));
		(new WebDriverWait(driver, 10))
			.until(ExpectedConditions.elementToBeClickable(errorMessages.get(3)));
		(new WebDriverWait(driver, 10))
			.until(ExpectedConditions.elementToBeClickable(errorMessages.get(4)));
	}
	
	public void ensureUsernameErrorIsDisplayed() {
		(new WebDriverWait(driver, 10))
				.until(ExpectedConditions.elementToBeClickable(errorMessages.get(0)));		
	}

}

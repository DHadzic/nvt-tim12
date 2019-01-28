package com.project.e2e;

import static org.testng.AssertJUnit.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.project.constants.PassengerConstants;


public class LinePagesTest {
	private WebDriver browser;
	
	MainPage mainPage;
	LoginPage loginPage;
	RegisterPage registerPage;
	
	@BeforeMethod
	public void setupSelenium() {
		//instantiate browser
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Damir\\Desktop\\chromedriver.exe");
		browser = new ChromeDriver();
		//maximize window
		browser.manage().window().maximize();
		//navigate
		browser.navigate().to("http://localhost:4200");
		
		mainPage = PageFactory.initElements(browser, MainPage.class);
		loginPage = PageFactory.initElements(browser, LoginPage.class);
		registerPage = PageFactory.initElements(browser, RegisterPage.class);
	}
	
	@Test
	public void testLogin() {
		// get menu element
		mainPage.ensureIsDisplayed();
		
		if(mainPage.getLogoutBtn() == null) {
			mainPage.getLogoutBtn().click();
		}
		
		mainPage.ensureLoginIsDisplayed();
		mainPage.getLoginLink().click();
		
		assertEquals("http://localhost:4200/login", browser.getCurrentUrl());
		
		loginPage.ensureIsDisplayed();
		loginPage.getSignUpButton().click();
		
		loginPage.ensureErrorIsDisplayed();
		assertEquals("Wrong username or password.",loginPage.getErrorMessage().getText());
		
		loginPage.getUsernameInput().sendKeys("user");
		loginPage.getPasswordInput().sendKeys("user123");
		loginPage.getSignInButton().click();

		loginPage.ensureErrorIsDisplayed();
		assertEquals("Wrong username or password.",loginPage.getErrorMessage().getText());
		
		loginPage.getPasswordInput().clear();
		loginPage.getPasswordInput().sendKeys("user");
		loginPage.getSignInButton().click();
		
		mainPage.ensureIsDisplayed();
		
		assertEquals("http://localhost:4200/main", browser.getCurrentUrl());			
	}

	@Test
	public void testRegister() {
		// get menu element
		mainPage.ensureIsDisplayed();
		
		if(mainPage.getLogoutBtn() == null) {
			mainPage.getLogoutBtn().click();
		}
		
		mainPage.ensureLoginIsDisplayed();
		mainPage.getLoginLink().click();
		
		assertEquals("http://localhost:4200/login", browser.getCurrentUrl());
		
		loginPage.ensureIsDisplayed();
		loginPage.getSignUpButton().click();
		
		registerPage.ensureIsDisplayed();
		
		assertEquals("http://localhost:4200/login", browser.getCurrentUrl());
		
		registerPage.getUsernameInput().sendKeys(PassengerConstants.NEW_USERNAME_SHORT);
		registerPage.getPasswordInput().sendKeys(PassengerConstants.NEW_PASSWORD_SHORT);
		registerPage.getRepeatPasswordInput().sendKeys(PassengerConstants.NEW_PASSWORD_SHORT);
		registerPage.getNameInput().sendKeys(PassengerConstants.NEW_NAME_SHORT);
		registerPage.getSurnameInput().sendKeys(PassengerConstants.NEW_SURNAME_SHORT);
		registerPage.getTypeInput().sendKeys("REGULAR");
		registerPage.getDateInput().sendKeys("12/12/1880");

		registerPage.getRegisterButton().click();
		registerPage.ensureAllFormatErrorsAreDisplayed();
		
		assertEquals("Username must be between 3 and 20 characters..",registerPage.getErrorMessages().get(0).getText());
		assertEquals("Password must be between 3 and 20 characters..",registerPage.getErrorMessages().get(1).getText());
		assertEquals("Name must be between 3 and 20 characters..",registerPage.getErrorMessages().get(2).getText());
		assertEquals("Surname must be between 3 and 20 characters..",registerPage.getErrorMessages().get(3).getText());
		assertEquals("Must be inbetween Jan 1st 1900 and Jan 1st 2010",registerPage.getErrorMessages().get(4).getText());
		
		registerPage.getUsernameInput().clear();
		registerPage.getPasswordInput().clear();
		registerPage.getRepeatPasswordInput().clear();
		registerPage.getNameInput().clear();
		registerPage.getSurnameInput().clear();

		registerPage.getUsernameInput().sendKeys(PassengerConstants.NEW_USERNAME_LONG);
		registerPage.getPasswordInput().sendKeys(PassengerConstants.NEW_PASSWORD_LONG);
		registerPage.getRepeatPasswordInput().sendKeys(PassengerConstants.NEW_PASSWORD_LONG);
		registerPage.getNameInput().sendKeys(PassengerConstants.NEW_NAME_LONG);
		registerPage.getSurnameInput().sendKeys(PassengerConstants.NEW_SURNAME_LONG);
		registerPage.getTypeInput().sendKeys("REGULAR");
		registerPage.getDateInput().sendKeys("12/12/2280");
		
		registerPage.getRegisterButton().click();
		browser.manage().timeouts().implicitlyWait(1,TimeUnit.SECONDS);
		registerPage.ensureAllFormatErrorsAreDisplayed();
		
		assertEquals("Username must be between 3 and 20 characters..",registerPage.getErrorMessages().get(0).getText());
		assertEquals("Password must be between 3 and 20 characters..",registerPage.getErrorMessages().get(1).getText());
		assertEquals("Name must be between 3 and 20 characters..",registerPage.getErrorMessages().get(2).getText());
		assertEquals("Surname must be between 3 and 20 characters..",registerPage.getErrorMessages().get(3).getText());
		assertEquals("Must be inbetween Jan 1st 1900 and Jan 1st 2010",registerPage.getErrorMessages().get(4).getText());
		
		registerPage.getUsernameInput().clear();
		registerPage.getPasswordInput().clear();
		registerPage.getRepeatPasswordInput().clear();
		registerPage.getNameInput().clear();
		registerPage.getSurnameInput().clear();

		registerPage.getUsernameInput().sendKeys(PassengerConstants.NEW_USERNAME1);
		registerPage.getPasswordInput().sendKeys(PassengerConstants.NEW_PASSWORD);
		registerPage.getRepeatPasswordInput().sendKeys("nahh");
		registerPage.getNameInput().sendKeys(PassengerConstants.NEW_NAME);
		registerPage.getSurnameInput().sendKeys(PassengerConstants.NEW_SURNAME);
		registerPage.getTypeInput().sendKeys("REGULAR");
		registerPage.getDateInput().sendKeys("12/12/1993");
		
		registerPage.getRegisterButton().click();
		browser.manage().timeouts().implicitlyWait(1,TimeUnit.SECONDS);
		registerPage.ensureUsernameErrorIsDisplayed();
		assertEquals("Password repetition incorrect..",registerPage.getErrorMessages().get(0).getText());
		
		registerPage.getUsernameInput().clear();
		registerPage.getRepeatPasswordInput().clear();
		registerPage.getUsernameInput().sendKeys("admin");
		registerPage.getRepeatPasswordInput().sendKeys(PassengerConstants.NEW_PASSWORD);

		registerPage.getRegisterButton().click();
		browser.manage().timeouts().implicitlyWait(1,TimeUnit.SECONDS);
		registerPage.ensureUsernameErrorIsDisplayed();
		assertEquals("Username taken",registerPage.getErrorMessages().get(0).getText());

		registerPage.getUsernameInput().clear();
		registerPage.getUsernameInput().sendKeys(PassengerConstants.NEW_USERNAME1);
		registerPage.getRegisterButton().click();

		mainPage.ensureLoginIsDisplayed();
		assertEquals("http://localhost:4200/main", browser.getCurrentUrl());
	}

	@AfterMethod
	public void closeSelenium() {
		//Shutdown the browser
		browser.close();
	}	

}

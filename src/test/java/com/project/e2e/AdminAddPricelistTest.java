package com.project.e2e;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AdminAddPricelistTest {

private WebDriver browser;
	
	LoginPage loginPage;
	MainPage mainPage;
	
	@BeforeMethod
	public void setupSelenium() {
		//instantiate browser
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Dell\\Desktop\\chromedriver.exe");
		browser = new ChromeDriver();
		//maximize window
		browser.manage().window().maximize();
		//navigate
		browser.navigate().to("http://localhost:4200/login");
		
		loginPage = PageFactory.initElements(browser, LoginPage.class);
		mainPage = PageFactory.initElements(browser, MainPage.class);
	}
	
	@Test
	public void testAddPricelist() throws InterruptedException {
		loginPage.ensureIsDisplayed();
		assertEquals("http://localhost:4200/login", browser.getCurrentUrl());
		
		loginPage.getUsernameInput().sendKeys("admin");
		loginPage.getPasswordInput().sendKeys("admin");
		
		loginPage.getSignInButton().click();
		
		Thread.sleep(2000);
		
		browser.navigate().to("http://localhost:4200/pricelist");
		
	}
	@AfterMethod
	public void closeSelenium() {
		//Shutdown the browser
		browser.close();
	}	
}

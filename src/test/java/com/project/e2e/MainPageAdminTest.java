package com.project.e2e;

import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MainPageAdminTest {
	private WebDriver browser;
	
	MainPage mainPage;
	
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
	}
	
	@Test
	public void testDisplayLine() throws InterruptedException {
		
		mainPage.ensureIsDisplayed();
		assertEquals("http://localhost:4200/main", browser.getCurrentUrl());
		
		mainPage.ensureSelectsAreDisplayed();
		
		mainPage.getTypeSelect().sendKeys("Bus");
		assertTrue(mainPage.getBusLineSelect().isDisplayed());
		
		mainPage.getBusLineSelect().sendKeys("8a");
		
		mainPage.getShowButton().click();
		
		Thread.sleep(2000);
		
		mainPage.getTypeSelect().sendKeys("Tram");
		assertTrue(mainPage.getTramLineSelect().isDisplayed());

		mainPage.getTramLineSelect().sendKeys("8a");

		mainPage.getShowButton().click();
		
		Thread.sleep(2000);
}
	
	@AfterMethod
	public void closeSelenium() {
		//Shutdown the browser
		browser.close();
	}	

}

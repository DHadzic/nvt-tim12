package com.project.e2e;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AdminAddPricelistTest {

private WebDriver browser;
	
	LoginPage loginPage;
	PricelistPage pricelistPage;
	
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
		pricelistPage = PageFactory.initElements(browser, PricelistPage.class);
		
		int numOfPricelists = pricelistPage.getPricelistsContainer().findElements(By.cssSelector("*")).size();
		
		pricelistPage.ensureCreateBtnIsDisplayed();
		pricelistPage.getCreateButton().click();
		
		pricelistPage.ensureInputsAreDisplayed();
		pricelistPage.getInsertPrice1().sendKeys("50");
		pricelistPage.getInsertPrice2().sendKeys("100");
		pricelistPage.getInsertPrice3().sendKeys("1200");
		pricelistPage.getInsertPrice4().sendKeys("12000");
		pricelistPage.getInsertPrice5().sendKeys("52");
		pricelistPage.getInsertPrice6().sendKeys("112");
		pricelistPage.getInsertPrice7().sendKeys("512");
		pricelistPage.getInsertPrice8().sendKeys("1212");
		pricelistPage.getInsertPrice9().sendKeys("62");
		pricelistPage.getInsertPrice10().sendKeys("112");
		pricelistPage.getInsertPrice11().sendKeys("1112");
		pricelistPage.getInsertPrice12().sendKeys("3112");

		pricelistPage.ensureSubmitIsDisplayed();
		pricelistPage.getSubmitCreateBtn().click();
		Thread.sleep(3000);
		int newNumOfPricelists = pricelistPage.getPricelistsContainer().findElements(By.cssSelector("*")).size();
		
		assertTrue(newNumOfPricelists > numOfPricelists);
		
	}
	@AfterMethod
	public void closeSelenium() {
		//Shutdown the browser
		browser.close();
	}	
}

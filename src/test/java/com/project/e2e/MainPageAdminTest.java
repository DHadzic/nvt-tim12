package com.project.e2e;

import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

import java.util.concurrent.TimeUnit;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MainPageAdminTest {
	private WebDriver browser;
	
	MainPage mainPage;
	LoginPage loginPage;
	AddStopPage addStopPage;
	AddLinePage addLinePage;
	DeleteStopPage deleteStopPage;
	DeleteLinePage deleteLinePage;
	
	@BeforeMethod
	public void setupSelenium() {
		//instantiate browser
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Dell\\Desktop\\chromedriver.exe");
		browser = new ChromeDriver();
		//maximize window
		browser.manage().window().maximize();
		//navigate
		browser.navigate().to("http://localhost:4200");
		
		mainPage = PageFactory.initElements(browser, MainPage.class);
		loginPage = PageFactory.initElements(browser, LoginPage.class);
		addStopPage = PageFactory.initElements(browser, AddStopPage.class);
		addLinePage = PageFactory.initElements(browser, AddLinePage.class);
		deleteStopPage = PageFactory.initElements(browser, DeleteStopPage.class);
		deleteLinePage = PageFactory.initElements(browser, DeleteLinePage.class);
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
	
	@Test
	public void testAddPoint() throws InterruptedException {
		mainPage.ensureIsDisplayed();
		assertEquals("http://localhost:4200/main", browser.getCurrentUrl());
		
		mainPage.getLoginLink().click();
		loginPage.ensureIsDisplayed();
		assertEquals("http://localhost:4200/login", browser.getCurrentUrl());
		
		loginPage.getUsernameInput().sendKeys("admin");
		loginPage.getPasswordInput().sendKeys("admin");
		loginPage.getSignInButton().click();
		
		mainPage.ensureAdminLinksAreDisplayed();
		assertEquals("http://localhost:4200/main", browser.getCurrentUrl());
		
		mainPage.getAddStopLink().click();
		
		addStopPage.ensureIsDisplayed();
		assertEquals("http://localhost:4200/addStop", browser.getCurrentUrl());
		
		Actions builder = new Actions(browser);
		builder.moveToElement(addStopPage.getMarkers().get(0), +10, 0).click().build().perform();
		
		Thread.sleep(500);
		
		addStopPage.getAddButton().click();
		
		Thread.sleep(500);

		browser.switchTo().alert().dismiss();
		
		mainPage.ensureIsDisplayed();
		assertEquals("http://localhost:4200/main", browser.getCurrentUrl());
	}

	@Test
	public void testAddLine() throws InterruptedException {
		mainPage.ensureIsDisplayed();
		assertEquals("http://localhost:4200/main", browser.getCurrentUrl());
		
		mainPage.getLoginLink().click();
		loginPage.ensureIsDisplayed();
		assertEquals("http://localhost:4200/login", browser.getCurrentUrl());
		
		loginPage.getUsernameInput().sendKeys("admin");
		loginPage.getPasswordInput().sendKeys("admin");
		loginPage.getSignInButton().click();
		
		mainPage.ensureAdminLinksAreDisplayed();
		assertEquals("http://localhost:4200/main", browser.getCurrentUrl());
		
		mainPage.getAddLineLink().click();
		
		addLinePage.ensureIsDisplayed();
		
		addLinePage.getMarkers().get(0).click();

		Thread.sleep(500);

		assertEquals(1,addLinePage.getListItems().size());
		addLinePage.getMarkers().get(1).click();

		Thread.sleep(500);
		
		assertEquals(2,addLinePage.getListItems().size());
		addLinePage.getShowButton().click();
		
		Thread.sleep(500);

		addLinePage.getShowButton().click();

		Thread.sleep(500);

		addLinePage.getMarkers().get(2).click();

		Thread.sleep(500);

		assertEquals(3,addLinePage.getListItems().size());
		
		addLinePage.getAddButton().click();
		
		addLinePage.ensureErrorIsDisplayed();
		
		assertEquals("Name must be between 1 and 10 characters",addLinePage.getErrorMessages().get(0).getText());
		
		addLinePage.getNameInput().sendKeys("aaaaaaaaaaaa");
		addLinePage.getAddButton().click();
		Thread.sleep(200);
		addLinePage.ensureErrorIsDisplayed();
		assertEquals("Name must be between 1 and 10 characters",addLinePage.getErrorMessages().get(0).getText());
		
		addLinePage.getNameInput().clear();
		addLinePage.getNameInput().sendKeys("8a");
		addLinePage.getAddButton().click();

		Thread.sleep(1000);
		addLinePage.ensureErrorIsDisplayed();
		assertEquals("Line name is taken.. Try another one..",addLinePage.getErrorMessages().get(0).getText());

		addLinePage.getNameInput().clear();
		addLinePage.getNameInput().sendKeys("new-line");
		addLinePage.getAddButton().click();

		Thread.sleep(200);

		browser.switchTo().alert().dismiss();
		
		mainPage.ensureIsDisplayed();
		assertEquals("http://localhost:4200/main", browser.getCurrentUrl());
	}
	
	@Test
	public void testDeleteStop() throws InterruptedException{
		mainPage.ensureIsDisplayed();
		assertEquals("http://localhost:4200/main", browser.getCurrentUrl());
		
		mainPage.getLoginLink().click();
		loginPage.ensureIsDisplayed();
		assertEquals("http://localhost:4200/login", browser.getCurrentUrl());
		
		loginPage.getUsernameInput().sendKeys("admin");
		loginPage.getPasswordInput().sendKeys("admin");
		loginPage.getSignInButton().click();
		
		mainPage.ensureAdminLinksAreDisplayed();
		assertEquals("http://localhost:4200/main", browser.getCurrentUrl());
		
		mainPage.getDeleteStopLink().click();
		
		deleteStopPage.ensureIsDisplayed();
		
		deleteStopPage.getMarkers().get(0).click();
		
		int before = deleteStopPage.getMarkers().size();

		Thread.sleep(500);
		
		deleteStopPage.getDeleteButton().click();
		
		Thread.sleep(500);

		browser.switchTo().alert().dismiss();
		
		mainPage.ensureIsDisplayed();
		assertEquals("http://localhost:4200/main", browser.getCurrentUrl());
		
		mainPage.getDeleteStopLink().click();
		
		deleteStopPage.ensureIsDisplayed();
		assertEquals("http://localhost:4200/deleteStop", browser.getCurrentUrl());

		Thread.sleep(500);
		int after = deleteStopPage.getMarkers().size();
		
		assertEquals(before-1,after);
	}
	
	@Test
	public void testDeleteLine() throws InterruptedException{
		mainPage.ensureIsDisplayed();
		assertEquals("http://localhost:4200/main", browser.getCurrentUrl());
		
		mainPage.getLoginLink().click();
		loginPage.ensureIsDisplayed();
		assertEquals("http://localhost:4200/login", browser.getCurrentUrl());
		
		loginPage.getUsernameInput().sendKeys("admin");
		loginPage.getPasswordInput().sendKeys("admin");
		loginPage.getSignInButton().click();
		
		mainPage.ensureAdminLinksAreDisplayed();
		assertEquals("http://localhost:4200/main", browser.getCurrentUrl());
		
		mainPage.getDeleteLineLink().click();
		
		deleteLinePage.ensureIsDisplayed();
		assertEquals("http://localhost:4200/deleteLine", browser.getCurrentUrl());
	
		int before = deleteLinePage.getListItems().size();
		
		deleteLinePage.getListItems().get(0).click();
		
		Thread.sleep(1000);

		deleteLinePage.getListItems().get(1).click();
		
		Thread.sleep(1000);
		
		deleteLinePage.getDeleteButton().click();

		Thread.sleep(500);

		browser.switchTo().alert().dismiss();
		
		mainPage.ensureIsDisplayed();
		assertEquals("http://localhost:4200/main", browser.getCurrentUrl());
		
		mainPage.getDeleteLineLink().click();
		
		deleteLinePage.ensureIsDisplayed();
		assertEquals("http://localhost:4200/deleteLine", browser.getCurrentUrl());

		Thread.sleep(500);

		int after = deleteLinePage.getListItems().size();
		
		assertEquals(after,before-1);
		
	}
	
	@AfterMethod
	public void closeSelenium() {
		//Shutdown the browser
		browser.close();
	}	

}

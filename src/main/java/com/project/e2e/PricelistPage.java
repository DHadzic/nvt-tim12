package com.project.e2e;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PricelistPage {
	private WebDriver driver;

	@FindBy(id = "create_pricelist")
	private WebElement createButton;

	@FindBy(id = "insert_price_1")
	private WebElement insertPrice1;
	
	@FindBy(id = "insert_price_2")
	private WebElement insertPrice2;
	
	@FindBy(id = "insert_price_3")
	private WebElement insertPrice3;
	
	@FindBy(id = "insert_price_4")
	private WebElement insertPrice4;
	
	@FindBy(id = "insert_price_5")
	private WebElement insertPrice5;
	
	@FindBy(id = "insert_price_6")
	private WebElement insertPrice6;
	
	@FindBy(id = "insert_price_7")
	private WebElement insertPrice7;
	
	@FindBy(id = "insert_price_8")
	private WebElement insertPrice8;
	
	@FindBy(id = "insert_price_9")
	private WebElement insertPrice9;
	
	@FindBy(id = "insert_price_10")
	private WebElement insertPrice10;
	
	@FindBy(id = "insert_price_11")
	private WebElement insertPrice11;
	
	@FindBy(id = "insert_price_12")
	private WebElement insertPrice12;

	@FindBy(id = "create")
	private WebElement submitCreateBtn;
	
	@FindBy(id = "accordion")
	private WebElement pricelistsContainer;
	
	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getCreateButton() {
		return createButton;
	}

	public void setCreateButton(WebElement createButton) {
		this.createButton = createButton;
	}

	public WebElement getInsertPrice1() {
		return insertPrice1;
	}

	public void setInsertPrice1(WebElement insertPrice1) {
		this.insertPrice1 = insertPrice1;
	}

	public WebElement getInsertPrice2() {
		return insertPrice2;
	}

	public void setInsertPrice2(WebElement insertPrice2) {
		this.insertPrice2 = insertPrice2;
	}

	public WebElement getInsertPrice3() {
		return insertPrice3;
	}

	public void setInsertPrice3(WebElement insertPrice3) {
		this.insertPrice3 = insertPrice3;
	}

	public WebElement getInsertPrice4() {
		return insertPrice4;
	}

	public void setInsertPrice4(WebElement insertPrice4) {
		this.insertPrice4 = insertPrice4;
	}

	public WebElement getInsertPrice5() {
		return insertPrice5;
	}

	public void setInsertPrice5(WebElement insertPrice5) {
		this.insertPrice5 = insertPrice5;
	}

	public WebElement getInsertPrice6() {
		return insertPrice6;
	}

	public void setInsertPrice6(WebElement insertPrice6) {
		this.insertPrice6 = insertPrice6;
	}

	public WebElement getInsertPrice7() {
		return insertPrice7;
	}

	public void setInsertPrice7(WebElement insertPrice7) {
		this.insertPrice7 = insertPrice7;
	}

	public WebElement getInsertPrice8() {
		return insertPrice8;
	}

	public void setInsertPrice8(WebElement insertPrice8) {
		this.insertPrice8 = insertPrice8;
	}

	public WebElement getInsertPrice9() {
		return insertPrice9;
	}

	public void setInsertPrice9(WebElement insertPrice9) {
		this.insertPrice9 = insertPrice9;
	}

	public WebElement getInsertPrice10() {
		return insertPrice10;
	}

	public void setInsertPrice10(WebElement insertPrice10) {
		this.insertPrice10 = insertPrice10;
	}

	public WebElement getInsertPrice11() {
		return insertPrice11;
	}

	public void setInsertPrice11(WebElement insertPrice11) {
		this.insertPrice11 = insertPrice11;
	}

	public WebElement getInsertPrice12() {
		return insertPrice12;
	}

	public void setInsertPrice12(WebElement insertPrice12) {
		this.insertPrice12 = insertPrice12;
	}
	
	public WebElement getSubmitCreateBtn() {
		return submitCreateBtn;
	}

	public void setSubmitCreateBtn(WebElement submitCreateBtn) {
		this.submitCreateBtn = submitCreateBtn;
	}

	public WebElement getPricelistsContainer() {
		return pricelistsContainer;
	}

	public void setPricelistsContainer(WebElement pricelistsContainer) {
		this.pricelistsContainer = pricelistsContainer;
	}

	public void ensureCreateBtnIsDisplayed(){
		(new WebDriverWait(driver, 10))
		.until(ExpectedConditions.elementToBeClickable(createButton));
	}
	
	public void ensureInputsAreDisplayed(){
		(new WebDriverWait(driver, 10))
		.until(ExpectedConditions.elementToBeClickable(insertPrice1));
		(new WebDriverWait(driver, 10))
		.until(ExpectedConditions.elementToBeClickable(insertPrice2));
		(new WebDriverWait(driver, 10))
		.until(ExpectedConditions.elementToBeClickable(insertPrice3));
		(new WebDriverWait(driver, 10))
		.until(ExpectedConditions.elementToBeClickable(insertPrice4));
		(new WebDriverWait(driver, 10))
		.until(ExpectedConditions.elementToBeClickable(insertPrice5));
		(new WebDriverWait(driver, 10))
		.until(ExpectedConditions.elementToBeClickable(insertPrice6));
		(new WebDriverWait(driver, 10))
		.until(ExpectedConditions.elementToBeClickable(insertPrice7));
		(new WebDriverWait(driver, 10))
		.until(ExpectedConditions.elementToBeClickable(insertPrice8));
		(new WebDriverWait(driver, 10))
		.until(ExpectedConditions.elementToBeClickable(insertPrice9));
		(new WebDriverWait(driver, 10))
		.until(ExpectedConditions.elementToBeClickable(insertPrice10));
		(new WebDriverWait(driver, 10))
		.until(ExpectedConditions.elementToBeClickable(insertPrice11));
		(new WebDriverWait(driver, 10))
		.until(ExpectedConditions.elementToBeClickable(insertPrice12));
	}
	
	public void ensureSubmitIsDisplayed(){
		(new WebDriverWait(driver, 10))
		.until(ExpectedConditions.elementToBeClickable(submitCreateBtn));
	}
	
	public void ensureContainerIsDisplayed(){
		(new WebDriverWait(driver, 10))
		.until(ExpectedConditions.elementToBeClickable(pricelistsContainer));
	}
	
}

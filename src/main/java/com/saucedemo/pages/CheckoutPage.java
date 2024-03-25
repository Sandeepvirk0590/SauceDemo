package com.saucedemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.saucedemo.base.SauceDemoTestBase;

public class CheckoutPage extends SauceDemoTestBase {

	public CheckoutPage(WebDriver wd) {
		this.wd = wd;
		PageFactory.initElements(wd, this);
	}

	@FindBy(css = "#first-name")
	private WebElement inputFirstName;

	@FindBy(css = "#last-name")
	private WebElement inputLastName;

	@FindBy(css = "#postal-code")
	private WebElement inputPostalCode;

	@FindBy(css = "#continue")
	private WebElement continueButton;

	@FindBy(css = "span.title")
	private WebElement titleOfPage;

	@FindBy(xpath = "//span[text()='Checkout: Overview']")
	private WebElement overviewText;

	@FindBy(xpath = "//div[text()='34.54']")
	private WebElement totalText;

	@FindBy(css = "#finish")
	private WebElement finishButton;

	@FindBy(xpath = "//h2[text()='Thank you for your order!']")
	private WebElement thankYouMessage;

	@FindBy(css = "#back-to-products")
	private WebElement backToProductsButton;

	public void fillOutCheckOutInfoAndContinue(String fName, String lName, String postal) {
		inputFirstName.sendKeys(fName);
		inputLastName.sendKeys(lName);
		inputPostalCode.sendKeys(postal);
		continueButton.click();
	}

	public String getTitleForThePage() {
		return titleOfPage.getText();

	}

	public String getOverviewText() {
		return overviewText.getText();
	}

	public String getTotalText() {
		return totalText.getText();

	}

	public void clickFinishButton() {
		finishButton.click();
	}

	public String getThankYouMessage() {
		return thankYouMessage.getText();
	}

	public InventoryPage clickBackToProducts() {
		backToProductsButton.click();
		return new InventoryPage(wd);

	}
}

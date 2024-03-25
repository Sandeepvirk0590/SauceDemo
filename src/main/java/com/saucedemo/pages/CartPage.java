package com.saucedemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.saucedemo.base.SauceDemoTestBase;

public class CartPage extends SauceDemoTestBase {

	public CartPage(WebDriver wd) {
		this.wd = wd;
		PageFactory.initElements(wd, this);
	}

	@FindBy(xpath = "//div[text()='Sauce Labs Bolt T-Shirt']")
	private WebElement BoltTShirtText;

	@FindBy(xpath = "//div[text()='Test.allTheThings() T-Shirt (Red)']")
	private WebElement redTShirtText;

	@FindBy(css = "#checkout")
	private WebElement checkoutButton;

	public String getTextForBoltTShirt() {
		return BoltTShirtText.getText();
	}

	public String getTextForRedTShirt() {
		return redTShirtText.getText();
	}

	public CheckoutPage clickOnCheckout() {
		checkoutButton.click();
		return new CheckoutPage(wd);
	}

}

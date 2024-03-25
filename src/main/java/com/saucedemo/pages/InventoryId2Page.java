package com.saucedemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.saucedemo.base.SauceDemoTestBase;

public class InventoryId2Page extends SauceDemoTestBase {

	public InventoryId2Page(WebDriver wd) {
		this.wd = wd;
		PageFactory.initElements(wd, this);
	}

	@FindBy(xpath = "//div[text()='Sauce Labs Onesie']")
	private WebElement textForOnsieProduct;

	@FindBy(css = "#add-to-cart-sauce-labs-onesie")
	private WebElement addToCartButton;

	@FindBy(css = "button[id^='remove']")
	private WebElement removeButton;

	@FindBy(id = "back-to-products")
	private WebElement backToProductsButton;

	public String getTextFromInventoryId2Page() {
		return textForOnsieProduct.getText();
	}

	public void clickAddToCartButton() {
		Actions action = new Actions(wd);
		action.click(addToCartButton);
	}

	public String getTextFromRemoveButton() {
		return removeButton.getText();
	}

	public InventoryPage goToBackToProductsPage() {
		backToProductsButton.click();
		return new InventoryPage(wd);
	}

}

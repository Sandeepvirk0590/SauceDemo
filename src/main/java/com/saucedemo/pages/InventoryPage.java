package com.saucedemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.saucedemo.base.SauceDemoTestBase;

public class InventoryPage extends SauceDemoTestBase {

	public InventoryPage(WebDriver wd) {
		this.wd = wd;
		PageFactory.initElements(wd, this);
	}

	@FindBy(css = "span.title")
	private WebElement productText;

	@FindBy(css = "#react-burger-menu-btn")
	private WebElement leftSideButtonOnHeader;

	@FindBy(css = "#logout_sidebar_link")
	private WebElement logout;

	@FindBy(css = "select.product_sort_container")
	private WebElement dropDownList;

	@FindBy(xpath = "//div[text()='7.99']")
	private WebElement priceText;

//	@FindBy(css = "img[alt$='Onesie']")
//	private WebElement onsieImage;

	@FindBy(css = "a.shopping_cart_link")
	private WebElement cartLink;

	@FindBy(css = "#add-to-cart-sauce-labs-onesie")
	private WebElement onsieAddToCartButton;

	@FindBy(css = "#add-to-cart-sauce-labs-bolt-t-shirt")
	private WebElement sauceLabsBoltTShirtAddToCartButton;

	@FindBy(id = "add-to-cart-test.allthethings()-t-shirt-(red)")
	private WebElement redTShirtAddToCartButton;

	public String getTextOnInventoryPage() {
		return productText.getText();
	}

	public void clickOnDropDown(String text) {
		Select select = new Select(dropDownList);
		select.selectByVisibleText(text);
	}

	public String textOfPrice() {
		return priceText.getText();
	}

//	public InventoryId2Page selectOnsieProduct() {
//		Actions action = new Actions(wd);
//		action.click(onsieImage);
//		return new InventoryId2Page(wd);
//	}

	public CartPage openCart() {
		cartLink.click();
		return new CartPage(wd);
	}

	public InventoryId2Page addToCartOnsie() {
		onsieAddToCartButton.click();
		return new InventoryId2Page(wd);
	}

	public void addToCartBoltTShirt() {
		sauceLabsBoltTShirtAddToCartButton.click();
	}

	public void addToCartRedTShirt() {
		redTShirtAddToCartButton.click();
	}

	public void clickOnLeftSideButtonOnHeader() {
		leftSideButtonOnHeader.click();
	}

	public SauceDemoPage clickOnLogout() {
		clickOnLeftSideButtonOnHeader();
		logout.click();
		return new SauceDemoPage(wd);
	}

}

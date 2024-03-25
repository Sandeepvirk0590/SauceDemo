package com.saucedemo.testpages;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.saucedemo.base.SauceDemoTestBase;
import com.saucedemo.pages.CartPage;
import com.saucedemo.pages.CheckoutPage;
import com.saucedemo.pages.InventoryPage;
import com.saucedemo.pages.SauceDemoPage;

public class CheckoutPageTest extends SauceDemoTestBase {

	SauceDemoPage swagLabsPage;
	InventoryPage inventoryPage;
	CartPage cartpage;
	CheckoutPage checkoutPage;

	@BeforeMethod
	public void launch() {
		intialisation();
		swagLabsPage = new SauceDemoPage(wd);
	}

	@Test(priority = 1)
	public void validateIfContinueButtonForCheckoutIsWorking() {
		inventoryPage = swagLabsPage.SubmitLogin("visual_user", "secret_sauce");
		cartpage = inventoryPage.openCart();
		checkoutPage = cartpage.clickOnCheckout();
		checkoutPage.fillOutCheckOutInfoAndContinue("Sandy", "Kaur", "L6T1K8");
		Assert.assertEquals(checkoutPage.getOverviewText(), "Checkout: Overview", "Continue Button is not working!");

	}

	@Test(dependsOnMethods = "validateIfContinueButtonForCheckoutIsWorking")
	public void validateTotalPaymentOnPage() {
		inventoryPage = swagLabsPage.SubmitLogin("visual_user", "secret_sauce");
		inventoryPage.clickOnDropDown("Price (low to high)");
		inventoryPage.addToCartBoltTShirt();
		inventoryPage.addToCartRedTShirt();
		cartpage = inventoryPage.openCart();
		checkoutPage = cartpage.clickOnCheckout();
		checkoutPage.fillOutCheckOutInfoAndContinue("Sandy", "Kaur", "L6T1K8");
		Assert.assertEquals(checkoutPage.getTotalText(), "Total: $34.54", "Incorrect Amount!");
	}

	@Test(dependsOnMethods = "validateTotalPaymentOnPage()")
	public void validateFinishButton() {
		inventoryPage = swagLabsPage.SubmitLogin("visual_user", "secret_sauce");
		inventoryPage.clickOnDropDown("Price (low to high)");
		cartpage = inventoryPage.openCart();
		checkoutPage = cartpage.clickOnCheckout();
		checkoutPage.fillOutCheckOutInfoAndContinue("Sandy", "Kaur", "L6T1K8");
		checkoutPage.clickFinishButton();
		Assert.assertEquals(checkoutPage.getThankYouMessage(), "Thank you for your order!", "Purchase not completed!");

	}

	@Test(dependsOnMethods = "validateFinishButton")
	public void validateBackToProductsButtonIsWorking() {
		inventoryPage = swagLabsPage.SubmitLogin("visual_user", "secret_sauce");
		cartpage = inventoryPage.openCart();
		checkoutPage = cartpage.clickOnCheckout();
		checkoutPage.fillOutCheckOutInfoAndContinue("Sandy", "Kaur", "L6T1K8");
		checkoutPage.clickFinishButton();
		inventoryPage = checkoutPage.clickBackToProducts();
		Assert.assertEquals(inventoryPage.getTextOnInventoryPage(), "Products");

	}

	@AfterMethod
	public void quitBrowser() {
		tearDown();
	}

}

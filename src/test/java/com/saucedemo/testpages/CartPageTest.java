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

public class CartPageTest extends SauceDemoTestBase {

	SauceDemoPage swagLabsPage;
	InventoryPage inventoryPage;
	CartPage cartpage;
	CheckoutPage checkoutPage;

	@BeforeMethod
	public void launch() {
		intialisation();
		swagLabsPage = new SauceDemoPage(wd);
	}

	@Test
	public void validateIfRedAndBoltTShirtAvailableInCart() {
		inventoryPage = swagLabsPage.SubmitLogin("visual_user", "secret_sauce");
		inventoryPage.addToCartBoltTShirt();
		inventoryPage.addToCartRedTShirt();
		cartpage = inventoryPage.openCart();
		Assert.assertEquals(cartpage.getTextForBoltTShirt(), "Sauce Labs Bolt T-Shirt",
				"Bolt T-Shirt Is not added to cart");
		Assert.assertEquals(cartpage.getTextForRedTShirt(), "Test.allTheThings() T-Shirt (Red)",
				"Red T-Shirt Is not added to cart");
	}

	@Test(dependsOnMethods = "validateIfRedAndBoltTShirtAvailableInCart")
	public void validateCheckoutButtonOnCartPage() {
		inventoryPage = swagLabsPage.SubmitLogin("visual_user", "secret_sauce");
		cartpage = inventoryPage.openCart();
		checkoutPage = cartpage.clickOnCheckout();
		Assert.assertEquals(checkoutPage.getTitleForThePage(), "Checkout: Your Information",
				"Checkout Button Is not working!");
	}

	@AfterMethod
	public void quitBrowser() {
		tearDown();
	}

}

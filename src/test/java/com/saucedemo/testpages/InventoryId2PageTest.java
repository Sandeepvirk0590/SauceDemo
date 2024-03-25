package com.saucedemo.testpages;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.saucedemo.base.SauceDemoTestBase;
import com.saucedemo.pages.InventoryId2Page;
import com.saucedemo.pages.InventoryPage;
import com.saucedemo.pages.SauceDemoPage;

public class InventoryId2PageTest extends SauceDemoTestBase {

	SauceDemoPage swagLabsPage;
	InventoryPage inventoryPage;
	InventoryId2Page inventoryId2Page;

	@BeforeMethod
	public void launch() {
		intialisation();
		swagLabsPage = new SauceDemoPage(wd);
	}

	@Test
	public void validateIfAddToCartButtonWorks() {
		inventoryPage = swagLabsPage.SubmitLogin("standard_user", "secret_sauce");
		inventoryId2Page = inventoryPage.addToCartOnsie();
		inventoryId2Page.clickAddToCartButton();
		String removeText = inventoryId2Page.getTextFromRemoveButton();
		Assert.assertEquals(removeText, "Remove", "product is not added to the cart");
	}
	
	@Test 
	public void validateIfUserCanGoBackToProductPage() {
		inventoryPage = swagLabsPage.SubmitLogin("standard_user", "secret_sauce");
		inventoryId2Page = inventoryPage.addToCartOnsie();
		inventoryId2Page.clickAddToCartButton();
		inventoryId2Page.goToBackToProductsPage();
		Assert.assertEquals(inventoryPage.getTextOnInventoryPage(), "Products", "Back To products button not working");
	}

	@AfterMethod
	public void quitBrowser() {
		tearDown();
	}

}

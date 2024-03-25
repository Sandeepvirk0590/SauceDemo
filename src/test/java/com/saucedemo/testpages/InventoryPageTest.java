package com.saucedemo.testpages;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.saucedemo.base.SauceDemoTestBase;
import com.saucedemo.pages.InventoryId2Page;
import com.saucedemo.pages.InventoryPage;
import com.saucedemo.pages.SauceDemoPage;

public class InventoryPageTest extends SauceDemoTestBase {

	SauceDemoPage swagLabsPage;
	InventoryPage inventoryPage;
	InventoryId2Page inventoryId2Page;

	@BeforeMethod
	public void launch() {
		intialisation();
		swagLabsPage = new SauceDemoPage(wd);
	}

	@Test
	public void validateIfUserCanSetPriceFromLowToHigh() {
		inventoryPage = swagLabsPage.SubmitLogin("visual_user", "secret_sauce");
		inventoryPage.clickOnDropDown("Price (low to high)");
		Assert.assertEquals(inventoryPage.textOfPrice(), "$7.99", "List is not sorted from Low to high");
	}

	@Test
	public void validateLogoutButton() {
		inventoryPage = swagLabsPage.SubmitLogin("visual_user", "secret_sauce");
		inventoryPage.clickOnLeftSideButtonOnHeader();
		swagLabsPage = inventoryPage.clickOnLogout();
		Assert.assertEquals(swagLabsPage.getLogo(), "Swag Labs", "LogoutButtonNotWorking");
	}

	@AfterMethod
	public void quitBrowser() {
		tearDown();
	}

}

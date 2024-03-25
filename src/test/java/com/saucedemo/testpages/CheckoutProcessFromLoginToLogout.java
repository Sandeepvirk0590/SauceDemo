package com.saucedemo.testpages;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.saucedemo.Utils.ExcelUtils;
import com.saucedemo.base.SauceDemoTestBase;
import com.saucedemo.pages.CartPage;
import com.saucedemo.pages.CheckoutPage;
import com.saucedemo.pages.InventoryPage;
import com.saucedemo.pages.SauceDemoPage;

public class CheckoutProcessFromLoginToLogout extends SauceDemoTestBase {

	SauceDemoPage swagLabsPage;
	InventoryPage inventoryPage;
	CartPage cartpage;
	CheckoutPage checkoutStepOnePage;

	@BeforeMethod
	public void launch() {
		intialisation();
		swagLabsPage = new SauceDemoPage(wd);
	}

	@Test(dataProvider = "UserCredentials")
	public void validateIfContinueButtonForCheckoutIsWorking(String userName, String password) {
		inventoryPage = swagLabsPage.SubmitLogin(userName, password);
		Assert.assertEquals(inventoryPage.getTextOnInventoryPage(), "Products", "Login Failed!");
		inventoryPage.clickOnDropDown("Price (low to high)");
		Assert.assertEquals(inventoryPage.textOfPrice(), "$7.99", "List is not sorted from Low to high");
		inventoryPage.addToCartBoltTShirt();
		inventoryPage.addToCartRedTShirt();
		cartpage = inventoryPage.openCart();
		Assert.assertEquals(cartpage.getTextForBoltTShirt(), "Sauce Labs Bolt T-Shirt",
				"Bolt T-Shirt Is not added to cart");
		Assert.assertEquals(cartpage.getTextForRedTShirt(), "Test.allTheThings() T-Shirt (Red)",
				"Red T-Shirt Is not added to cart");
		checkoutStepOnePage = cartpage.clickOnCheckout();
		Assert.assertEquals(checkoutStepOnePage.getTitleForThePage(), "Checkout: Your Information",
				"Checkout Button Is not working!");
	checkoutStepOnePage.fillOutCheckOutInfoAndContinue("Sandy", "virk", "L6T1K8");
//		Assert.assertEquals(checkoutPage.getTotalText(), "$34.54", "Incorrect Total!");
//		checkoutPage.clickFinishButton();
//		Assert.assertEquals("", false);

	}

	@DataProvider(name = "UserCredentials")
	public String[][] getDataFromExcel() throws Exception {
		String filename = "C:\\Users\\gurja\\OneDrive\\Desktop\\PIVOT\\AcceptedUsernames.xlsx";
		String sheetName = "Sheet1";
		int rowCount = ExcelUtils.getRowCount(filename, sheetName);
		int cellCount = ExcelUtils.getCellCount(filename, sheetName, rowCount);

		String[][] virtualSheet = new String[rowCount][cellCount];

		for (int i = 1; i <= rowCount; i++) {
			for (int j = 0; j < cellCount; j++) {
				virtualSheet[i - 1][j] = ExcelUtils.getCellData(filename, sheetName, i, j + 1);
			}

		}
		return virtualSheet;
	}

	@AfterMethod
	public void quitBrowser() {
		tearDown();
	}

}

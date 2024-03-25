package com.saucedemo.testpages;

import org.testng.Assert;
//import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.saucedemo.Utils.ExcelUtils;
import com.saucedemo.base.SauceDemoTestBase;
import com.saucedemo.pages.InventoryPage;
import com.saucedemo.pages.SauceDemoPage;

public class SauceDemoPageTest extends SauceDemoTestBase {

	SauceDemoPage swagLabsPage;
	InventoryPage inventoryPage;

	@BeforeMethod
	public void launch() {
		intialisation();
		swagLabsPage = new SauceDemoPage(wd);
	}

	@Test(dataProvider = "UserCredentials")
	public void validateIfUserCanLogin(String userName, String password) {
		String pageLogo = swagLabsPage.getLogo();
		Assert.assertEquals(pageLogo, "Swag Labs", "HomePage not found");
		inventoryPage = swagLabsPage.SubmitLogin(userName, password);
		Assert.assertEquals(inventoryPage.getTextOnInventoryPage(), "Products", "Login Failed!");
	}

	@Test
	public void validateLockOutUserCanNotLogin() {
		swagLabsPage.SubmitLogin("locked_out_user", "secret_sauce");
		Assert.assertEquals(swagLabsPage.getErrorMessageForLockedOutUser(),
				"Epic sadface: Sorry, this user has been locked out.");
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

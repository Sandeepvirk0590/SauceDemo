package com.saucedemo.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.saucedemo.base.SauceDemoTestBase;

public class SauceDemoPage extends SauceDemoTestBase {

	public SauceDemoPage(WebDriver wd) {
		this.wd = wd;
		PageFactory.initElements(wd, this);
	}

	@FindBy(css = "div.login_logo")
	private WebElement logo;

	@FindBy(css = "input[placeholder='Username']")
	private WebElement inputUserName;

	@FindBy(css = "input[type='Password']")
	private WebElement inputPassword;

	@FindBy(css = "input.submit-button")
	private WebElement loginButton;

	@FindBy(xpath = "//h3[text()='Epic sadface: Sorry, this user has been locked out.']")
	private WebElement errorMessage;

	public String getLogo() {
		return logo.getText();

	}

	private void enterUsername(String username) {
		inputUserName.sendKeys(username);
	}

	private void enterPassword(String password) {
		wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		inputPassword.sendKeys(password);
	}

	public InventoryPage SubmitLogin(String email, String password) {
		enterUsername(email);
		enterPassword(password);
		loginButton.click();
		return new InventoryPage(wd);
	}

	public String getErrorMessageForLockedOutUser() {
		return errorMessage.getText();

	}

}

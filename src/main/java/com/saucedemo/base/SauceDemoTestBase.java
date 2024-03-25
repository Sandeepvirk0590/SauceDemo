package com.saucedemo.base;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;

public class SauceDemoTestBase {

	public static WebDriver wd;
	public WebDriverWait wait;
	public static Logger logger;

	private final String DEFAULT_BROWSER = "CHROME";
	private final String URL = "https://www.saucedemo.com/";

	@BeforeClass
	public void loggerSetup() {
		logger = Logger.getLogger(SauceDemoTestBase.class);
		PropertyConfigurator.configure("log4j.properties");
		BasicConfigurator.configure();
		logger.setLevel(Level.INFO);
	}

	public void intialisation() {

		switch (DEFAULT_BROWSER) {
		case "CHROME":
			wd = new ChromeDriver();
			break;
		case "EDGE":
			wd = new EdgeDriver();
			break;
		case "FIREFOX":
			wd = new FirefoxDriver();
			break;
		default:
			throw new IllegalArgumentException();
		}

		wait = new WebDriverWait(wd, Duration.ofSeconds(10));
		wd.get(URL);
		wd.manage().window().maximize();
		wd.manage().deleteAllCookies();
//		wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	}

	public static void screenShotOfFailedTestCases(String testMethodName) {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

		File screenShotFile = ((TakesScreenshot) wd).getScreenshotAs(OutputType.FILE);

		try {
			FileHandler.copy(screenShotFile,
					new File("FailedTestCasesScreenShot" + "--->" + testMethodName + "--->" + timeStamp + ".jpg"));
		} catch (IOException e) {
			System.out.println("The IO Exception is: " + e);
			e.printStackTrace();
		}

	}

	public void tearDown() {

		wd.quit();

	}
}

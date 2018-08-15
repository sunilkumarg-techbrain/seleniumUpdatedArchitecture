package com.google.gmodule.googlesearch;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.google.gmodule.googlesearch.reader.ExcelFileReader;
import com.google.gmodule.googlesearch.reader.NotepadReader;
import com.google.gmodule.googlesearch.utils.GoogleUtils;

import io.qameta.allure.Allure;

/**
 * BaseTest class to store method including testSetup, driver declarations etc.
 * 
 * @author Sunil kumar G
 *
 */
public class GoogleSearchBaseTest {
	protected NotepadReader notepadReader;
	protected ExcelFileReader excelFileReader;
	private String url = "https://www.google.com";
	private WebDriver driver;
	protected GoogleSearchBasePage googleSearchBasePage;
	protected GoogleUtils googleUtils;

	/**
	 * constructor to initialize base test page
	 */
	public GoogleSearchBaseTest() {
		super();
		notepadReader = new NotepadReader();
		googleUtils = new GoogleUtils();
		excelFileReader = new ExcelFileReader();
		googleSearchBasePage = new GoogleSearchBasePage();
	}

	/**
	 * method to capture screenshot by passing the driver and method name as
	 * parameters
	 * 
	 * @param driver
	 * @param methodName
	 */
	public void captureScreenshot(WebDriver driver, String methodName) {
		System.out.println("Capturing screenshot of failure .....");
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		InputStream is = null;
		try {
			is = new FileInputStream(screenshot);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Allure.addAttachment("SCREENSHOT-" + methodName, is);
		System.out.println("Attached screenshot of failure to allure report .....");

	}

	/**
	 * method to setup the driver
	 */
	@BeforeMethod
	public void testSetUp(ITestContext testContext) {
		System.setProperty("webdriver.chrome.driver",
				googleSearchBasePage.getBaseProjectPath() + "src\\test\\resources\\drivers\\chromedriver.exe");
		this.driver = new ChromeDriver();
		googleSearchBasePage.setWebDriver(this.driver);
		testContext.setAttribute("WebDriver", this.driver);
		launchUrl();
		getDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	/**
	 * method to quit the browser session
	 */
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	/**
	 * method to get driver
	 */
	public WebDriver getDriver() {
		return this.driver;
	}

	/**
	 * method to launch url
	 * 
	 * @param driver
	 * @param url
	 */
	public void launchUrl() {
		try {
			getDriver().navigate().to(url);
			getDriver().manage().window().maximize();
		} catch (Exception e) {
			googleSearchBasePage.takeScreenshot();
		}
	}

}

package com.google.gmodule.googlesearch;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.google.gmodule.googlesearch.reader.ExcelFileReader;
import com.google.gmodule.googlesearch.reader.NotepadReader;
import com.google.gmodule.googlesearch.utils.GoogleUtils;

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

	/**
	 * method to setup the driver
	 */
	@BeforeMethod
	public void testSetUp() {
		System.setProperty("webdriver.chrome.driver",
				googleSearchBasePage.getBaseProjectPath() + "src\\test\\resources\\drivers\\chromedriver.exe");
		this.driver = new ChromeDriver();
		googleSearchBasePage.setWebDriver(this.driver);
		launchUrl();
		getDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	
}

package com.google.gmodule.googlesearch;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * BrowserFactory to create required brwoser session to be used in the test
 * cases
 * 
 * @author Sunil kumar
 *
 */
public class BrowserFactory {

	protected GoogleSearchBasePage googleSearchBasePage;
	protected static final String CHROME = "chrome";
	protected static final String FIREFOX = "firefox";
	protected static final String WEB_DRIVER_CHROME_DRIVER = "webdriver.chrome.driver";
	protected static final String FALSE = "false";
	protected static final String TRUE = "true";
	private WebDriver driver = null;
	private DesiredCapabilities capChrome;

	/**
	 * constructor to initialize browser factory elements
	 * 
	 * @param driver
	 */
	public BrowserFactory() {
		googleSearchBasePage = new GoogleSearchBasePage();
		capChrome = null;
	}

	/**
	 * method to get browser driver
	 * 
	 * @param browserType
	 * @param remote
	 * @param hubUrl
	 * @return
	 */
	public WebDriver getBrowserDriver(BrowserType browserType, String remote, String hubUrl) {

		switch (browserType) {
		case FIREFOX:
			break;

		case CHROME:
			if (remote.equalsIgnoreCase(FALSE)) {
				this.driver = new ChromeDriver();
			} else if (remote.equalsIgnoreCase(TRUE)) {
				System.setProperty(WEB_DRIVER_CHROME_DRIVER,
						googleSearchBasePage.getBaseProjectPath() + "src\\test\\resources\\drivers\\chromedriver.exe");
				capChrome = DesiredCapabilities.chrome();
				try {
					this.driver = new RemoteWebDriver(new URL(hubUrl), capChrome);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			}
			break;

		default:
			if (remote.equalsIgnoreCase(FALSE)) {
				this.driver = new ChromeDriver();
			} else if (remote.equalsIgnoreCase(TRUE)) {
				System.setProperty(WEB_DRIVER_CHROME_DRIVER,
						googleSearchBasePage.getBaseProjectPath() + "src\\test\\resources\\drivers\\chromedriver.exe");
				capChrome = DesiredCapabilities.chrome();
				try {
					this.driver = new RemoteWebDriver(new URL(hubUrl), capChrome);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			}

			break;

		}

		return driver;
	}
}
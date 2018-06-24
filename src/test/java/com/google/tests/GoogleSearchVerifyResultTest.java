package com.google.tests;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.config.Config;
import com.google.pages.GoogleResultPage;
import com.google.pages.GoogleSearchPage;
import com.google.reader.ExcelFileReader;
import com.google.utils.Libraries;
import com.google.utils.SeleneseActionMethods;

public class GoogleSearchVerifyResultTest extends BaseTest {
    
	
	public GoogleSearchVerifyResultTest() {
		super();
	}
	
	
	
	private WebDriver driver; 
	String appURL = "http://google.com";


	@BeforeMethod
	public void testSetUp() throws IOException {
		driver = new FirefoxDriver();
		SeleneseActionMethods.launchUrl(driver,appURL);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);				

	}
	
	
	@DataProvider(name ="GoogleTextSearchData")
    public Object[][] GoogleTextSearchData() throws Exception{
         Object[][] testObjArray = ExcelFileReader.getTableArray(Config.getExcelReaderPath(),"Sheet1");
         return (testObjArray);
 		}
	
	
	
	@Test(dataProvider="GoogleTextSearchData")
	public void searchTextAndVerifyResultTest(String sNo, String searchText) throws InterruptedException, IOException {
		
		System.out.println("SNo "+ sNo );
		System.out.println("searchText "+ searchText );
		
		GoogleSearchPage googleSearchPage = new GoogleSearchPage();
		GoogleResultPage googleResultPage = new GoogleResultPage();
		
		
		System.out.println("GoogleSearchPage.getTitle(driver) " + GoogleSearchPage.getTitle(driver));
		Assert.assertTrue(GoogleSearchPage.getTitle(driver).contains("Google"));
		googleSearchPage.enterGoogleSearchText(driver, searchText);
		googleSearchPage.submitGoogleSearch(driver);
		List<WebElement> googleSearchResultList = googleResultPage.getGoogleSearchResultsList(driver);
	    for (WebElement singleSearchResult : googleSearchResultList)
	    {   
	    	
	    	Libraries.highlight(driver, singleSearchResult);
	        System.out.println(singleSearchResult.getAttribute("href"));
			Assert.assertTrue(singleSearchResult.getAttribute("href").contains("https://") | singleSearchResult.getAttribute("href").contains("http://"));

	    }	
	}
	
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
}
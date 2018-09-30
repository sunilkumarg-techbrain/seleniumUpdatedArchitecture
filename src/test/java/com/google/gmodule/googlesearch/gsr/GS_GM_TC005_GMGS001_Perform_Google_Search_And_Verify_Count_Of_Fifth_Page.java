package com.google.gmodule.googlesearch.gsr;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.google.gmodule.googlesearch.TestListener;

import io.qameta.allure.Description;
import io.qameta.allure.Story;

/**
 * Test to verify google search result. The test data is from
 * GoogleSearchVeifyResult.xlsx
 * 
 * @author G Sunil kumar
 *
 */
@Listeners(TestListener.class)
public class GS_GM_TC005_GMGS001_Perform_Google_Search_And_Verify_Count_Of_Fifth_Page
		extends GoogleSearchResultBaseTest {
	private int httpsCount = 0;
	private Object[][] hashMapObjArray;

	/**
	 * constructor to initialize google search verify result test
	 */
	public GS_GM_TC005_GMGS001_Perform_Google_Search_And_Verify_Count_Of_Fifth_Page() {
		super();
	}

	/**
	 * method to initialize pages required for the test
	 */
	@BeforeMethod(alwaysRun = true)
	public void pageSetup() {
		googleSearchPage = new GoogleSearchPage(getDriver());
		googleResultPage = new GoogleResultPage(getDriver());
	}

	/**
	 * data provider method to extract data from
	 * GS_GM_TC005_GMGS001_Perform_Google_Search_And_Verify_Count_Of_Fifth_Page.txt
	 * 
	 * @return
	 */
	@DataProvider(name = "perform_Google_Search_And_Verify_Result_Data")
	public Object[][] perform_Google_Search_And_Verify_Result_Data() {
		hashMapObjArray = notepadReader.createHashMapObj(this.getClass(), "dataset1");
		return hashMapObjArray;
	}

	@Test(groups = { "beforeBatchRegression" }, dataProvider = "perform_Google_Search_And_Verify_Result_Data")
	@Description("Test Description - Perform Google Search And Verify Count Of Fifth Page")
	@Story("GS_GM_TC005_GMGS001")
	public void searchTextAndVerifyResultTest(HashMap<String, String> hashMapObj) {
		System.out.println("googleSearchPage.getTitle() " + googleSearchPage.getTitle());
		Assert.assertTrue(googleSearchPage.getTitle().contains("Google"));
		googleSearchPage.enterGoogleSearchText(hashMapObj.get("searchText"));
		googleSearchPage.submitGoogleSearch();

		googleSearchPage.clickPage5Button();
		List<WebElement> googleSearchResultList = googleResultPage.getGoogleSearchResultsList();
		httpsCount = 0;
		for (WebElement singleSearchResult : googleSearchResultList) {
			googleSearchPage.highlight(singleSearchResult);
			httpsCount++;
			System.out.println(singleSearchResult.getAttribute("href"));
			Assert.assertTrue(singleSearchResult.getAttribute("href").contains("https://")
					| singleSearchResult.getAttribute("href").contains("http://"));
		}
		System.out.println("Count of https ::" + httpsCount);

	}

}
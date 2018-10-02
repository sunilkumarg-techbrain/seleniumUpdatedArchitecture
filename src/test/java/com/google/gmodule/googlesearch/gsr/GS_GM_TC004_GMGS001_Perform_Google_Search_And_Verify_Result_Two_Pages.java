package com.google.gmodule.googlesearch.gsr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.google.gmodule.googlesearch.TestListener;
import com.google.gmodule.googlesearch.reader.NotepadFileReader;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

/**
 * Test to verify google search result. The test data is from
 * GoogleSearchVeifyResult.xlsx
 * 
 * @author G Sunil kumar
 *
 */
@Listeners(TestListener.class)
public class GS_GM_TC004_GMGS001_Perform_Google_Search_And_Verify_Result_Two_Pages extends GoogleSearchResultBaseTest {

	List<WebElement> googleSearchResultList;
	private Object[][] hashMapObjArray;

	/**
	 * constructor to initialize google search verify result test
	 */
	public GS_GM_TC004_GMGS001_Perform_Google_Search_And_Verify_Result_Two_Pages() {
		super();
		googleSearchResultList = new ArrayList<WebElement>();
	}

	/**
	 * method to initialize pages required for the test
	 */
	@BeforeMethod(alwaysRun = true)
	public void pageSetup() {
		googleSearchPage = new GoogleSearchPage(getDriver());
		googleResultPage = new GoogleResultPage(getDriver());
	}

	@DataProvider(name = "perform_Google_Search_And_Verify_Result_Data")
	public Object[][] perform_Google_Search_And_Verify_Result_Data() {
		notepadReader =(NotepadFileReader) readerFactory.getReader(TXT__FILE);
		hashMapObjArray = notepadReader.createHashMapObj(this.getClass(), DATA_SET_1);
		return hashMapObjArray;
	}

	@Test(groups = { "beforeBatchRegression" },priority = 0, description = "Perform Google Search And Verify Result Two Pages", dataProvider = "perform_Google_Search_And_Verify_Result_Data")
	@Severity(SeverityLevel.NORMAL)
	@Description("Test Description - Perform Google Search And Verify Result Two Pages")
	@Story("GS_GM_TC004_GMGS001")
	public void searchTextAndVerifyResultTest(HashMap<String, String> hashMapObj) {
		System.out.println("googleSearchPage.getTitle() " + googleSearchPage.getTitle());
		Assert.assertTrue(googleSearchPage.getTitle().contains(GOOGLE_PAGE_TITLE));
		googleSearchPage.enterGoogleSearchText(hashMapObj.get(GOOGLE_PAGE_SEARCH_TEXT));
		googleSearchPage.submitGoogleSearch();

		googleSearchResultList = googleResultPage.getGoogleSearchResultsList();

		for (WebElement singleSearchResult : googleSearchResultList) {
			googleSearchPage.highlight(singleSearchResult);
			System.out.println(singleSearchResult.getAttribute("href"));
			Assert.assertTrue(singleSearchResult.getAttribute("href").contains("https://")
					| singleSearchResult.getAttribute("href").contains("http://"));
		}
		googleSearchPage.clickPage2Button();
		googleSearchResultList.clear();

		googleSearchResultList = googleResultPage.getGoogleSearchResultsList();

		for (WebElement singleSearchResult : googleSearchResultList) {
			googleSearchPage.highlight(singleSearchResult);
			System.out.println(singleSearchResult.getAttribute(HREF));
			Assert.assertTrue(singleSearchResult.getAttribute(HREF).contains(HTTPS)
					| singleSearchResult.getAttribute(HREF).contains(HTTP));
		}

	}

}
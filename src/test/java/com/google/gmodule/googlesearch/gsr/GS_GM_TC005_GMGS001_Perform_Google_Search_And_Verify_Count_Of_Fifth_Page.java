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
		notepadReader =(NotepadFileReader) readerFactory.getReader(TXT__FILE);
		hashMapObjArray = notepadReader.createHashMapObj(this.getClass(), DATA_SET_1);
		return hashMapObjArray;
	}

	@Test(groups = { "beforeBatchRegression" }, priority = 0, description = "Perform Google Search And Verify Count Of Fifth Page", dataProvider = "perform_Google_Search_And_Verify_Result_Data")
	@Severity(SeverityLevel.NORMAL)
	@Description("Test Description - Perform Google Search And Verify Count Of Fifth Page")
	@Story("GS_GM_TC005_GMGS001")
	public void searchTextAndVerifyResultTest(HashMap<String, String> hashMapObj) {
		System.out.println("Test Case - " + getClass().getSimpleName()
				+ " with Thread Id:- " + Thread.currentThread().getId());
		System.out.println("googleSearchPage.getTitle() " + googleSearchPage.getTitle());
		Assert.assertTrue(googleSearchPage.getTitle().contains(GOOGLE_PAGE_TITLE));
		googleSearchPage.enterGoogleSearchText(hashMapObj.get(GOOGLE_PAGE_SEARCH_TEXT));
		googleSearchPage.submitGoogleSearch();
		googleSearchPage.clickPage5Button();
		List<WebElement> googleSearchResultList = googleResultPage.getGoogleSearchResultsList();
		httpsCount = 0;
		for (WebElement singleSearchResult : googleSearchResultList) {
			httpsCount++;
			googleSearchPage.highlight(singleSearchResult);
			System.out.println(singleSearchResult.getAttribute(HREF));
			Assert.assertTrue(singleSearchResult.getAttribute(HREF).contains(HTTPS)
					| singleSearchResult.getAttribute(HREF).contains(HTTP));
		}
		System.out.println("Count of https ::" + httpsCount);
	}

}
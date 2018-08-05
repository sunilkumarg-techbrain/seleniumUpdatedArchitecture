package com.google.gmodule.googlesearch.gsr;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.google.gmodule.googlesearch.GoogleSearchBasePage;

public class GoogleSearchPage extends GoogleSearchBasePage {

	private final String GOOGLE_SEARCH_PAGE_GOOGLE_TEXT_BOX = "//*[@id='lst-ib']";
	private final String GOOGLE_SEARCH_PAGE_GOOGLE_SEARCH_BUTTON = "//*[@id='tsf']/div[2]/div[3]/center/input[1]";
	private final String GOOGLE_SEARCH_PAGE_AREA_OUTSIDE_SEARCH_BOX = "//*[@id='body']/center";
	private final String GOOGLE_SEARCH_PAGE_RESULT_STATS = "//*[@id='rso']//h3/a";

	@FindBy(xpath = GOOGLE_SEARCH_PAGE_GOOGLE_TEXT_BOX)
	WebElement googleTextBox;
	@FindBy(xpath = GOOGLE_SEARCH_PAGE_GOOGLE_SEARCH_BUTTON)
	WebElement googleSearchButton;
	@FindBy(xpath = GOOGLE_SEARCH_PAGE_AREA_OUTSIDE_SEARCH_BOX)
	WebElement areaOutsideSearchBox;
	@FindBy(xpath = GOOGLE_SEARCH_PAGE_RESULT_STATS)
	WebElement resultStats;
	@FindBy(xpath = GOOGLE_SEARCH_PAGE_GOOGLE_TEXT_BOX)
	List<WebElement> googleTextBoxList;


	public GoogleSearchPage(WebDriver driver) {
		super();
		setWebDriver(driver);
		PageFactory.initElements(driver, this);
	}

	/**
	 * Method to enter google search text
	 * 
	 * @param driver
	 * @param searchText
	 * @return
	 */
	public GoogleSearchPage enterGoogleSearchText(String searchText) {
		System.out.println("googleTextBoxList.size() "+ googleTextBoxList.size());
		if (isElementPresent(googleTextBox)) {
			googleTextBox.clear();
			googleTextBox.sendKeys(searchText + "\n");
		}
		return this;
	}

	/**
	 * method to submit google search
	 * 
	 * @param driver
	 * @return
	 */
	public GoogleSearchPage submitGoogleSearch() {
		if (isElementPresent(googleTextBox)) {
			googleTextBox.submit();
		}
		return this;
	}

	public String getTitle() {
		return getWebDriver().getTitle();
	}

}

package com.google.pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.google.utils.Libraries;

public class GoogleSearchPage extends BasePage {

	
	private static By GoogleSearchPage_googleTextBox = By.name("q");
	private static By GoogleSearchPage_googleSearchButton = By.xpath("//*[@id='tsf']/div[2]/div[3]/center/input[1]");
	private static By GoogleSearchPage_areaOutsideSearchBox = By.xpath("//*[@id='body']/center");
	private static By GoogleSearchPage_resultStats = By.xpath("//*[@id='rso']//h3/a");
	
	public GoogleSearchPage enterGoogleSearchText(WebDriver driver, String searchText) throws InterruptedException, IOException {
		
		
		try {
		if (driver.findElements(GoogleSearchPage_googleTextBox).size()!=0) {
		    Libraries.highlight(driver, driver.findElement(GoogleSearchPage_googleTextBox));

		    driver.findElement(GoogleSearchPage_googleTextBox).clear();
			driver.findElement(GoogleSearchPage_googleTextBox).sendKeys(searchText+ "\n");
			
		}

		if (driver.findElements(GoogleSearchPage_areaOutsideSearchBox).size()!=0) {
		    Libraries.highlight(driver, driver.findElement(GoogleSearchPage_areaOutsideSearchBox));
			driver.findElement(GoogleSearchPage_areaOutsideSearchBox).click();

		}	
		
		} catch (Exception e){
			Libraries.takeScreenshot(driver);
		}
		
		
		    return this;
	}
	
	public GoogleSearchPage submitGoogleSearch(WebDriver driver) throws IOException {
		
     try { 
		
		if (driver.findElements(GoogleSearchPage_googleTextBox).size()!=0) {
		    Libraries.highlight(driver, driver.findElement(GoogleSearchPage_googleTextBox));
		    driver.findElement(GoogleSearchPage_googleTextBox).submit();
		
		}
		
		
	} catch (Exception e){
		Libraries.takeScreenshot(driver);
	}

		
		return this;
		}
	
	
	
	
	public static String getTitle(WebDriver driver){
		
		return driver.getTitle();

	}
	
}

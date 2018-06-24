package com.google.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.utils.Libraries;

public class GoogleResultPage extends BasePage {

	private static By GoogleResultPage_resultStats = By.xpath("//*[@id='rso']//h3/a");

	
	
	
	public List<WebElement> getGoogleSearchResultsList(WebDriver driver) throws InterruptedException {
		
		    WebElement myDynamicElement = (new WebDriverWait(driver, 10))
		              .until(ExpectedConditions.presenceOfElementLocated(GoogleResultPage_resultStats));

		    List<WebElement> googleResultWebElementList = driver.findElements(GoogleResultPage_resultStats);
		    return googleResultWebElementList;
	}
	
}

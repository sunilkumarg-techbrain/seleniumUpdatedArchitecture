package com.google.utils;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SeleneseActionMethods {

		public static WebElement element = null; 
		
		public static void launchUrl(WebDriver driver,String appURL) throws IOException{
		
			try { 
			
			driver.navigate().to(appURL);
			driver.manage().window().maximize();
		
			} catch (Exception e) {
				
				Libraries.takeScreenshot(driver);
				
			}
 			
			}
	}


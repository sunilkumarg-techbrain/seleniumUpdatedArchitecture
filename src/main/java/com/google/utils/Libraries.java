package com.google.utils;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.google.config.Config;

public class Libraries {


	
	
   public static void highlight(WebDriver driver, WebElement element) {	

	  JavascriptExecutor js = (JavascriptExecutor) driver;
      js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 4px solid blue;');", element);

   }
	

   public static void takeScreenshot(WebDriver driver) throws IOException {
	
     File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
     FileUtils.copyFile(scrFile, new File(Config.getBaseProjectPath()+"src\\test\\resources\\Screenshots\\screeenshot_"+getCurrentTimestamp()+ ".png" ));

   }

   
   public static String getCurrentTimestamp() {
	   

	    final SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");

	        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	        System.out.println(timestamp);
	        System.out.println(sdf.format(timestamp));
	   
	   
	   return sdf.format(timestamp);
	   
   }
   
	
}

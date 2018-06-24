package com.google.config;

public class Config {

	
	private static String baseProjectPath = System.getProperty("user.dir");
	
	public static String getBaseProjectPath() {
		String baseProjectPath = System.getProperty("user.dir")+ "\\";
		return baseProjectPath;
	}
	

	public static String getExcelReaderPath() {
		String excelReaderPath = getBaseProjectPath() +"src\\test\\resources\\TestData\\GoogleSearchVerfiyResult.xlsx";
		return excelReaderPath;
	}

	
}

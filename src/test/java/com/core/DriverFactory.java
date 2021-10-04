package com.core;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {
	
	public static WebDriver driver = null; 
	
	public static WebDriver getDriver() {
		
		if (driver == null) {
			System.setProperty("webdriver.chrome.driver", 
					"/home/antonio/dev/drivers/chromedriver");				
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
		return driver;
	}
	
	public static void killDriver() {
		if (driver !=null) {
			driver.quit();
			driver = null;
		}
	}
}

package com.core;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {
	
	public static WebDriver driver = null; 
	
	public static WebDriver getDriver() {
		
		if (driver == null) {
			
			String browser = GlobaProperty.getProperty("webdriver.browser");
			String path = GlobaProperty.getProperty("webdriver.path");
			
			if (browser.equals("chrome")) {
				System.setProperty("webdriver.chrome.driver", path + "chromedriver");				
				driver = new ChromeDriver();
			}
			
			if (browser.equals("chromeheadless")) {
				System.setProperty("webdriver.chrome.driver", path + "chromedriver");
				
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--headless");
				options.addArguments("--window-size=1200x960");
				
				driver = new ChromeDriver(options);
			}
			
			if (browser.equals("firefox")) {
				System.setProperty("webdriver.gecko.driver", path + "geckodriver");
				driver = new FirefoxDriver();
			}
			
			if (browser.equals("firefoxheadless")) {
				System.setProperty("webdriver.gecko.driver", path + "geckodriver");
				
				FirefoxOptions options = new FirefoxOptions();
				options.addArguments("--headless");
				options.addArguments("--window-size=1200x960");
				
				driver = new FirefoxDriver(options);
			}
			
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

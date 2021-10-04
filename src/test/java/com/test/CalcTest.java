package com.test;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CalcTest {

	private WebDriver driver;
	private WebElement tfNumber1;
	private WebElement tfNumber2;		
	private WebElement tfTotal;
	
	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", 
				"/home/antonio/dev/drivers/chromedriver");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://antoniotrindade.com.br/treinoautomacao/desafiosoma.html");
		
		mapWebElements();
	}
	
	private void mapWebElements() {
		tfNumber1 = driver.findElement(By.id("number1"));
		tfNumber2 = driver.findElement(By.id("number2"));		
		tfTotal = driver.findElement(By.id("total"));
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
	
	@Test
	public void testSum() throws InterruptedException {
		Float val1 = 10.5f;
		Float val2 = 5.4f;
		Float vlTotal = val1 + val2;
			
		WebElement btnSum = driver.findElement(By.id("somar"));
		
		tfNumber1.sendKeys(Float.toString(val1));
		tfNumber2.sendKeys(Float.toString(val2));
		
		btnSum.click();
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions
				.textToBePresentInElementValue(tfTotal, String.valueOf(vlTotal)));
			
		assertEquals(String.valueOf(vlTotal), tfTotal.getAttribute("value"));
	}
	
	@Test
	public void testSub() {
		Float val1 = 10.5f;
		Float val2 = 5.4f;
		Float vlTotal = val1 - val2;
				
		WebElement btnSub = driver.findElement(By.id("subtrair"));
		
		tfNumber1.sendKeys(Float.toString(val1));
		tfNumber2.sendKeys(Float.toString(val2));
		
		btnSub.click();
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions
				.textToBePresentInElementValue(tfTotal, String.valueOf(vlTotal)));
			
		assertEquals(String.valueOf(vlTotal), tfTotal.getAttribute("value"));
	}
}

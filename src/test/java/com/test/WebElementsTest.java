package com.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class WebElementsTest {
	private WebDriver driver;
	
	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", 
				"/home/antonio/dev/drivers/chromedriver");
				//"c:\\driver\\chromedriver.exe"
		driver = new ChromeDriver();
		driver.get("http://antoniotrindade.com.br/treinoautomacao/elementsweb.html");
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void testValidationName() throws InterruptedException {
		WebElement textFieldBox1 = driver.findElement(By.name("txtbox1"));
				
		textFieldBox1.sendKeys("Antônio");
		
		assertEquals("Antônio",	textFieldBox1.getAttribute("value"));
	}
	
	@Test
	public void testValidateTextFieldsDisabled() {
		WebElement textFieldBox1 = driver.findElement(By.name("txtbox1"));
		WebElement textFieldBox2 = driver.findElement(By.name("txtbox2"));
		
		assertTrue(textFieldBox1.isEnabled());
		assertFalse(textFieldBox2.isEnabled());
	}
}

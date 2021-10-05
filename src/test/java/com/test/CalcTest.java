package com.test;

import static com.core.DriverFactory.getDriver;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.core.BaseTest;

public class CalcTest extends BaseTest {

	private WebElement tfNumber1;
	private WebElement tfNumber2;		
	private WebElement tfTotal;
	
	@Before
	public void setUp() throws Exception {
		getDriver().get("http://antoniotrindade.com.br/treinoautomacao/desafiosoma.html");
		mapWebElements();
	}
	
	private void mapWebElements() {
		tfNumber1 = getDriver().findElement(By.id("number1"));
		tfNumber2 = getDriver().findElement(By.id("number2"));		
		tfTotal = getDriver().findElement(By.id("total"));
	}
	
	@Test
	public void testSum() throws InterruptedException {
		Float val1 = 10.5f;
		Float val2 = 5.4f;
		Float vlTotal = val1 + val2;
			
		WebElement btnSum = getDriver().findElement(By.id("somar"));
		
		tfNumber1.sendKeys(Float.toString(val1));
		tfNumber2.sendKeys(Float.toString(val2));
		
		btnSum.click();
		
		WebDriverWait wait = new WebDriverWait(getDriver(), 10);
		wait.until(ExpectedConditions
				.textToBePresentInElementValue(tfTotal, String.valueOf(vlTotal)));
			
		assertEquals(String.valueOf(vlTotal), tfTotal.getAttribute("value"));
	}
	
	@Test
	public void testSub() {
		Float val1 = 10.5f;
		Float val2 = 5.4f;
		Float vlTotal = val1 - val2;
				
		WebElement btnSub = getDriver().findElement(By.id("subtrair"));
		
		tfNumber1.sendKeys(Float.toString(val1));
		tfNumber2.sendKeys(Float.toString(val2));
		
		btnSub.click();
		
		WebDriverWait wait = new WebDriverWait(getDriver(), 10);
		wait.until(ExpectedConditions
				.textToBePresentInElementValue(tfTotal, String.valueOf(vlTotal)));
			
		assertEquals(String.valueOf(vlTotal), tfTotal.getAttribute("value"));
	}
}

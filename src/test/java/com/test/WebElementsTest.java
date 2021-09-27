package com.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;


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
	public void testValidationName(){
		WebElement textFieldBox1 = driver.findElement(By.name("txtbox1"));
				
		textFieldBox1.sendKeys("Antônio");
		
		assertEquals("Antônio",	textFieldBox1.getAttribute("value"));
	}
	
	@Test
	public void testValidateTextFieldsDisabled() {
		WebElement textFieldBox1 = driver.findElement(By.name("txtbox1"));
		WebElement textFieldBox2 = driver.findElement(By.xpath("//input[@name='txtbox2']"));
		
		assertTrue(textFieldBox1.isEnabled());
		assertFalse(textFieldBox2.isEnabled());
	}
	
	@Test
	public void testValidateRadioButton() throws InterruptedException {
		List<WebElement> radios = driver.findElements(By.name("radioGroup1"));
		
		assertEquals("O tamanho não está de acordo!", 4, radios.size());
		
		for(WebElement el: radios) {
			if (el.getAttribute("value").equals("Radio Button 3 selecionado")) {
				el.click();
			}
		}
		
		assertTrue("Posição 3 deveria estar selecionada!", radios.get(2).isSelected());
		assertFalse("Posição 4 não deveria estar selecionada!", radios.get(3).isSelected());
		assertFalse("Posição 1 não deveria estar selecionada!", radios.get(0).isSelected());
		assertFalse("Posição 2 não deveria estar selecionada", radios.get(1).isSelected());
	}
	
	@Test
	public void testValidateCheckBox() throws InterruptedException {
		List<WebElement> listChecks = driver.findElements(By.name("chkbox"));
		
		assertEquals("Tamanho deveria ser 4!", 4, listChecks.size());
		
		for(WebElement el: listChecks) {
			if ((el.getAttribute("value").equals("Check Box 3 selecionado")) ||
					(el.getAttribute("value").equals("Check Box 4 selecionado"))) {
				el.click();
			}			
		}
		assertTrue("Posição 3 deveria estar selecionada!", listChecks.get(2).isSelected());
		assertTrue("Posiçao 4 deveria estar selecionada!", listChecks.get(3).isSelected());
	}
	
	@Test
	public void testValidateSingleSelect() {
		WebElement dropSingle = driver.findElement(By.name("dropdownlist"));
		Select selectSingle = new Select(dropSingle);
		
		selectSingle.selectByIndex(0);
		assertEquals("Item 1", selectSingle.getFirstSelectedOption().getText());
		
		selectSingle.selectByVisibleText("Item 7");		
		assertEquals("Item 7", selectSingle.getFirstSelectedOption().getText());
	}
	
	
	
	
}

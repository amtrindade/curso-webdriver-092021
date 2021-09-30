package com.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import com.inter.NegativeInterface;
import com.inter.PositiveInterface;

@FixMethodOrder(MethodSorters.DEFAULT)
public class WebElementsTest {
	private WebDriver driver;
	
	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", 
				"/home/antonio/dev/drivers/chromedriver");
				//"c:\\driver\\chromedriver.exe"
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://antoniotrindade.com.br/treinoautomacao/elementsweb.html");
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	
	@Test
	@Category(PositiveInterface.class)
	public void testValidationName(){
		WebElement textFieldBox1 = driver.findElement(By.name("txtbox1"));
				
		textFieldBox1.sendKeys("Antônio");
		
		assertEquals("Antônio",	textFieldBox1.getAttribute("value"));
	}
	
	@Test
	@Category(PositiveInterface.class)
	public void testValidateTextFieldsDisabled() {
		WebElement textFieldBox1 = driver.findElement(By.name("txtbox1"));
		WebElement textFieldBox2 = driver.findElement(By.xpath("//input[@name='txtbox2']"));
		
		assertTrue(textFieldBox1.isEnabled());
		assertFalse(textFieldBox2.isEnabled());
	}
	
	@Test
	@Category(NegativeInterface.class)
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
	@Category(NegativeInterface.class)
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
	@Category(NegativeInterface.class)
	public void testValidateSingleSelect() {
		WebElement dropSingle = driver.findElement(By.name("dropdownlist"));
		Select selectSingle = new Select(dropSingle);
		
		selectSingle.selectByIndex(0);
		assertEquals("Item 1", selectSingle.getFirstSelectedOption().getText());
		
		selectSingle.selectByVisibleText("Item 7");		
		assertEquals("Item 7", selectSingle.getFirstSelectedOption().getText());
	}
	
	@Test
	@Category(NegativeInterface.class)
	public void testValidateMultiSelect() throws InterruptedException {
		WebElement dropMulti = driver.findElement(By.name("multiselectdropdown"));
		Select selectMulti = new Select(dropMulti);
		
		selectMulti.selectByVisibleText("Item 5");
		selectMulti.selectByVisibleText("Item 8");
		selectMulti.selectByVisibleText("Item 9");
		
		assertEquals("Deveriam ter 3 elementos!", 3, selectMulti.getAllSelectedOptions().size());
		
		assertEquals("Item 5", selectMulti.getAllSelectedOptions().get(0).getText());
		assertEquals("Item 8", selectMulti.getAllSelectedOptions().get(1).getText());
		assertEquals("Item 9", selectMulti.getAllSelectedOptions().get(2).getText());
		
		
		selectMulti.deselectByVisibleText("Item 8");
		
		assertEquals("Deveriam ter 2 elementos!", 2, selectMulti.getAllSelectedOptions().size());

		assertEquals("Item 5", selectMulti.getAllSelectedOptions().get(0).getText());
		assertEquals("Item 9", selectMulti.getAllSelectedOptions().get(1).getText());
		
		selectMulti.deselectAll();
	}
	
	@Ignore("Desabilitado teste devido ao bug xpt-345")
	@Category(NegativeInterface.class)
	@Test
	public void testValidateIFrames() throws InterruptedException {
		
		driver.switchTo().frame("iframe_b");	
		
		List<WebElement> btnAllow = driver.findElements(By.cssSelector("a.cc-btn.cc-ALLOW"));
		assertTrue(btnAllow.get(1).isDisplayed());
		btnAllow.get(1).click();
		
		driver.switchTo().defaultContent();
		
		driver.switchTo().frame("iframe_d");
		
		WebElement btnMenu = driver.findElement(By.cssSelector("nav > button"));
		btnMenu.click();
		
		WebElement tfSelenium = driver.findElement(By.cssSelector("#main_navbar > div > span > input"));
		tfSelenium.sendKeys("Antônio");
		assertEquals("Antônio", tfSelenium.getAttribute("value"));
	}
	
	@Test	
	public void testValidateAlerts() {
		WebElement btnAlert = driver.findElement(By.name("alertbtn"));
		btnAlert.click();
		
		Alert alert = driver.switchTo().alert();
		
		assertEquals("Eu sou um alerta!", alert.getText());
		
		alert.accept();
		
		WebElement btnConfirm = driver.findElement(By.name("confirmbtn"));
		btnConfirm.click();
		
		Alert alert2 = driver.switchTo().alert();
		assertEquals("Pressione um botão!", alert2.getText());
		alert2.dismiss();
	}
	
}

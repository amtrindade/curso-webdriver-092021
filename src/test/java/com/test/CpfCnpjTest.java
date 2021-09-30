package com.test;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class CpfCnpjTest {
	
	private WebDriver driver;
	
	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", 
				"/home/antonio/dev/drivers/chromedriver");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
	
	@Test
	public void testValidateCpfWithMask() throws InterruptedException {
		driver.get("https://www.geradordecpf.org/");
		
		WebElement checkMask = driver.findElement(By.id("cbPontos"));
		checkMask.click();
		
		WebElement btnGenerate = driver.findElement(By.id("btn-gerar-cpf"));
		btnGenerate.click();
		
		WebElement textFieldCpf = driver.findElement(By.id("numero"));
		
		String cpf = textFieldCpf.getAttribute("value");
		assertTrue("Expressão não valida", cpf.matches("^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$"));
	}
	
	@Test
	public void testValidateCpfWhithoutMask() {
		driver.get("https://www.geradordecpf.org/");
		
		WebElement btnGenerate = driver.findElement(By.id("btn-gerar-cpf"));
		btnGenerate.click();
		
		WebElement textFieldCpf = driver.findElement(By.id("numero"));
		
		String cpf = textFieldCpf.getAttribute("value");
		System.out.println(cpf);
		
		assertTrue("Expressão não valida", cpf.matches("^\\d{11}$"));
	}
	
	@Test
	public void testValidateCnpjWithMask() throws InterruptedException {
		driver.get("https://www.4devs.com.br/gerador_de_cnpj");
		
		WebElement checkYes = driver.findElement(By.id("pontuacao_sim"));
		if (!checkYes.isSelected()) {
			checkYes.click();
		}
		
		WebElement btnGenereateCnpj = driver.findElement(By.id("bt_gerar_cnpj"));
		btnGenereateCnpj.click();
		
		//TODO refazer esperas
		Thread.sleep(1000);
		
		WebElement labelCnpj = driver.findElement(By.id("texto_cnpj"));
		String cnpj = labelCnpj.getText();
		System.out.println(cnpj);
		
		assertTrue(cnpj.matches("^\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}$"));
	}
	
	@Test
	public void testValidateCnpjWhithoutMask() throws InterruptedException {
		driver.get("https://www.4devs.com.br/gerador_de_cnpj");
		
		WebElement checkNo = driver.findElement(By.id("pontuacao_nao"));
		if (!checkNo.isSelected()) {
			checkNo.click();
		}
		
		WebElement btnGenereateCnpj = driver.findElement(By.id("bt_gerar_cnpj"));
		btnGenereateCnpj.click();
		
		//TODO refazer esperas
		Thread.sleep(1000);
		
		WebElement labelCnpj = driver.findElement(By.id("texto_cnpj"));
		String cnpj = labelCnpj.getText();
		System.out.println(cnpj);
		
		assertTrue(cnpj.matches("^\\d{14}$"));
	}
}

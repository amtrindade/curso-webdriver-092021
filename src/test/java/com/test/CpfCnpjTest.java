package com.test;

import static com.core.DriverFactory.getDriver;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.core.BaseTest;

public class CpfCnpjTest extends BaseTest {
			
	@Test
	public void testValidateCpfWithMask() throws InterruptedException {
		getDriver().get("https://www.geradordecpf.org/");
		
		WebElement checkMask = getDriver().findElement(By.id("cbPontos"));
		checkMask.click();
		
		WebElement btnGenerate = getDriver().findElement(By.id("btn-gerar-cpf"));
		btnGenerate.click();
		
		WebElement textFieldCpf = getDriver().findElement(By.id("numero"));
		
		String cpf = textFieldCpf.getAttribute("value");
		assertTrue("Expressão não valida", cpf.matches("^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$"));
	}
	
	@Test
	public void testValidateCpfWhithoutMask() {
		getDriver().get("https://www.geradordecpf.org/");
		
		WebElement btnGenerate = getDriver().findElement(By.id("btn-gerar-cpf"));
		btnGenerate.click();
		
		WebElement textFieldCpf = getDriver().findElement(By.id("numero"));
		
		String cpf = textFieldCpf.getAttribute("value");
		System.out.println(cpf);
		
		assertTrue("Expressão não valida", cpf.matches("^\\d{11}$"));
	}
	
	@Test
	public void testValidateCnpjWithMask() throws InterruptedException {
		getDriver().get("https://www.4devs.com.br/gerador_de_cnpj");
		
		WebElement checkYes = getDriver().findElement(By.id("pontuacao_sim"));
		if (!checkYes.isSelected()) {
			checkYes.click();
		}
		
		WebElement btnGenereateCnpj = getDriver().findElement(By.id("bt_gerar_cnpj"));
		btnGenereateCnpj.click();
		
		
		WebElement labelCnpj = getDriver().findElement(By.id("texto_cnpj"));
		
		WebDriverWait wait = new WebDriverWait(getDriver(), 10);
		wait.until(ExpectedConditions
				.invisibilityOfElementWithText(By.id("texto_cnpj"), "Gerando..."));
		
		
		String cnpj = labelCnpj.getText();
		System.out.println(cnpj);
		
		assertTrue(cnpj.matches("^\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}$"));
	}
	
	@Test
	public void testValidateCnpjWhithoutMask() throws InterruptedException {
		getDriver().get("https://www.4devs.com.br/gerador_de_cnpj");
		
		WebElement checkNo = getDriver().findElement(By.id("pontuacao_nao"));
		if (!checkNo.isSelected()) {
			checkNo.click();
		}
		
		WebElement btnGenereateCnpj = getDriver().findElement(By.id("bt_gerar_cnpj"));
		btnGenereateCnpj.click();
		
		WebDriverWait wait = new WebDriverWait(getDriver(), 10);
		wait.until(ExpectedConditions
				.invisibilityOfElementWithText(By.id("texto_cnpj"), "Gerando..."));
		
		WebElement labelCnpj = getDriver().findElement(By.id("texto_cnpj"));
		String cnpj = labelCnpj.getText();
		System.out.println(cnpj);
		
		assertTrue(cnpj.matches("^\\d{14}$"));
	}
}

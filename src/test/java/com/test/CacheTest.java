package com.test;

import static com.core.DriverFactory.getDriver;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.core.BaseTest;
import com.inter.NegativeInterface;
import com.inter.PositiveInterface;

public class CacheTest extends BaseTest{
	
	@Before
	public void setUp() throws Exception {		
		getDriver().get("http://antoniotrindade.com.br/treinoautomacao");
	}
		
	@Test
	@Category({PositiveInterface.class, NegativeInterface.class})
	public void testNavigationCache() {
		getDriver().navigate().forward();
		
		WebElement btnCalculadora = getDriver().findElement(By.linkText("Calculadora"));
		btnCalculadora.click();
		
		assertEquals("Desafio Automação Cálculos", getDriver().getTitle());
		
		WebElement btnTable = getDriver().findElement(By.linkText("Localizar Table"));
		btnTable.click();
		
		assertEquals("Trabalhando com tables", getDriver().getTitle());
		
		getDriver().navigate().back();
		assertEquals("Desafio Automação Cálculos", getDriver().getTitle());
		
		getDriver().navigate().back();
		assertEquals("Treino Automação de Testes", getDriver().getTitle());
		
		getDriver().navigate().forward();
		assertEquals("Desafio Automação Cálculos", getDriver().getTitle());
		
		getDriver().navigate().forward();
		assertEquals("Trabalhando com tables", getDriver().getTitle());
		
		getDriver().navigate().forward();
		assertEquals("Trabalhando com tables", getDriver().getTitle());
	}
	
	
}

package com.test;

import static com.core.DriverFactory.getDriver;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.core.BaseTest;
import com.inter.PositiveInterface;

public class NavigationTabsTest extends BaseTest {
	
	@Before
	public void setUp() throws Exception {	
		getDriver().get("http://antoniotrindade.com.br/treinoautomacao");
	}
	
	@Category(PositiveInterface.class)
	@Test
	public void testNavigationTabs() throws InterruptedException {
		assertEquals("Treino Automação de Testes", getDriver().getTitle());
		
		WebElement linkCPF = getDriver().findElement(By.linkText("Gerador de CPF"));
		linkCPF.click();
		
		ArrayList<String> tabs = new ArrayList<String>(getDriver().getWindowHandles());
		assertEquals(2, tabs.size());
		getDriver().switchTo().window(tabs.get(1));
		
		assertEquals("Gerador de CPF", getDriver().getTitle());
		
		getDriver().switchTo().window(tabs.get(0));
		assertEquals("Treino Automação de Testes", getDriver().getTitle());
		
		WebElement linkJQuery = getDriver().findElement(By.linkText("Drag and Drop JQuery"));
		linkJQuery.click();
		
		tabs = new ArrayList<String>(getDriver().getWindowHandles());
		assertEquals(3, tabs.size());
		
		getDriver().switchTo().window(tabs.get(2));
		
		assertEquals("jQuery UI Droppable - Default functionality", getDriver().getTitle());
		
		getDriver().switchTo().window(tabs.get(1));
		assertEquals("Gerador de CPF", getDriver().getTitle());
		
		getDriver().switchTo().window(tabs.get(0));
		assertEquals("Treino Automação de Testes", getDriver().getTitle());
	}
}

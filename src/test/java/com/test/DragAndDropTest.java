package com.test;

import static com.core.DriverFactory.getDriver;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.core.BaseTest;

public class DragAndDropTest extends BaseTest {

	@Before
	public void setUp() throws Exception {
		getDriver().get("https://jqueryui.com/resources/demos/droppable/default.html");
	}

	
	@Test
	public void testDragAndDrop() throws InterruptedException, IOException {
		File scrnShot = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrnShot, new File("target/before.jpg"));
		
		WebElement origin = getDriver().findElement(By.id("draggable"));
		assertEquals("Drag me to my target", origin.getText());
		
		WebElement destiny = getDriver().findElement(By.id("droppable"));
		assertEquals("Drop here", destiny.getText());
		
		new Actions(getDriver()).dragAndDrop(origin, destiny).perform();
		
		scrnShot = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrnShot, new File("target/after.jpg"));
		
		assertEquals("Dropped!", destiny.getText());
	}

}

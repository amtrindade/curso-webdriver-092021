package com.core;

import static com.core.DriverFactory.getDriver;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public abstract class BaseTest {
	
	@Rule
	public TestName testName = new TestName();
	
	@After
	public void tearDown() throws IOException {
		File scrnShot = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrnShot, new File("target"+ File.separator + testName.getMethodName()+ ".jpg"));
		
		DriverFactory.killDriver();
	}

}

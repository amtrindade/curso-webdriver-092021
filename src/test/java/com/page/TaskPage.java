package com.page;

import static com.core.DriverFactory.getDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TaskPage {

	public TaskPage clearFilter() {
		WebElement btnClear = getDriver().findElement(By.id("scheduleList_doClear"));
		btnClear.click();
		return this;
	}

	public TaskPage filter(String search) {
		WebElement tfSearch = getDriver().findElement(By.id("genericFilter"));
		tfSearch.sendKeys(search);
		
		WebElement btnSearch = getDriver().findElement(By.id("scheduleList_doSearch"));
		btnSearch.click();
		
		return this;
		
	}

	public Boolean isExistTaskWithAgent(String agent) {
		WebElement line = getDriver().findElement(By.xpath("//a[contains(text(),'"+ agent +"')] "));
		return line.isDisplayed();
	}

}

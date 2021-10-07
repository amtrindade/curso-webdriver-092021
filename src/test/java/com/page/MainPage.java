package com.page;

import static com.core.DriverFactory.getDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MainPage {

	public MainPage clickAvatar() {
		WebElement avatar = getDriver()
				.findElement(By.cssSelector(".topbar-widget.profile-widget"));
		avatar.click();
		return this;
	}

	public String getUserLogger() {
		WebElement labelName = getDriver()
				.findElement(By.xpath("//span[@class='text-login']/.."));
		return labelName.getText();
	}

	public TaskPage accessMenuTask() {
		WebElement menuTask = getDriver().findElement(By.linkText("Tarefa"));
		menuTask.click();
		return new TaskPage();
	}

}

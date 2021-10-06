package com.page;

import static com.core.DriverFactory.getDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginPage {

	public LoginPage open() {
		getDriver().get("https://center.umov.me/");
		return this;		
	}

	public LoginPage setEnvironment(String environment) {
		WebElement tfEnvironment = getDriver().findElement(By.id("workspace"));
		tfEnvironment.sendKeys(environment);
		return this;
	}

	public LoginPage setUsername(String username) {
		WebElement tfUsername = getDriver().findElement(By.id("username"));
		tfUsername.sendKeys(username);
		return this;
	}

	public LoginPage setPassword(String pass) {
		WebElement tfPass = getDriver().findElement(By.id("password"));
		tfPass.sendKeys(pass);
		return this;
	}

	public MainPage submitLogin() {
		WebElement btnLogin = getDriver().findElement(By.id("submit_button"));
		btnLogin.click();
		return new MainPage();
	}
	
}

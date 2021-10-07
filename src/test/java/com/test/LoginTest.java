package com.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.core.BaseTest;
import com.page.LoginPage;
import com.page.MainPage;

public class LoginTest extends BaseTest{
	private LoginPage login;
	private MainPage main;
	
	@Test
	public void testValidLogin() {
		login = new LoginPage();
		
		login.open();
		login.setEnvironment("trindade");
		login.setUsername("aluno01");
		login.setPassword("123456");
		
		main = login.submitLogin();
		
		main.clickAvatar();
		assertEquals("Aluno 01 (aluno01)", main.getUserLogger());
		
	}
	
	@Test
	public void testInvalidUserLogin() {
		login = new LoginPage();
		login.open();
		login.setEnvironment("trindade");
		login.setUsername("wronguser");
		login.setPassword("wrongpassword");
		
		login.submitLoginInvalid();
		
		assertEquals("ERRO\nLOGIN INVÁLIDO.", login.getMessageError());
	}
}

package com.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.core.BaseTest;
import com.page.LoginPage;
import com.page.MainPage;
import com.page.TaskPage;

public class TaskTest extends BaseTest{
	
	private LoginPage login;
	private MainPage main;
	private TaskPage task;
	
	@Test
	public void testSearchTask() {
		login = new LoginPage();
		main  = login.open().loggin("trindade", "aluno01", "123456");
		task = main.accessMenuTask();
		
		task.clearFilter();
		task.filter("Le Grand Burger");
		assertTrue(task.isExistTaskWithAgent("Aluno 01"));
	}
}

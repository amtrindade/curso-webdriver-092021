package com.core;

import org.junit.After;

public abstract class BaseTest {
	
	@After
	public void tearDown() {
		DriverFactory.killDriver();
	}

}

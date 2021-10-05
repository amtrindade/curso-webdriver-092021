package com.core;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class GlobaProperty {
	
	public static String getProperty(String key) {
		Properties property = new Properties();
		String value = null;
		
		try {
			property.load(
				new FileInputStream(
						"src"+File.separator+
						"test"+File.separator+
						"resources"+File.separator+
						"environment.properties"));			
				value = property.getProperty(key);						
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}
}

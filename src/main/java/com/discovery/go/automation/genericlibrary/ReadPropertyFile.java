package com.discovery.go.automation.genericlibrary;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadPropertyFile {
	public static String getPropertyFileData(String key) throws IOException {
		String propertyfileName = System.getProperty("user.dir") + "/src/main/resources/Utility.properties";
		FileInputStream fis = new FileInputStream(propertyfileName);
		Properties prop = new Properties();
		prop.load(fis);
		fis.close();
		return prop.getProperty(key);
	}
}

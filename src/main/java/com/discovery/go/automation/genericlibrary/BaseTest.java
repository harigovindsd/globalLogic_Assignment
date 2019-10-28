package com.discovery.go.automation.genericlibrary;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {
	
	protected WebDriver driver = null;
	
	@BeforeMethod
	@Parameters("browsertype")
	public void launchBrowser(@Optional("chrome")String browser,Object[] obj) throws Exception{
		if(browser.equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/src/main/resources/chromedriver.exe");
			driver = new ChromeDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Long.parseLong(ReadPropertyFile.getPropertyFileData("IMPLICITWAIT")), TimeUnit.SECONDS);
		driver.get(ReadPropertyFile.getPropertyFileData("URL"));
	}
	@AfterMethod
	public void tearDown(){
		driver.close();
	}
	
	public static void forceFailTestCases(String message) throws Exception{
		throw new Exception(message);
	}

}

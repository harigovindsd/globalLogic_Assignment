package com.discovery.go.automation.pageObjectGenericMethods;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.discovery.go.automation.pageObjects.HomePage;

public class HomePageGeneric {

	WebDriver driver = null;

	public HomePageGeneric(WebDriver driver) {
		this.driver = driver;
	}
	
	public void scrollToDisplayShowsListInView(WebDriver driver){
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,98)");
	}
	
	public void navigateToTvShowsPage(){
		HomePage homePage = new HomePage(driver);
		
		scrollToDisplayShowsListInView(driver);
		homePage.HomePage_Gnav_Shows.click();
		homePage.HomePage_Gnav_Shows_SeeAllShows.click();
	}

}

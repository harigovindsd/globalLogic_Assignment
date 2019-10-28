package com.discovery.go.automation.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TvShowsPage {
	
	public TvShowsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//div[@class='allShowsGrid__tile ']/div/a[contains(@href,'apollo')]")
	public List<WebElement> TvShows_Apollo_ShowsList;
	
}

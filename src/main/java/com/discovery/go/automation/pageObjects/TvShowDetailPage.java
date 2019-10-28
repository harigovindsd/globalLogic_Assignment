package com.discovery.go.automation.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TvShowDetailPage {
	
	public TvShowDetailPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath="//img[@class='showHero__showLogo']")
	public WebElement TvShowDetailPage_TvShowTitle;
	
	@FindBy(xpath="//div[@class='showHero__main']//div[@class='my-favorites-button-container']//i[contains(@class,'flipIconCore__icon')]")
	public WebElement TvShowDetailPage_Favourites_Button;

}

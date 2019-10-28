package com.discovery.go.automation.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//span[contains(text(),'Shows')]")
	public WebElement HomePage_Gnav_Shows;
	
	@FindBy(xpath = "//div[@id='show-drop-desktop']//a[@class='dscShowsDropContent__seeAllShows'][contains(text(),'See All Shows')]")
	public WebElement HomePage_Gnav_Shows_SeeAllShows;
	
	@FindBy(xpath="//li[@class='dscHeaderMain__hideMobile']")
	public WebElement Gnav_HiddenMenu;

}

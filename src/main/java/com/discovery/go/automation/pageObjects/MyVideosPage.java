package com.discovery.go.automation.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyVideosPage {
	
	public MyVideosPage(WebDriver driver){
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//span[contains(text(),'My Videos')]")
	public WebElement MyVideos_Link;
	
	@FindBy(xpath="(//section[contains(@class,'layout-section FavoriteShowsCarousel layoutSection__main')]//h3[contains(@class,'showTileSquare__title')]/div)")
	public List<WebElement> FavMyVideosList;
	
	@FindBy(xpath="//h2[contains(text(),'Favorite Shows')]")
	public WebElement FavouriteShows_Header;

}

package com.discovery.go.automation.pageObjectGenericMethods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.discovery.go.automation.genericlibrary.BaseTest;
import com.discovery.go.automation.pageObjects.HomePage;
import com.discovery.go.automation.pageObjects.MyVideosPage;

public class MyVideosGeneric {

	WebDriver driver = null;

	String favMyVideosObject = "(//section[contains(@class,'layout-section FavoriteShowsCarousel layoutSection__main')]//h3[contains(@class,'showTileSquare__title')]/div)[%d]";

	public MyVideosGeneric(WebDriver driver) {
		this.driver = driver;
	}

	public void navigateToMyVideos() {
		HomePage homePage = new HomePage(driver);
		MyVideosPage myVideosPage = new MyVideosPage(driver);
		WebDriverWait wait = new WebDriverWait(driver, 15);
		
		homePage.Gnav_HiddenMenu.click();
		myVideosPage.MyVideos_Link.click();
		wait.until(ExpectedConditions.visibilityOf(myVideosPage.FavouriteShows_Header));
	}

	public List<String> fetchFavouriteVideoList() {

		MyVideosPage myVideosPage = new MyVideosPage(driver);
		Actions actions = new Actions(driver);
		TvShowsPageGeneric tvShowsPageGeneric= new TvShowsPageGeneric(driver);
		tvShowsPageGeneric.doLongSleep();
		int numberOfFavVideos = myVideosPage.FavMyVideosList.size();
		List<String> favouriteMyVideosList = new ArrayList<String>();
		for (int i = 1; i <= numberOfFavVideos; i++) {
			WebElement favouriteVideoElement = driver.findElement(By.xpath(favMyVideosObject.replace("%d", String.valueOf(i))));
			actions.moveToElement(favouriteVideoElement).perform();
			String favouriteMyVideoTitle = driver
					.findElement(By.xpath(favMyVideosObject.replace("%d", String.valueOf(i)))).getText().toLowerCase();
			favouriteMyVideosList.add(favouriteMyVideoTitle);
		}
		
		return favouriteMyVideosList;
	}
	
	public void validateFavouriteVideos(Map<String, List<String>> favUnfavDataMap)throws Exception{
//		TvShowsPageGeneric tvShowsPageGeneric = new TvShowsPageGeneric(driver);
		
//		Map<String, List<String>> favUnfavDataMap = tvShowsPageGeneric.getFavUnfavDataMap();
		
		
		List<String> favouriteMyVideosList = fetchFavouriteVideoList();
		
		List<String> favList = favUnfavDataMap.get("favourites");
		if(!favouriteMyVideosList.containsAll(favList)){
			System.out.println("favouriteMyVideosList-->"+Arrays.toString(favouriteMyVideosList.toArray()));
			System.out.println("favList-->"+Arrays.toString(favList.toArray()));
			BaseTest.forceFailTestCases("Newly Added favourites are not listed in Favourite Videos section");
		}
		
		List<String> unFavList = favUnfavDataMap.get("unFavourites"); 
		for(String s : unFavList){
			if(favouriteMyVideosList.contains(s)){
				BaseTest.forceFailTestCases("Unfavourited videos are still listed in Favourite Videos section");
			}
		}
	}

}

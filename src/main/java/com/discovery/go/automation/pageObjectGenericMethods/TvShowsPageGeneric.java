package com.discovery.go.automation.pageObjectGenericMethods;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.discovery.go.automation.genericlibrary.BaseTest;
import com.discovery.go.automation.genericlibrary.ReadPropertyFile;
import com.discovery.go.automation.pageObjects.TvShowDetailPage;
import com.discovery.go.automation.pageObjects.TvShowsPage;

public class TvShowsPageGeneric {

	WebDriver driver = null;

	public TvShowsPageGeneric(WebDriver driver) {
		this.driver = driver;
	}
	
	Actions actions = null;
	WebDriverWait wait = null;

	String apolloTileObj = "(//div[@class='allShowsGrid__tile ']/div/a[contains(@href,'apollo')])[%d]";

	public void scrollForPageLoad() {
		System.out.println("scrollForPageLoad : STARTED");
		actions = new Actions(driver);
		TvShowsPage tvShowsPage = new TvShowsPage(driver);
		
		while (tvShowsPage.TvShows_Apollo_ShowsList.size() == 0) {
//			System.out.println("Apollo List-->"+tvShowsPage.TvShows_Apollo_ShowsList.size());
			actions.sendKeys(Keys.END).perform();
			actions.sendKeys(Keys.PAGE_UP).perform();
			actions.sendKeys(Keys.PAGE_UP).perform();
		}
		System.out.println("scrollForPageLoad : ENDED");
	}

	public Map<String, List<String>> performFavouriteUnfavouriteAction() throws Exception {
		System.out.println("performFavouriteUnfavouriteAction : STARTED");
		scrollForPageLoad();
		
		List<String> favList = new ArrayList<String>();
		List<String> unFavList = new ArrayList<String>();
		Map<String, List<String>> favUnfavDataMap = new HashMap<String, List<String>>();
		
		wait = new WebDriverWait(driver, 15);
		TvShowsPage tvShowsPage = new TvShowsPage(driver);
		TvShowDetailPage tvShowDetailPage = new TvShowDetailPage(driver);

		for (int i = 1; i <= tvShowsPage.TvShows_Apollo_ShowsList.size(); i++) {
			doLongSleep();
			wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(apolloTileObj.replace("%d", String.valueOf(i)))));

			driver.findElement(By.xpath(apolloTileObj.replace("%d", String.valueOf(i)))).click();
			wait.until(ExpectedConditions.elementToBeClickable(tvShowDetailPage.TvShowDetailPage_Favourites_Button));
			String showTitle = tvShowDetailPage.TvShowDetailPage_TvShowTitle.getAttribute("alt").toLowerCase();
			System.out.println("show title--->" + showTitle);
			String favStatus = tvShowDetailPage.TvShowDetailPage_Favourites_Button.getAttribute("class");
			System.out.println("status before click" + favStatus);
			if (favStatus.contains("icon-plus")) {
				tvShowDetailPage.TvShowDetailPage_Favourites_Button.click();
				doShortSleep();
				System.out.println("status after click"
						+ tvShowDetailPage.TvShowDetailPage_Favourites_Button.getAttribute("class"));
				favList.add(showTitle);
			} else if (favStatus.contains("icon-minus")) {
				tvShowDetailPage.TvShowDetailPage_Favourites_Button.click();
				doShortSleep();
				System.out.println("status after click"
						+ tvShowDetailPage.TvShowDetailPage_Favourites_Button.getAttribute("class"));
				unFavList.add(showTitle);
			} else {
				BaseTest.forceFailTestCases("Unable to determine Fav Icon status");
			}

			driver.navigate().back();
		}
		
		favUnfavDataMap.put("favourites", favList);
		favUnfavDataMap.put("unFavourites",unFavList);
		for (String key : favUnfavDataMap.keySet()) {
	        System.out.println(key + "=" + favUnfavDataMap.get(key));
	    }
		System.out.println("performFavouriteUnfavouriteAction : ENDED");
		return favUnfavDataMap;
	}

	public void doShortSleep() {
		try {
			Thread.sleep(Long.valueOf(ReadPropertyFile.getPropertyFileData("SLEEP_DURATION_SHORT")));
		} catch (NumberFormatException e) {
			e.getMessage();
		} catch (IOException e) {
			e.getMessage();
		} catch (InterruptedException e) {
			e.getMessage();
		}
	}

	public void doLongSleep() {
		try {
			Thread.sleep(Long.valueOf(ReadPropertyFile.getPropertyFileData("SLEEP_DURATION_LONG")));
		} catch (NumberFormatException e) {
			e.getMessage();
		} catch (IOException e) {
			e.getMessage();
		} catch (InterruptedException e) {
			e.getMessage();
		}
	}
	
/*	public Map<String, List<String>> getFavUnfavDataMap(){
		System.out.println("getFavUnfavDataMap : STARTED");
		for (String key : favUnfavDataMap.keySet()) {
	        System.out.println(key + "=" + favUnfavDataMap.get(key));
	    }
		System.out.println("getFavUnfavDataMap : ENDED");
		return favUnfavDataMap;
	}*/

}

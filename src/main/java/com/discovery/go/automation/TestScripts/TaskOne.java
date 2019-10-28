package com.discovery.go.automation.TestScripts;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.discovery.go.automation.genericlibrary.BaseTest;
import com.discovery.go.automation.pageObjectGenericMethods.HomePageGeneric;
import com.discovery.go.automation.pageObjectGenericMethods.MyVideosGeneric;
import com.discovery.go.automation.pageObjectGenericMethods.TvShowsPageGeneric;

public class TaskOne extends BaseTest{
	
	
	@Test
	public void testTaskOne() throws Exception{
		HomePageGeneric homePageGeneric = new HomePageGeneric(driver);
		TvShowsPageGeneric tvShowsPageGeneric = new TvShowsPageGeneric(driver);
		MyVideosGeneric myVideosGeneric = new MyVideosGeneric(driver);

		homePageGeneric.navigateToTvShowsPage();
		Map<String, List<String>> map = tvShowsPageGeneric.performFavouriteUnfavouriteAction();
		myVideosGeneric.navigateToMyVideos();
		myVideosGeneric.validateFavouriteVideos(map);
		
	}

}

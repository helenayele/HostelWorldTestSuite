package tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import pages.HomePage;
import pages.PropertyViewPage;
import pages.SearchResultPage;

public class HostelBookingTests extends TestBase{
	
	HomePage home;
	SearchResultPage search;
	PropertyViewPage property;
	
	@BeforeClass
    public void initPages() {
		home = new HomePage(driver());
		search = new SearchResultPage(driver());
		property = new PropertyViewPage(driver());
    }

	@Test(dataProvider="getData")
	public void HostelCheckOutTest(Map<String,String> data) {
		try {
			home.launchApp();
			report().log(LogStatus.INFO, "App is launched successfully");
			home.setDestination(data.get("city"));
			report().log(LogStatus.PASS, "User selects city successfully");
			takeSnapShots();
			home.setTravelDate(data.get("checkindate"), data.get("checkoutdate"));
			report().log(LogStatus.PASS, "User selects checkIn and checkOut day successfully");
			home.performHostelSeach();
			report().log(LogStatus.PASS, "Search is done successfully");
			search.clickFirstProperty();
			report().log(LogStatus.PASS, "USer selects first property successfully");
			//property.chooseRoomToBook();
			//System.out.println();
			
		}catch(Exception e) {
			report().log(LogStatus.FAIL,e.getMessage());
		}
	}
	
	
	
	
	
}

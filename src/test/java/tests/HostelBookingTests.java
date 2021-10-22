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
	public void HostelCheckOutTest(Map<String,String> data) throws Exception {
		try {
			home.launchApp();
			report().report(LogStatus.INFO,"Launching app step", "App is launched successfully");
			home.setDestination(data.get("city"));
			report().report(LogStatus.PASS, "Desiination city selection step","User selects city successfully");
			home.setTravelDate(data.get("checkindate"), data.get("checkoutdate"));
			report().report(LogStatus.PASS, "Checkinout","User selects checkIn and checkOut day successfully",true);
			home.performHostelSeach();
			report().report(LogStatus.PASS, "SearchStep","User ables to search ",true);;
			search.clickFirstProperty();
			report().report(LogStatus.PASS, "First property","User selects first Property successfully",true);
			//property.chooseRoomToBook();
			//System.out.println();
			
		}catch(Exception e) {
			report().report(LogStatus.FAIL,"Exception Occured",e.getMessage(),true);
		}
	}
	
	
	
	
	
}

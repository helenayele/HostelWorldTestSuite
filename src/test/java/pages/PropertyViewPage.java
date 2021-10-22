package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PropertyViewPage extends BasePage{
	
	@FindBy(xpath="(//div[@aria-label='Choose'])[1]")
	private WebElement chooseFirstRoomList;
	
	@FindBy(xpath="//div[@class='menu']//li[contains(text(),'1 Room')]")
	private WebElement chooseOneRoom;
	
	@FindBy(id="book-now-button")
	private WebElement btnBookNow;
	
	
	public PropertyViewPage(WebDriver driver) {
		super(driver);
	}
	
	public void chooseRoomToBook() {
		chooseFirstRoomList.click();
		chooseOneRoom.click();
		btnBookNow.click();
	}
	
	
	

}

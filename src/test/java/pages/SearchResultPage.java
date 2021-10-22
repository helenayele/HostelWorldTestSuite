package pages;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchResultPage extends BasePage{

	@FindBy(xpath="//p[@class='tot-properties']/following-sibling::div[@class='property-card']//a[@class='view-button']")
	private List<WebElement> listPropertyViewButtons;
	
	public SearchResultPage(WebDriver driver) {
		super(driver);
	}
	
	public void clickFirstProperty() {
		listPropertyViewButtons.get(0).click();
	}
	
}

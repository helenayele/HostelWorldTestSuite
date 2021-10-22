package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import core.FileConfig;

public class HomePage extends BasePage{
	
	// locators
	@FindBy(id="truste-consent-button")    
	private WebElement cookies;
	
	@FindBy(id="location-text-input-field")   
	private WebElement searchLocationTextbox;
	
	@FindBy(id="search-input-field")   
	private WebElement searchLocationTextInput;
	
	@FindBy(xpath="//ul[@id='predicted-search-results']/li[2]")   
	private WebElement locationSearchResult;
	
	@FindBy(xpath="//label[text()='Check In']/following-sibling::div")   
	private WebElement checkInLabel;
	
	@FindBy(xpath="//div[@id='check-in-field']//div[@class='month-name']")   
	private WebElement checkInMonth;
	
	@FindBy(xpath="//div[@id='check-in-field']/i[contains(@class,'right')]")   
	private WebElement checkInRightArrow;
	
	@FindBy(xpath="//div[@id='check-in-field']")   
	private WebElement checkInField;
	
	@FindBy(xpath="//div[@id='check-out-field']//div[@class='month-name']")   
	private WebElement checkOutMonth;
	
	@FindBy(xpath="//div[@id='check-out-field']/i[contains(@class,'right')]")   
	private WebElement checkOutRightArrow;
	
	@FindBy(xpath="//div[@id='check-out-field']")   
	private WebElement checkOutField;
	
	@FindBy(id="search-button")   
	private WebElement searchButton;
		
	public HomePage(WebDriver driver) {
		super(driver);
	}
    
	// business methods
	
    public void launchApp() throws Exception {
    	driver.get(FileConfig.getProperty("baseUrl"));
    	acceptCookies();
    }
    
    public void acceptCookies()  {
    	try {
    		waitForElement(2,cookies).click();
    	}catch(Exception e) {}
    }
	
	public void setDestination(String cityName) throws Exception {
		searchLocationTextbox.click();
		for(int i=0; i<cityName.length();i++) {
			searchLocationTextInput.sendKeys(String.valueOf(cityName.charAt(i)));
			Thread.sleep(200);
			if(i==cityName.length()-1) {
				waitForElement(10,locationSearchResult).click();
			}
		}
	}
	
	public void setTravelDate(String inDate, String outDate) throws Exception {
		String inday = inDate.split("-")[0];
	    String inmonth = inDate.split("-")[1]+" "+inDate.split("-")[2];
	    
	    String outday = outDate.split("-")[0];
	    String outmonth = outDate.split("-")[1]+" "+outDate.split("-")[2];
	    
	    checkInLabel.click();
	    int counter=1;
	    while(!checkInMonth.getText().equals(inmonth)) {
	    	if(counter == 12) break;
	    	checkInRightArrow.click();
	    	counter++;
	    }
	    checkInField.findElement(By.xpath("//div[text()='"+inday+"']")).click();
	    
	    if(!checkOutMonth.getText().equals(outmonth)) {
	    	checkOutRightArrow.click();
	    }
	    Thread.sleep(2000);
	    checkOutField.findElement(By.xpath("//div[text()='"+outday+"']")).click();
	}
	
	public void performHostelSeach() {
		searchButton.click();
	}

	
	
	
	
	
	
	
	

}

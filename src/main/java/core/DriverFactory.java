package core;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;


import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	
	private WebDriver driver =null;
	
	public WebDriver getDriver(String browser) {
		
		if(browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions ops =  new ChromeOptions();
			ops.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"}); 
			//System.setProperty("webdriver.chrome.driver","C:\\devwork\\drivers\\chromedriver\\chromedriver.exe");
			driver = new ChromeDriver(ops);
		}else if(browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			//System.setProperty("webdriver.gecko.driver","C:\\devwork\\drivers\\geckodriver-v0.29.1-win64\\geckodriver.exe");
			driver =  new FirefoxDriver();
		}	
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Long.parseLong(FileConfig.getProperty("pageLoadTimeOut")), TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Long.parseLong(FileConfig.getProperty("implicitWait")), TimeUnit.SECONDS);
		return driver;
	}
	
	public void quitDriver() {
		if(driver!=null) {
			driver.quit();
		}
	}
	
	
	

}

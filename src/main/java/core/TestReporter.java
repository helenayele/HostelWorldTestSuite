package core;

import java.io.File;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.google.common.io.Files;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestReporter {
	private ExtentReports extentReport;
	private ExtentTest test ;
	private WebDriver driver;
	
	public TestReporter() {
	
		String filePath = System.getProperty("user.dir")+"//reports//testReport.html";
		System.out.println(filePath + " file path");
		extentReport = new ExtentReports(filePath, true);
		File file = new File("/Screenshots");
		if (!file.exists()) {
			file.mkdir();
		}
	}
	
	public void startReporting(String testCaseName,WebDriver driver) {
		this.driver = driver;
		test = new ExtentTest(testCaseName, ""); 
	}
	
	public void endReporting() {
		 extentReport.endTest(test);
	}
	
	public void flushReport() {
		extentReport.flush();
		extentReport.close();
	}
	
	public void report(LogStatus status, String description) {
		if (test!=null) {
			test.log(status, description);
		}
		
	}
	
	public void report(LogStatus status,String stepName, String description) {
		if (test!=null) {
			test.log(status, stepName,description);
		}
		
	}
	
	public void report(LogStatus status,String stepName, String description, boolean takeSnap) throws Exception {
		if (test!=null) {
			if (takeSnap) {
			   String snapLocation = this.takeSnapShots();
			   test.log(status,stepName, description + " "  + snapLocation);
			
			}else 
				test.log(status, stepName,description);
		}
		
	}
	
	private String takeSnapShots() throws Exception{
		TakesScreenshot shot = (TakesScreenshot)driver;
		File srcFile = shot.getScreenshotAs(OutputType.FILE);
		Path srcPath = srcFile.toPath();  
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/mm/yyy hh:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String  date = format.format(now);
		date = date.replaceAll("/","").replaceAll(":", "").replaceAll(" ", "");
		
		File destFile = new File("/ScreenShots/" + date + ".png");
		Path destPath = destFile.toPath();
		Files.copy(srcFile, destFile);
		
		return destPath.toAbsolutePath().toString();
	}
}

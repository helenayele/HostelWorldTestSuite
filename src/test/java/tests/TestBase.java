package tests;

import java.io.File;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import com.google.common.io.Files;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import core.FileConfig;
import core.DriverFactory;
import core.ExcelDataProvider;
import core.ITestData;
import core.JSONDataProvider;

public class TestBase {

	private WebDriver driver;
	private DriverFactory df;
	private ExtentReports extentReport;
	ExtentTest test ;
	@BeforeSuite
	public void initSuite() {
		FileConfig.loadConfig(System.getenv("env"));
		String filePath = System.getProperty("user.dir")+"//reports//";
		System.out.println(filePath + " file path");
		extentReport = new ExtentReports(filePath, true);
		File file = new File("./Screenshots");
		if (!file.exists()) {
			file.mkdir();
		}
	}
	
	//@Parameters("browser")
	@BeforeClass(alwaysRun=true)
	public void initDriver() {
		df = new DriverFactory();
		driver = df.getDriver(System.getenv("browser"));
		if (driver == null) {
			System.out.println("The driver is null please stop here");
		}
	}
	
	@BeforeMethod
	public void initTestReport(Method method) {
		 test = new ExtentTest(method.getName(), ""); 
	}
	
	public ExtentTest report() {
		if (test!=null) {
			return test;
		}
		
		return null;
	}
	@AfterMethod
	public void closeReport() {
		extentReport.endTest(test);
	}
	
	@AfterClass(alwaysRun=true)
	public void killDriver() {
		df.quitDriver();
	}
	
	public void clearReport() {
		extentReport.flush();
		extentReport.close();
	}
	
	protected WebDriver driver() {
		return driver;
	}
	
	@DataProvider
	public Object[][] getData(Method method) {
		
		ITestData dt = null;
		String envName = System.getenv("env").toLowerCase();//.toUpperCase();
		String testCaseName = method.getName();
		
		try {
			if(System.getenv("ds").toUpperCase().equals("EXCEL")){
				String filePath = System.getProperty("user.dir")+"//src//test//resources//data//TestData.xlsx";	
				dt = new ExcelDataProvider(filePath,envName);
				
			}else if (System.getenv("ds").toUpperCase().equals("JSON")) {
				String filePath = System.getProperty("user.dir")
						+"//src//test//resources//data//data."+envName.toLowerCase()+".json";	
				dt = new JSONDataProvider(filePath);
			}
			
			List<HashMap<String,String>> finalData = dt.getAllData(testCaseName);
			
//			Object[][] dataObj = new Object[finalData.size()][1];
//			for(int i=0;i<finalData.size();i++) {
//				dataObj[i][0] = finalData.get(i);
//			}
			
			return createDataProvider(finalData);
		}catch(Exception e) {
			e.printStackTrace();
		}
	
		return null;
	}
	
	private Object[][] createDataProvider(Object dataSet){
		int rowNo = ((ArrayList)dataSet).size();
		Object[][] dataArray = new Object[rowNo][1];
		int dim = 0;
		for(int iRow=0;iRow<rowNo;iRow++) {
			dataArray[dim][0] = ((ArrayList)dataSet).get(iRow);
			dim++;
		}
		return dataArray;
	}
	
	public void takeSnapShots() throws Exception{
		WebDriver driver = driver();
		TakesScreenshot shot = (TakesScreenshot)driver;
		File srcFile = shot.getScreenshotAs(OutputType.FILE);
		Path srcPath = srcFile.toPath();  
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/mm/yyy hh:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String  date = format.format(now);
		date = date.replaceAll("/","").replaceAll(":", "").replaceAll(" ", "");
		
		File destFile = new File("./ScreenShots/" + date + ".png");
		Path destPath = destFile.toPath();
		Files.copy(srcFile, destFile);
		
		
	}
}

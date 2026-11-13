package rahulshettyacademy.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.LoginPractice;

public class BaseTestPractice  {
   //properties file
	public WebDriver driver ;
	ChromeOptions options ;
	   public WebDriver initializeDriver() throws IOException {
		   Properties prop = new Properties();
		   FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\rahulshettyacademy\\resources\\GlobalData.properties");
		   prop.load(fis);
		   String browserName = System.getProperty("browser")!=null ? System.getProperty("browser"): prop.getProperty("browser");
		 // String browserName = prop.getProperty("browser");
		  if(browserName.contains("chrome")) {
			
			  options = new ChromeOptions();
		  if(browserName.contains("headless")) {
			
			  options.addArguments("--headless=new");
		  }
		      WebDriverManager.chromedriver().setup();
			  driver= new ChromeDriver(options);
		  
		  }
		  return driver;
		   
	   }
	    public LoginPractice launchApplication() throws IOException {
		   initializeDriver();
		   LoginPractice login = new LoginPractice(driver);
		   login.goTo();
		   return login;
	   }
	 //check brokenlink
	    public List<HashMap<String, String>> getJsonData() throws IOException {
	 String src = FileUtils.readFileToString(new File (System.getProperty("user.dir")+"\\src\\test\\java\\rahulshettyacademy\\data\\PracticePage.json"),StandardCharsets.UTF_8);
	 ObjectMapper mapper = new ObjectMapper();
	 TypeReference<List<HashMap<String,String>>> ref = new TypeReference<List<HashMap<String,String>>>() {
	  
	 };
	 return mapper.readValue(src,ref);
	
	    }
	  
	    //Extentreport
		
		  public static ExtentReports extentReport() { String path =
		  System.getProperty("user.dir")+("//reports//index.html"); ExtentSparkReporter
		  reporter = new ExtentSparkReporter(path);
		  reporter.config().setReportName("Automation");
		  reporter.config().setDocumentTitle("Results"); ExtentReports extent = new
		  ExtentReports(); extent.attachReporter(reporter); return extent; }
		 
	  
	    public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
	    TakesScreenshot scrn= 	(TakesScreenshot)driver;
	   File src =  scrn.getScreenshotAs(OutputType.FILE);
	   String path2 =  (System.getProperty("user.dir")+("//reports//.png"));
	   FileUtils.copyFile(src, new File(path2));
	   return path2;
	    }
	    public void getName () {
	    	System.out.println("name");
	    	
	    }
}

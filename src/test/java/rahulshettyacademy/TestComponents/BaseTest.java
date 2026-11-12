package rahulshettyacademy.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.LandingPage;

public class BaseTest {
	
   public WebDriver driver;
   public  LandingPage landing ;
	
    public WebDriver initializeDriver() throws IOException {
		//properties class to read all the information from .properties file
		Properties prop = new Properties();
		FileInputStream src = new FileInputStream(System.getProperty("user.dir")+ "//src//main//java//rahulshettyacademy//resources//GlobalData.properties");
		prop.load(src);
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser"); //to read the globalparameter from command prompt using maven commands
		//String browserName = prop.getProperty("browser");
		if(browserName.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			if(browserName.contains("headless")) {
				options.addArguments("--headless=new");	
				 options.addArguments("--window-size=1440,900");
			}
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440,900));// full screen
		}
		else {
			//firefox code
		}
		driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	    return driver;
	}
    public List<HashMap<String, String>> getJsonDataToMap(String FilePath) throws IOException {
		String jsonContent = FileUtils.readFileToString(new File (FilePath), StandardCharsets.UTF_8);
		//String to HashMap - Jackson DataBind
		ObjectMapper map = new ObjectMapper();
	List<HashMap<String,String>> data = map.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>() {
			
		});
	return data;
	}
    public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
    	TakesScreenshot scrn = (TakesScreenshot) driver;
    	File src = scrn.getScreenshotAs(OutputType.FILE);
    	String destination=  (System.getProperty("user.dir")+"//reports//"+ testCaseName +".png");
    	FileUtils.copyFile(src, new File(destination));
    	return destination;
    }
    @BeforeMethod(alwaysRun=true)
    public LandingPage launchApplication() throws IOException {
    	driver = initializeDriver();
         landing = new LandingPage(driver);
        landing.goTo();
       return landing;
    }
    @AfterMethod(alwaysRun=true)
    public void tearDown() {
    	driver.close();
    }
}

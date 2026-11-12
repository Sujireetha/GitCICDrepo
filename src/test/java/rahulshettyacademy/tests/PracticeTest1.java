package rahulshettyacademy.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.Retry;

import rahulshettyacademy.TestComponents.BaseTestPractice;
import rahulshettyacademy.pageobjects.AngularPractice;
import rahulshettyacademy.pageobjects.LoginPractice;
import rahulshettyacademy.pageobjects.SubmitPractice;
import rahulshettyacademy.TestComponents.RetryPractice;
public class PracticeTest1 extends BaseTestPractice {
	
	@Test(dataProvider="getData", retryAnalyzer=RetryPractice.class)
	public void practicePage(HashMap<String, String> input) throws IOException {
		LoginPractice loginpage = launchApplication();
		loginpage.userInfo(input.get("email"),input.get("password"));
		AngularPractice page = loginpage.submitInfo();
		SubmitPractice sumbit = page.secondPage("Suji");
		sumbit.submitPage();
		
	}
	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String,String>> data = getJsonData();
		return new Object[][] {
			{data.get(0)}
		};
		
	}
	public void takesScreenshot() throws IOException {
		WebDriver driver = new ChromeDriver();
		TakesScreenshot src= (TakesScreenshot)driver;
		File image = src.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(image, new File(""));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
		js.executeScript("dom.querySelector(.'tableFixHead').scrollTop=500");
		Properties prop = new Properties();
		FileInputStream input = new FileInputStream(System.getProperty("user.dir")+"");
		prop.load(input);
		String browserName = prop.getProperty("browser");
	}
  @DataProvider
  public Object[][] getData2() {
	  HashMap<String,String> map = new HashMap<String,String> ();
	  map.put("email", "user@123");
	  map.put("name", "user");
	  HashMap<String,String> map1 = new HashMap<String,String> ();
	  map1.put("email", "user@1234");
	  map1.put("name", "user1");
	  return new Object[][] {
		  {map},{map1}
	  };
  }

}

package rahulshettyacademy.tests;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.LandingPage;

public class StandAloneTest2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String product = "ADIDAS ORIGINAL";
		String cname = "ind";
      WebDriverManager.chromedriver().setup(); // invokes the chrome automatically
      WebDriver driver = new ChromeDriver();
      driver.manage().window().maximize();
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
      driver.get("https://rahulshettyacademy.com/client/");
      LandingPage landing = new LandingPage(driver);
      
      driver.findElement(By.id("userPassword")).sendKeys("Shop@123");
      driver.findElement(By.id("login")).click();
      ////div[@class='card-body']/h5//b   /button[@class='btn w-10 rounded'] //button[@class='btn btn-custom']
     List<WebElement> products = driver.findElements(By.xpath("//div[@class='card-body']/h5//b "));
    for(int i = 0; i<products.size() ; i++) {
    	 if(products.get(i).getText().contains(product)) {
    		 driver.findElement(By.xpath("//div[@class='card-body']/button[@class='btn w-10 rounded']")).click();
    	 } 
     }
     WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(10));
     wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")) );
     wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
     driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
     //driver.findElement(By.xpath("//div[@class='cartSection removeWrap']/button")).click();
   List<WebElement> cart=   driver.findElements(By.xpath("//div[@class='cartSection']/h3"));
   String cartname = "ADIDAS ORIGINAL";
   for(int i = 0; i<cart.size() ; i++) {
	   if(cart.get(i).getText().contains(product)) {
		    cartname = cart.get(i).getText();
		   System.out.println(cartname);
		   break;
		   
	   }
	      }
     Assert.assertEquals(product, cartname);
     driver.findElement(By.xpath("//ul//button[@type='button']")).click();
     driver.findElement(By.xpath("//div[@class='form-group']/input")).sendKeys(cname);
    List<WebElement> country = driver.findElements(By.xpath("//section[@class='ta-results list-group ng-star-inserted']//span"));
    for(int i = 0; i<country.size(); i++ ) {
    	String countires = country.get(i).getText();
    	countires.equals(" India");
    	//if(country.get(i).getText().contains(" India")) {
    	country.get(i).click();
    	break;
    }
    
    driver.findElement(By.xpath("//div[@class='actions']/a")).click();
    System.out.println(driver.findElement(By.xpath("//tr[@class='ng-star-inserted']//label")).getText());
    driver.quit();
	}
   
}

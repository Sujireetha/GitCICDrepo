package rahulshettyacademy.pageobjects;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PlaceOrder {
    // driver.findElement(By.xpath("//div[@class='form-group']/input")).sendKeys(cname);
	//PageFactory
	@FindBy(xpath="//div[@class='form-group']/input")
	WebElement countryname;
	
	//List<WebElement> country = driver.findElements(By.xpath("//section[@class='ta-results list-group ng-star-inserted']//span"));
	//PageFacory
	@FindBy(xpath="//section[@class='ta-results list-group ng-star-inserted']//span")
	List<WebElement> countrydropdown;
	
	//driver.findElement(By.xpath("//div[@class='actions']/a")).click();
	//PageFactory
	@FindBy(xpath="//div[@class='actions']/a")
	WebElement orderplaced;
	
	//System.out.println(driver.findElement(By.xpath("//tr[@class='ng-star-inserted']//label")).getText());
	//PageFactory
	@FindBy(xpath="//tr[@class='ng-star-inserted']//label")
	WebElement ordernumber;
	
	WebDriver driver;
	
	public PlaceOrder(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void sendCountry(String name) {
		countryname.sendKeys(name);
	}

	public void countryNameSelect(String compare) {
		for(int i = 0; i<countrydropdown.size(); i++ ) {
	    	String countires = countrydropdown.get(i).getText();
	    	if (countires.equals(compare.trim())) {
	    	//if(country.get(i).getText().contains(" India")) {
	    	countrydropdown.get(i).click();
	    	break;
	    	}
	    }
		
	}
   public void placeOrder() {
		orderplaced.click();
	}
   
   public String getOrderNumber() {
	  return ordernumber.getText();
   }
}

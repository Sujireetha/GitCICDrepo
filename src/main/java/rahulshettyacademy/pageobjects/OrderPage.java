package rahulshettyacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderPage {

	
	@FindBy(xpath="//tr[@class='ng-star-inserted']//td[2]")
	WebElement productName;
	
	WebDriver driver;
	
	public OrderPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}


	public String getProductName () {
		return productName.getText();
	}
}

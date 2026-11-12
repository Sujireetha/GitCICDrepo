package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class ProductCatalog extends AbstractComponent {
    
	WebDriver driver;
	
	public ProductCatalog(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	//List<WebElement> products = driver.findElements(By.xpath("//div[@class='card-body']/h5//b "));
    //PageFactory
	@FindBy(xpath="//div[@class='card-body']/h5//b ")
	List<WebElement> products;
	
	//PageFactory
	 @FindBy(id = "toast-container")
	 WebElement toastMessage;
	 
	 //PageFactory
	 @FindBy(css=".ng-animating")
	 WebElement spinner;
	 
	public CartCatalogue addproducts(String product) {
		for(int i = 0; i<products.size() ; i++) {
	    	 if(products.get(i).getText().contains(product)) {
	    		 driver.findElement(By.xpath("//div[@class='card-body']/button[@class='btn w-10 rounded']")).click();
	    		 break;
	    	 } 
	    	 
	     }
		waitelementvisible(toastMessage);
		waitelementtodisappear(spinner);
		return new CartCatalogue(driver);
	}
}

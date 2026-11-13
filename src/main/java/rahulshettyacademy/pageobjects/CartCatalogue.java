package rahulshettyacademy.pageobjects;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class CartCatalogue {
    
	//driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
	//PageFactory
	@FindBy(xpath="//button[@routerlink='/dashboard/cart']")
	WebElement cartbutton;
	// List<WebElement> cart=   driver.findElements(By.xpath("//div[@class='cartSection']/h3"));
	//PageFactory
	@FindBy(xpath="//div[@class='cartSection']/h3")
	List<WebElement> cartitems;
	// driver.findElement(By.xpath("//ul//button[@type='button']")).click();
	//PageFactory
	@FindBy(xpath="//ul//button[@type='button']")
	WebElement checkout;
	
	WebDriver driver;
	
	public CartCatalogue(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public String cartDetails(String product) {
		String cartname ="";
		cartbutton.click();
		 for(int i = 0; i<cartitems.size() ; i++) {
			   if(cartitems.get(i).getText().contains(product)) {
				   cartname = cartitems.get(i).getText();
				   System.out.println(cartname);
				   break;
			   }
			      }
		 return cartname;
		 
	}
	public PlaceOrder checkout() {
		checkout.click();
		return new PlaceOrder(driver);
	}
}

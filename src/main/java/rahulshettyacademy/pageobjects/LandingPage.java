package rahulshettyacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		 super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this); // this way findby@ knows about driver
		
	}
	// WebElement userEmail = driver.findElement(By.id("userEmail"));
	//PageFactory
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	//WebElement password = driver.findElement(By.id("userPassword"));
	//PageFactory
	@FindBy(id="userPassword")
	WebElement password;
	
	//WebElement login = driver.findElement(By.id("login"));
	//PageFactory
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(xpath="//button[@routerlink='/dashboard/myorders']")
	WebElement orders;
	
	public ProductCatalog loginpage(String email, String Password) {
		userEmail.sendKeys(email);
		password.sendKeys(Password);
		submit.click();
		return new ProductCatalog(driver);
	}
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/");
	}
	
	public OrderPage goToOrders() {
		orders.click();
		return new OrderPage(driver);
	}
	
}


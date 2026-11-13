package rahulshettyacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class LoginPractice extends AbstractComponent{
	WebDriver driver;

	public LoginPractice(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	//pageFactory
	@FindBy(id="username")
	WebElement name;
	@FindBy(id= "password") 
	WebElement pass;
	@FindBy(xpath="//input[@id='signInBtn']")
	WebElement submit;
	
     public WebDriver goTo () {
    	driver.get("https://rahulshettyacademy.com/loginpagePractise/");
    	return driver;
     }
     public void userInfo(String username, String password) {
    	 name.sendKeys(username);
    	 pass.sendKeys(password);
     }
     
    public AngularPractice submitInfo () {
    	submit.click();
    	return new AngularPractice(driver);
    }
}

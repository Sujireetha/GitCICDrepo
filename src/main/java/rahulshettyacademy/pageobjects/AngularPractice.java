package rahulshettyacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class AngularPractice extends AbstractComponent {
	
	WebDriver driver;

	public AngularPractice(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}

	//pageFactory
	@FindBy(xpath="//input[@name='name']")
	WebElement name;
	
	public SubmitPractice secondPage(String n) {
		name.sendKeys(n);
		return new SubmitPractice(driver);
		
	}
	
	
}

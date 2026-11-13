package rahulshettyacademy.stepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartCatalogue;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.PlaceOrder;
import rahulshettyacademy.pageobjects.ProductCatalog;

public class StepDefinitionImplementation extends BaseTest {
	public LandingPage landingpage;
	ProductCatalog productselect;
	CartCatalogue cartdetails;
	PlaceOrder order;
	OrderPage ordername;
    @Given("I land on the Website")
    public void I_land_on_the_website() throws IOException {
    	landingpage =launchApplication();
    }
    @Given ("^Login with username (.+) and Password (.+)$")
    public void logged_in_usernameand_password(String username, String password) {
    	productselect = landing.loginpage(username,password);
    }
    @When ("^I add product (.+) to cart$") 
    public void add_product_to_the_cart(String productName) {
    	cartdetails =productselect.addproducts(productName);
    }
    @ And ("^Checkout (.+) and sumbit the order$")
    public void checkout_submit_order (String productName) {
    	String actualProduct =cartdetails.cartDetails(productName);
    	Assert.assertEquals(productName, actualProduct);
         order = cartdetails.checkout();
        order.sendCountry("ind");
        order.countryNameSelect("India");
        order.placeOrder();
    }
    @Then ("Reference code for the product should be displayed") 
    public void reference_code_for_the_product_should_be_displayed () {
    	String ordernumberoutput= order.getOrderNumber();
        System.out.println(ordernumberoutput);
    }
    @When ("go to order page")
    public void goto_order_page () {
    	 ordername = landing.goToOrders();
    }
    @Then ("^Get the (.+)$")
    public void get_the_order_name(String product) {
    	String productName = ordername.getProductName();
		Assert.assertEquals(productName, product);
    }
}

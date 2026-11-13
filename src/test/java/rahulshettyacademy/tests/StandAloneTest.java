package rahulshettyacademy.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartCatalogue;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.PlaceOrder;
import rahulshettyacademy.pageobjects.ProductCatalog;

public class StandAloneTest extends BaseTest{

	
	@Test(dataProvider="getData")
	public void submitOrder(HashMap<String,String>input) throws IOException {
		// TODO Auto-generated method stub
		
		String countryselection ="India";
      //LandingPage landing=  launchApplication();
      ProductCatalog productselect = landing.loginpage(input.get("email"), input.get("password") );
      CartCatalogue cartdetails =productselect.addproducts(input.get("product")); 
      String actualProduct =cartdetails.cartDetails(input.get("product"));
     Assert.assertEquals(input.get("product"), actualProduct);
     PlaceOrder order = cartdetails.checkout();
     order.sendCountry("ind");
     order.countryNameSelect(countryselection);
     order.placeOrder();
     String ordernumberoutput= order.getOrderNumber();
     System.out.println(ordernumberoutput);
    
	}
	@Test(dependsOnMethods="submitOrder", dataProvider="getData")
	public void getOrderedProductName (HashMap<String,String>input) {
	    landing.loginpage(input.get("email"), input.get("password") );
		OrderPage ordername = landing.goToOrders();
		String productName = ordername.getProductName();
		Assert.assertEquals(productName, input.get("product"));
	}
	
	
		
	@DataProvider
	public Object[][] getData() throws IOException {
		
		 List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\rahulshettyacademy\\data\\SubmitOrder.json");
		return new Object[][] {
			{data.get(0)}, {data.get(1)}
		};
	}
} 
    
    
    
    
    


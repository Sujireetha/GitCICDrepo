Feature: Purchase products from Ecommerce Website

Background:
Given I land on the Website
@tag("Smoke")
Scenario Outline: Purchase the products with valid credentials
   Given Login with username <name> and Password <password>
   When I add product <productName> to cart
   And Checkout <productName> and sumbit the order
   Then Reference code for the product should be displayed 
   
   Examples:
    |name| |password| |productName|
    |shopper@gmail.com | |Shop@123| |ADIDAS ORIGINAL|
    
    Scenario Outline: Verify Ordered Product

  Given Login with username <name> and Password <password>
  When go to order page
  Then Get the <productName>

Examples:
| name              | password | productName      |
| shopper@gmail.com | Shop@123 | ADIDAS ORIGINAL  | 
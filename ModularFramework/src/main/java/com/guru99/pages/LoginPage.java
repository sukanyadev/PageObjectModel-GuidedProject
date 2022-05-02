package com.guru99.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage{

	
	@CacheLookup
	@FindBy (name="uid")
	private WebElement userId;
	
	@CacheLookup
	@FindBy (name="password")
	private WebElement userPassword;
	
	@CacheLookup
	@FindBy (name="btnLogin")
	private WebElement loginButton;
	
	public LoginPage (WebDriver driver) {
		
		super(driver) ;
		
		PageFactory.initElements(driver, this);
	}
	
	// @CacheLookup - if its a static element , then use this annotation 
	// @FindBy - basically the path to locate elements are provided
	// PageFactory - to initialize the web elements mentioned in this class
	// All the dynamic elements in the page , when initialized in normal way using driver.FindElements, it will throw an exception
	// Study more on Page factory and wrapper methods , Page object model for better understanding
	
	/* Adding logic to application layer */
	// Element control has to be initialized every time when adding logic for a particular page in the application
	// Instead that code can be maintained in a separate class
	// Login page extends Base page , then the functions in Element control can be called here
	
	public void logintoApplication (String username , String password) {
		
		elementControl.setText(userId, username);
		
		elementControl.setText(userPassword, password);
		
		elementControl.clickElement(loginButton);
	}
	
}


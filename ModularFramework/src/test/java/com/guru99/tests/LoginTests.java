package com.guru99.tests;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;


public class LoginTests extends BaseTests {

	
	@Parameters({"username","userPassword"})
	@Test
	public void verifyUserLogin_ValidCredentials (String username , String password) {
	
		reportUtils.createATestCase("Verify user login with correct credentials");
		
		reportUtils.addTestLog(Status.INFO, "Performing Login");
		
		// Invoking a browser , navigating to a URL , closing the browser can be maintained in a different class
		// These steps will be present in all the test cases
		
	loginPage.logintoApplication(username, password);
	
	String expectedTitle = "Guru99 Bank Manager HomePage";
	
	String actualTitle = comDriver.getTitleOfThePage();
	
	reportUtils.addTestLog(Status.INFO, "Comparing actual and expected title");
	
	Assert.assertEquals(actualTitle, expectedTitle);
	
	}
}

// To check for failed testcase, change the expected title text and try. Bcoz the assertion fails
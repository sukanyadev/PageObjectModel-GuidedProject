package com.guru99.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.internal.annotations.ITest;

import com.aventstack.extentreports.Status;
import com.guru99.pages.LoginPage;
import com.guru99.utils.ConfigUtils;
import com.guru99.utils.ReportUtils;
import com.guru99.utils.ScreenshotUtils;

import commonLibs.implementation.CommonDriver;

public class BaseTests {
	
	CommonDriver comDriver ; // initialize this class as it has all driver related logic is written
	
	// String url = "http://demo.guru99.com/v4"; Move this to Config properties file
	
	WebDriver driver ;
	String URL;
	LoginPage loginPage ;
	
	String configFileName;
	String currentWorkingDirectory;
	Properties configProperty;
	
	ReportUtils reportUtils;
	String reportFileName;
	
	ScreenshotUtils screenshot;
	
	@BeforeSuite
	public void preSetUp () throws Exception {
		
		currentWorkingDirectory = System.getProperty("user.dir");
		
		configFileName = currentWorkingDirectory + "/config/config.properties" ;
		
		reportFileName = currentWorkingDirectory + "/reports/guru99TestReport.html";
		
		configProperty = ConfigUtils.readProperties(configFileName);
		
		reportUtils = new ReportUtils(reportFileName);
		
	}
	
	@BeforeClass
	public void setUp () throws Exception {
		
		URL = configProperty.getProperty("baseUrl");
		
		String browserType = configProperty.getProperty("browesrTyoe");
		
		comDriver = new CommonDriver(browserType);
		
		driver = comDriver.getDriver();
		
		loginPage = new LoginPage (driver);
		
		screenshot = new ScreenshotUtils(driver); // can be initialized after driver has been initialized
		
		comDriver.navigateTourl(URL);
	}
	
	@AfterMethod
	public void postTestAction(ITestResult result) throws Exception {
		
		String testCaseName = result.getName();
		long executionTime = System.currentTimeMillis();
		
		String screenshotFileName = currentWorkingDirectory + "/screenshots/" + testCaseName +executionTime +".jpeg";
		
		if(result.getStatus()== ITestResult.FAILURE) {
			
			reportUtils.addTestLog(Status.FAIL, "One or more test steps failed");
			
			screenshot.captureAndSaveScreenshot(screenshotFileName);
			
			reportUtils.attachScreenshotToReport(screenshotFileName); // for attaching screenshot to the report
		}
	}
	
	
	@AfterClass
	public void tearDown () {
		
		comDriver.closeAllBrowser();
	}

	@AfterSuite
	public void closeReport() {
		
		reportUtils.flushReport();
		
	}
}

package com.guru99.utils;

import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ReportUtils {

	ExtentHtmlReporter htmlReport; // for HTML report
	
	ExtentReports extentReports; // to generate report
	
	ExtentTest extentTest; // to generate testcases
	
	public ReportUtils (String htmlReportFilename) {
		
		htmlReportFilename = htmlReportFilename.trim();
		
		htmlReport = new ExtentHtmlReporter(htmlReportFilename);
		
		extentReports = new ExtentReports ();
		
		extentReports.attachReporter(htmlReport); // tell the system we need to generate HTML report
	}
	public void createATestCase(String testCaseName) {
		
		extentTest = extentReports.createTest(testCaseName); // create a testcase
		
	}
	public void addTestLog(Status status, String comments) {
		
		extentTest.log(status, comments);
	}
	
	// For Adding screenshots to the reports, use the below method
	public void attachScreenshotToReport(String fileName) throws Exception {
		extentTest.addScreenCaptureFromPath(fileName);
	}
	
	
	
	public void flushReport () {
		
		extentReports.flush(); // will flush the report means it will close
		
	}
	
}

package com.guru99.utils;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtils {
	
	private TakesScreenshot camera ;
	
	public ScreenshotUtils (WebDriver driver) {
		
		camera = (TakesScreenshot) driver;
	// 	typecasting driver instance with TakeScreenshot, so that we can use same browser to take the screenshot
	}
	
	public void captureAndSaveScreenshot (String fileName) throws Exception {
		fileName = fileName.trim();
		
		File imgFile, tmpFile;
		imgFile = new File(fileName);
		if(imgFile.exists()) {
			throw new Exception ("File already exists");
		}
		tmpFile = camera.getScreenshotAs(OutputType.FILE);
		FileUtils.moveFile(tmpFile, imgFile);
	}

}

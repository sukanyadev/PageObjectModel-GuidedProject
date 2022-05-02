package commonLibs.implementation;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class CommonDriver {
	
	private WebDriver driver;
	private int pageLoadTimeout; 
	private int elementDetecttionTimeout; // implicit wait
	private String currentWorkingDirectory; 
//save the drivers in project folder and provide the relative path in the script instead of absolute path

	public CommonDriver(String browserType)throws Exception {
		pageLoadTimeout = 10;
		elementDetecttionTimeout = 10;
		currentWorkingDirectory = System.getProperty("user.dir");		
		if(browserType.equalsIgnoreCase("chrome")) {
			
			System.setProperty("webdriver.chrome.driver",currentWorkingDirectory + "/drivers/chromedriver.exe");
			driver = new ChromeDriver();
		}
		else if (browserType.equalsIgnoreCase("edge")) {
			
			System.setProperty("webdriver.edge.driver",currentWorkingDirectory + "/drivers/msedgedriver.exe");
			driver = new EdgeDriver();
		}
		else {
			throw new Exception("Invalid Browser Type" + browserType);
		}
		driver.manage().window().maximize();
		
		driver.manage().deleteAllCookies();
		
	}

	public void navigateTourl( String url) {
		
		driver.manage().timeouts().pageLoadTimeout(pageLoadTimeout, TimeUnit.SECONDS);
		
		driver.manage().timeouts().implicitlyWait(elementDetecttionTimeout, TimeUnit.SECONDS);
	    
		driver.get(url);
	}

	// 3 variable declared are Private , so we need to define the setters/getters so that anyone from outside can set the values
	public WebDriver getDriver() {
		return driver;
	}

	public void setPageLoadTimeout(int pageLoadTimeout) {
		this.pageLoadTimeout = pageLoadTimeout;
	}

	public void setElementDetecttionTimeout(int elementDetecttionTimeout) {
		this.elementDetecttionTimeout = elementDetecttionTimeout;
	}
	
	public String getTitleOfThePage() {
		return driver.getTitle();
	}
	public void closeAllBrowser() {
		driver.quit();
	}
}

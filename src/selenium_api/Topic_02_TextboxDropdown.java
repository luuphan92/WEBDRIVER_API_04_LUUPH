package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_TextboxDropdown {
    WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		
//		//Firefox <=47 + selenium
//		driver = new FirefoxDriver();
//		
//		//Firefox >=48 + selenium
//		System.setProperty("webdriver.gecko.driver",".\\driver\\geckodriver.exe" );
//		driver = new ChromeDriver();
		
		//Chrome
		System.setProperty("webdriver.chrome.driver",".\\driver\\chromedriver.exe" );
		driver = new ChromeDriver();
	
		//IE
		System.setProperty("webdriver.ie.driver",".\\driver\\IEDriverServer.exe" );
		driver = new InternetExplorerDriver();

		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://live.guru99.com/");
	}

	@Test
	public void TC_01_CheckTitleAndUrl() {
		String homePageTitle = driver.getTitle();
		Assert.assertEquals("Home page", homePageTitle);

		String homePageURL = driver.getCurrentUrl();
		Assert.assertEquals("http://live.guru99.com/", homePageURL);
	}

	@AfterClass
	public void afterClass() {
		// quit browser
		driver.quit();	}
}
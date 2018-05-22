package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_00_TestcaseTemplate {
    WebDriver driver;
    //System.setProperty("webdriver.chrome.driver",".\\driver\\chromedriver.exe");

	@BeforeClass
	public void beforeClass() {
		//Firefox <47
		driver = new FirefoxDriver();
		
		//Firefox >= 48
		System.setProperty("webdriver.Firefox.driver",".\\driver\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		//Chrome
		System.setProperty("webdriver.chrome.driver",".\\driver\\chromedriver.exe" );
		driver = new ChromeDriver();
		
		//IE
		System.setProperty("webdriver.IE.driver",".\\driver\\IEDriverServer.exe");
		driver = new InternetExplorerDriver();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://live.guru99.com/");
	}

	@Test
	public void TC_01() {
	}

	@Test
	public void TC_02() {
	}

//	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
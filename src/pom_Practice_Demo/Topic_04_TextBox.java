package pom_Practice_Demo;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.Topic_04_NewAccElement;

public class Topic_04_TextBox {
    WebDriver driver;
    //System.setProperty("webdriver.chrome.driver",".\\driver\\chromedriver.exe");

	@BeforeClass
	public void beforeClass() {
	
		//Chrome
		System.setProperty("webdriver.chrome.driver",".\\driver\\chromedriver.exe" );
		driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://daominhdam.890m.com/");
	}

	@Test
	public void dropDownTextbox() {
		// Check invisible
		
	}

	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
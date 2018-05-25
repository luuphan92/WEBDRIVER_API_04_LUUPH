package Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.Check_Element;


public class Check_Enable {
    WebDriver driver;
//	private static WebElement element = null;
   
	@BeforeClass
	public void beforeClass() {
		//Chrome
		System.setProperty("webdriver.chrome.driver",".\\driver\\chromedriver.exe" );
		driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://daominhdam.890m.com");
  }
	@Test
	  public void f() {
		   IsElementEnabled(Check_Element.mailElement(driver));
		  }

	  @AfterTest
	  public void afterTest() {
		  driver.quit();
	  }
	  
	  public boolean IsElementEnabled (WebElement myElement){
		 if(myElement.isEnabled()) {
			 System.out.print("here is something special ");
			 return true;
		 }else {
			 System.out.println("there is nothing happen");
			 return false;
		 }
		  
	  }

	}

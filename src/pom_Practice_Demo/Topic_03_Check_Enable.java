package pom_Practice_Demo;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.Topic_03_Check_Element;


public class Topic_03_Check_Enable {
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
	  public void Enable_Test() {
		   IsElementEnabled(Topic_03_Check_Element.mailElement(driver));
		   IsElementEnabled(Topic_03_Check_Element.ageElement(driver));
		   IsElementEnabled(Topic_03_Check_Element.eduElement(driver));
		   IsElementEnabled(Topic_03_Check_Element.slideElement(driver));
		   IsElementEnabled(Topic_03_Check_Element.checkboxElement(driver));
		   IsElementEnabled(Topic_03_Check_Element.buttonElement(driver));
		   IsElementEnabled(Topic_03_Check_Element.passElement(driver));
		   
		  }

	  @AfterTest
	  public void afterTest() {
		  driver.quit();
	  }
	  
	  public boolean IsElementEnabled (WebElement myElement){
		 if(myElement.isEnabled()) {
			 System.out.println("Element is enabled");
			 return true;
		 }else {
			 System.out.println(" Element is disabled");
			 return false;
		 }
		  
	  }

	}

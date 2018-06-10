package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_ButtonRadoibuttonCheckboxAlert {
    WebDriver driver;
    //System.setProperty("webdriver.chrome.driver",".\\driver\\chromedriver.exe");

	@BeforeClass
	public void beforeClass() {
//		//Firefox <47
//		driver = new FirefoxDriver();
//		
//		//Firefox >= 48
//		System.setProperty("webdriver.Firefox.driver",".\\driver\\geckodriver.exe");
//		driver = new FirefoxDriver();
		
		//Chrome
		System.setProperty("webdriver.chrome.driver",".\\driver\\chromedriver.exe" );
		driver = new ChromeDriver();
		
//		//IE
//		System.setProperty("webdriver.IE.driver",".\\driver\\IEDriverServer.exe");
//		driver = new InternetExplorerDriver();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://live.guru99.com/");
	}

	@Test
	public void TC_01_Button() {
		driver.get("http://live.guru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		Assert.assertEquals("http://live.guru99.com/index.php/customer/account/login/", driver.getCurrentUrl());
		
		WebElement createAnAccountButton = driver.findElement(By.xpath("//span[text()='Create an Account']"));
		clickElementByJavascript(createAnAccountButton);
		Assert.assertEquals("http://live.guru99.com/index.php/customer/account/create/", driver.getCurrentUrl());
	}

	@Test
	public void TC_02_CheckBox() {
		driver.get("http://demos.telerik.com/kendo-ui/styling/checkboxes");
		WebElement dualZoneRadioButton = driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input"));
		clickElementByJavascript(dualZoneRadioButton);
		Assert.assertTrue(dualZoneRadioButton.isSelected());
		unCheckTheCheckBox(dualZoneRadioButton);
	}
	
	@Test
	public void TC_03_RadioButton() {
		driver.get("http://demos.telerik.com/kendo-ui/styling/radios");
		WebElement radioButton = driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input"));
		clickElementByJavascript(radioButton);
		Assert.assertTrue(radioButton.isSelected());
		checkTheRadioButton(radioButton);
	}
	
	@Test
	public void TC_04_Alert() {
		driver.get("http://daominhdam.890m.com/");
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		//Khai bao alert
		Alert alert = driver.switchTo().alert();
		alert.accept();
		WebElement reSult = driver.findElement(By.xpath("//p[@id='result']"));
		Assert.assertEquals("You clicked an alert successfully",reSult.getText() );
	}
	
	@Test
	public void TC_05_CancelAlert() {
		driver.get("http://daominhdam.890m.com/");
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		//Khai bao alert
		Alert alert = driver.switchTo().alert();
		alert.dismiss();
		WebElement reSult = driver.findElement(By.xpath("//p[@id='result']"));
		Assert.assertEquals("You clicked: Cancel",reSult.getText() );
	}
	
	@Test
	public void TC_06_CancelPrompt() {
		driver.get("http://daominhdam.890m.com/");
		String name = "Automation Testing";
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		//Khai bao alert
		Alert alert = driver.switchTo().alert();
		alert.sendKeys(name);
		alert.accept();
		WebElement reSult = driver.findElement(By.xpath("//p[@id='result']"));
		Assert.assertEquals("You entered: " + name,reSult.getText());
	}

	public void clickElementByJavascript(WebElement element) {
	    JavascriptExecutor je = (JavascriptExecutor) driver;
	    je.executeScript("arguments[0].click();", element);
	}
	public void unCheckTheCheckBox(WebElement element) {
		if(element.isSelected()) {
		    JavascriptExecutor je = (JavascriptExecutor) driver;
		    je.executeScript("arguments[0].click();", element);
			Assert.assertTrue(!element.isSelected());
		}
	}
	
	public void checkTheRadioButton(WebElement element) {
		if(element.isSelected()) {
		    JavascriptExecutor je = (JavascriptExecutor) driver;
		    je.executeScript("arguments[0].click();", element);
			Assert.assertTrue(element.isSelected());
		}
	}



	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
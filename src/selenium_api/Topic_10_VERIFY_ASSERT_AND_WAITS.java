package selenium_api;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Function;



public class Topic_10_VERIFY_ASSERT_AND_WAITS {
    WebDriver driver;
    WebDriverWait wait;
    
    //System.setProperty("webdriver.chrome.driver",".\\driver\\chromedriver.exe");

	@BeforeClass
	public void beforeClass() {
//		//Firefox <47
//		driver = new FirefoxDriver();
//		
//		//Firefox >= 48
//		System.setProperty("webdriver.Firefox.driver",".\\driver\\geckodriver.exe");
//		driver = new FirefoxDriver();
//		
		//Chrome
		System.setProperty("webdriver.chrome.driver",".\\driver\\chromedriver.exe" );
		driver = new ChromeDriver();
		
//		//IE
//		System.setProperty("webdriver.IE.driver",".\\driver\\IEDriverServer.exe");
//		driver = new InternetExplorerDriver();
		 
		wait = new WebDriverWait(driver, 30);
		
	}

	@Test
	public void TC_01_ImplicitWait() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		//Wait ngam dinh
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		WebElement startBtn = driver.findElement(By.xpath("//div[@id='start']"));
		startBtn.click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//h4[text()='Hello World!']")).isDisplayed());
		
	}

	@Test
	public void TC_02_ExplicitWait() {
	driver.get("http://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");	
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	
	WebElement dateTimePicker = driver.findElement(By.xpath("//div[@id='ctl00_ContentPlaceholder1_Panel1']"));
	wait.until(ExpectedConditions.visibilityOf(dateTimePicker));
	
	WebElement dateSelectedBefore = driver.findElement(By.xpath("//*[@id = 'ctl00_ContentPlaceholder1_Label1']"));
	String textBefore = dateSelectedBefore.getText().trim();
	Assert.assertEquals("No Selected Dates to display.", textBefore);
	
	WebElement today = driver.findElement(By.xpath("//a[text()='21']"));
	today.click();
	
	By ajaxIcon = By.xpath("//div[@class='raDiv']");
	wait.until(ExpectedConditions.invisibilityOfElementLocated(ajaxIcon));
	
	WebElement todaySelected = driver.findElement(By.xpath("//td[@class='rcSelected']/a[text()='21']"));
	Assert.assertTrue(todaySelected.isDisplayed());
	
	WebElement dateSelectedAfter = driver.findElement(By.xpath("//*[@id = 'ctl00_ContentPlaceholder1_Label1']"));
	String textAfter = dateSelectedAfter.getText().trim();
	Assert.assertEquals("Thursday, June 21, 2018", textAfter);
	
	

	
	
	
	
	
	
		
	}

	@Test
	public void TC_03_FluentWait01() {
		driver.get("https://stuntcoders.com/snippets/javascript-countdown/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		WebElement countdown = driver.findElement(By.xpath("//div[@id='javascript_countdown_time']"));
		wait.until(ExpectedConditions.visibilityOf(countdown));
		
		new FluentWait<WebElement>(countdown)
		.withTimeout(15, TimeUnit.SECONDS)
		.ignoring(NoSuchElementException.class)
		.until(new Function<WebElement, Boolean>() {
			public Boolean apply(WebElement element) {
				boolean flag = element.getText().endsWith("00");
				System.out.println("Time = " + element.getText());
				return flag;
			}
		});	
	}

	@Test
	public void TC_04_FluentWait02() {
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
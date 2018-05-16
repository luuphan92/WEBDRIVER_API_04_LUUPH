package selenium_api;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_XpathCss {
    WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://live.guru99.com/");
	}

	@Test
	public void TC_02_Login_Empty() {
	driver.findElement(By.xpath("//div[@class='footer']//a[contains(text(),'My Account')]")).click();
	driver.findElement(By.xpath("//input[@id='email']")).sendKeys("");
	driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("");
	driver.findElement(By.xpath("//button[@id='send2']")).click();
	
	String emailErrorMsg = driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText();
	Assert.assertEquals("This is a required field.", emailErrorMsg);
	
	String passErrorMsg = driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText();
	Assert.assertEquals("This is a required field.", passErrorMsg);
	}

	@Test
	public void TC_03_Login_with_email_invalid() {
	driver.findElement(By.xpath("//div[@class='footer']//a[contains(text(),'My Account')]")).click();
	driver.findElement(By.xpath("//input[@id='email']")).sendKeys("23434234@12312.123123");
	driver.findElement(By.xpath("//button[@id='send2']")).click();
		
	String emailValidateMsg = driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).getText();
	Assert.assertEquals("Please enter a valid email address. For example johndoe@domain.com.", emailValidateMsg);
	}
	
	@Test
	public void TC_04_Login_with_pass_incorrect() {
	driver.findElement(By.xpath("//div[@class='footer']//a[contains(text(),'My Account')]")).click();
	driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");
	driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123");
	driver.findElement(By.xpath("//button[@id='send2']")).click();
		
	String passIncorrect = driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).getText();
	Assert.assertEquals("Please enter 6 or more characters without leading or trailing spaces.", passIncorrect);
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
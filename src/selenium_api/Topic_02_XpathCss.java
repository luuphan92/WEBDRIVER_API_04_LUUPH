package selenium_api;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_XpathCss {
	WebDriver driver;
	private String email;

	// Random number for email
	public int randomNumber() {
		Random random = new Random();
		int number = random.nextInt(99999);
		return number;
	}

	@BeforeClass
	public void beforeClass() {
		// Chrome
		System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://live.guru99.com/");
		// Email form
		email = "automation" + randomNumber() + "@gmail.com";
	}

	@Test
	public void TC_01_VerifyURLandTitle() throws Exception {
		driver.get("http://live.guru99.com/");
		Thread.sleep(3000);

		String homepageTitle = driver.getTitle();
		Assert.assertEquals("Home page", homepageTitle);
		Thread.sleep(3000);

		WebElement myAccountLink = driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']"));
		myAccountLink.click();
		Thread.sleep(3000);

		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		Thread.sleep(3000);

		driver.navigate().back();
		Thread.sleep(3000);

		String loginUrl = driver.getCurrentUrl();
		Assert.assertEquals("http://live.guru99.com/index.php/customer/account/login/", loginUrl);
		Thread.sleep(3000);

		driver.navigate().forward();
		Thread.sleep(3000);

		String creatAccountUlr = driver.getCurrentUrl();
		Assert.assertEquals("http://live.guru99.com/index.php/customer/account/create/", creatAccountUlr);

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

	@Test
	public void TC_05_CreateAccount() throws Exception  {
		WebElement myAccountLink = driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']"));
		myAccountLink.click();
		
		// Click Create Account
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

		// Input name
		driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys("Phan");
		driver.findElement(By.xpath("//input[@id='middlename']")).sendKeys("Hoang");
		driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys("Luu");

		// Input random email
		WebElement emailTxt = driver.findElement(By.xpath("//input[@id='email_address']"));
		emailTxt.sendKeys(email);

		// Input password
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys("123456");

		// Click Register button
		driver.findElement(By.xpath("//form[@id='form-validate']//button[@class='button']")).click();

		// Check message
		WebElement successMsg = driver.findElement(By.xpath("//span[text()='Thank you for registering with Main Website Store.']"));
		Assert.assertTrue(successMsg.isDisplayed());

		// Log out
		driver.findElement(By.xpath("//div[@class='skip-links']//a[@class='skip-link skip-account']")).click();
		driver.findElement(By.xpath("//div[@id='header-account']//a[text()='Log Out']")).click();
		Thread.sleep(5000);

		// Check Home page
		String homepageTitle = driver.getTitle();
		Assert.assertEquals("Home page", homepageTitle);
	}

	@AfterClass
	// Close tab
	public void afterClass() {
		driver.quit();
	}
}
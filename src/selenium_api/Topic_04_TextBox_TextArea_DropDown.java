package selenium_api;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.thoughtworks.selenium.webdriven.commands.FindFirstSelectedOptionProperty;

public class Topic_04_TextBox_TextArea_DropDown {
	WebDriver driver;
	String username, pass, name, dob, address, city, state, pin, mobi, email, password, customerID, newAddress, newCity;

	@BeforeClass
	public void beforeClass() {
//		//Firefox <47
//		driver = new FirefoxDriver();

		// Chrome
		System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
		driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		username = "mngr135045";
		pass = "adadumy";
		name = "Automation Test";
		dob = "1999-11-05";
		address = "77 CD9";
		city = "HCM";
		state = "Quan bay";
		pin = "123456";
		mobi = "0123456789";
		email = "automation" + randomNumber() + "@gmail.com";
		password = "123456";
		newAddress = "10 Pho Quang";
		newCity = "Da Nang";		
	}
	
	@Test
	public void TC_01_DropdownCheck(){
		//Step 1
		driver.get("http://daominhdam.890m.com/");
		//Step 2
		Select dropDown = new Select(driver.findElement(By.xpath("//select[@id='job1']")));
		Assert.assertFalse(dropDown.isMultiple());
		//Step 3
		dropDown.selectByVisibleText("Automation Tester");
		//Step 4
		Assert.assertEquals("Automation Tester", dropDown.getFirstSelectedOption().getText());
		//Step 5
		dropDown.selectByValue("manual");
		//Step 6
		dropDown.selectByIndex(3);
		//Step 8
		Assert.assertEquals("Mobile Tester", dropDown.getFirstSelectedOption().getText());
		//Step 9
		Assert.assertEquals(5, dropDown.getOptions().size());
	}
	
	@Test
	public void TC_02_CreateNewCus(){
		//Step 1
		driver.get("http://demo.guru99.com/v4");
		//Step 2
		WebElement userTextbox = driver.findElement(By.xpath("//input[@name = 'uid']"));
		WebElement passTextbox = driver.findElement(By.xpath("//input[@name = 'password']"));
		userTextbox.sendKeys(username);
		passTextbox.sendKeys(pass);
		WebElement loginBtn = driver.findElement(By.xpath("//input[@name = 'btnLogin']"));
		loginBtn.click();
		//Step 3
		WebElement newCustomerLink = driver.findElement(By.xpath("//a[text() = 'New Customer']"));
		newCustomerLink.click();
		//Step 4
		WebElement nameTextbox = driver.findElement(By.xpath("//input[@name = 'name']"));
		nameTextbox.sendKeys(name);
		WebElement femaleRatio = driver.findElement(By.xpath("//input[@value = 'f']"));
		femaleRatio.click();
		WebElement dobTextbox = driver.findElement(By.xpath("//input[@name = 'dob']"));
		removeAttributeInDOM(dobTextbox, "type");
		dobTextbox.sendKeys(dob);
		WebElement addrTextarea = driver.findElement(By.xpath("//textarea [@name = 'addr']"));
		addrTextarea.sendKeys(address);
		WebElement cityTextbox = driver.findElement(By.xpath("//input[@name = 'city']"));
		cityTextbox.sendKeys(city);
		WebElement stateTextbox = driver.findElement(By.xpath("//input[@name = 'state']"));
		stateTextbox.sendKeys(state);
		WebElement pinTextbox = driver.findElement(By.xpath("//input[@name = 'pinno']"));
		pinTextbox.sendKeys(pin);
		WebElement mobiTextbox = driver.findElement(By.xpath("//input[@name = 'telephoneno']"));
		mobiTextbox.sendKeys(mobi);
		WebElement emailTextbox = driver.findElement(By.xpath("//input[@name = 'emailid']"));
		emailTextbox.sendKeys(email);
		WebElement passwordTextbox = driver.findElement(By.xpath("//input[@name = 'password']"));
		passwordTextbox.sendKeys(password);
		
		WebElement submitButton = driver.findElement(By.xpath("//input[@name = 'sub']"));
		submitButton.click();
		
		WebElement createddSuccessMsg = driver.findElement(By.xpath("//p[@class='heading3' and text()='Customer Registered Successfully!!!']"));
		createddSuccessMsg.isDisplayed();
		
		customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
		
		//Step 5 get text data inputed
		Assert.assertEquals(name, driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText());
		Assert.assertEquals("female", driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText());
		Assert.assertEquals(dob, driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText());
		Assert.assertEquals(address, driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText());
		Assert.assertEquals(city, driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText());
		Assert.assertEquals(state, driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText());
		Assert.assertEquals(pin, driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText());
		Assert.assertEquals(mobi, driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText());
		Assert.assertEquals(email, driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText());
		
		//Step 6 Click Edit button
		WebElement editButton = driver.findElement(By.xpath("//a[text() = 'Edit Customer']"));
		editButton.click();
		
		//Input Customer ID
		WebElement customerIDTextbox = driver.findElement(By.xpath("//input[@name = 'cusid']"));
		customerIDTextbox.sendKeys(customerID);
		WebElement accSubmitBtn = driver.findElement(By.xpath("//input[@name = 'AccSubmit']"));
		accSubmitBtn.click();
		
		//Step 7 Verify Customer Name, Address
		WebElement editCusName = driver.findElement(By.xpath("//input[@name='name']"));		
		WebElement editAddr = driver.findElement(By.xpath("//textarea[@name='addr']"));
		Assert.assertEquals(name, editCusName.getAttribute("Value"));
		Assert.assertEquals(address, editAddr.getText());
		
		//Edit address, city
		editAddr.clear();
		editAddr.sendKeys(newAddress);
		WebElement editCityTextbox = driver.findElement(By.xpath("//input[@name = 'city']"));
		editCityTextbox.clear();
		editCityTextbox.sendKeys(newCity);
		WebElement submitAgaintButton = driver.findElement(By.xpath("//input[@name = 'sub']"));
		submitAgaintButton.click();		
		Assert.assertEquals(newAddress, driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText());
		Assert.assertEquals(newCity, driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText());
		
	}

	 @AfterClass
	public void afterClass() {
		driver.quit();
	}
	private int randomNumber() {
		Random random = new Random();
		int number = random.nextInt(999999);
		return number;
	}

	private void removeAttributeInDOM(WebElement element, String attributeName) {
		JavascriptExecutor javascript = (JavascriptExecutor) driver;
		javascript.executeScript( "arguments[0].removeAttribute('" + attributeName + "')" ,element );
	}
}
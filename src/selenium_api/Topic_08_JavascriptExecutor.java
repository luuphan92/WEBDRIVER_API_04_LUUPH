package selenium_api;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_JavascriptExecutor {
	WebDriver driver;
	private String email;

	@BeforeClass
	public void beforeClass() {
		// //Chrome
		System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
		driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		email = "automation" + randomNumber() + "@gmail.com";

	}

	@Test(enabled = false)
	public void TC_01() {
		driver.get("http://live.guru99.com/");
		String domain = (String) executeJSForBrowserElement("return document.domain;");
		Assert.assertEquals("live.guru99.com", domain);

		String url = (String) executeJSForBrowserElement("return document.URL;");
		Assert.assertEquals("http://live.guru99.com/", url);

		WebElement mobilePage = driver.findElement(By.xpath("//a[text()='Mobile']"));
		clickByJSForWebElement(mobilePage);

		WebElement samsungProduct = driver.findElement(
				By.xpath("//h2[a[contains(.,'Samsung Galaxy')]]/following-sibling::div[@class='actions']//button"));
		clickByJSForWebElement(samsungProduct);

		String addToCartMsg = (String) executeJSForBrowserElement("return document.documentElement.innerText;");
		Assert.assertTrue(addToCartMsg.contains("Samsung Galaxy was added to your shopping cart."));

		WebElement privacyPage = driver.findElement(By.xpath("//a[text()='Privacy Policy']"));
		clickByJSForWebElement(privacyPage);

		String privacyTitle = (String) executeJSForBrowserElement("return document.title;");
		Assert.assertEquals("Privacy Policy", privacyTitle);

		scrollToBottomPage();

		WebElement wishlist = driver.findElement(By.xpath(
				"//th[text()='WISHLIST_CNT']/following-sibling::td[text()='The number of items in your Wishlist.']"));
		Assert.assertTrue(wishlist.isDisplayed());

		String demoGuruSite = "http://demo.guru99.com/v4/";
		executeJSForBrowserElement("window.location = '" + demoGuruSite + "'");
		String domainDemoGuru = (String) executeJSForBrowserElement("return document.domain;");
		Assert.assertEquals("demo.guru99.com", domainDemoGuru);

	}

	@Test(enabled = false)
	public void TC_02() throws Exception {
		driver.get("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_input_disabled");
		Thread.sleep(3000);
		WebElement iframeResult = driver.findElement(By.xpath("//iframe[@id='iframeResult']"));
		driver.switchTo().frame(iframeResult);

		WebElement lastNameTextbox = driver.findElement(By.xpath("//input[@name='lname']"));
		removeAttributeInDOM(lastNameTextbox, "disabled");
		lastNameTextbox.sendKeys("Automation");

		WebElement submitBtn = driver.findElement(By.xpath("//input[@value='Submit']"));
		submitBtn.click();
		Thread.sleep(3000);
		WebElement lastnameSuccess = driver.findElement(By.xpath("//div[contains(text(),'Automation')]"));
		Assert.assertTrue(lastnameSuccess.isDisplayed());

	}

	@Test
	public void TC_03_CreatAnAcc() {
		driver.get("http://live.guru99.com/");
		WebElement myAccount = driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']"));
		clickByJSForWebElement(myAccount);

		WebElement creatAccBtn = driver.findElement(By.xpath("//span[text()='Create an Account']"));
		clickByJSForWebElement(creatAccBtn);

		WebElement firstNameTextbox = driver.findElement(By.xpath("//input[@id='firstname']"));
		sendkeyToElementByJS(firstNameTextbox, "phan");

		WebElement midleNameTextbox = driver.findElement(By.xpath("//input[@class='input-text ']"));
		sendkeyToElementByJS(midleNameTextbox, "hoang");

		WebElement lastNameTextbox = driver.findElement(By.xpath("//input[@id='lastname']"));
		sendkeyToElementByJS(lastNameTextbox, "luu");

		WebElement emailTextbox = driver.findElement(By.xpath("//input[@id='email_address']"));
		sendkeyToElementByJS(emailTextbox, email);

		WebElement passwordTextbox = driver.findElement(By.xpath("//input[@id='password']"));
		sendkeyToElementByJS(passwordTextbox, "123456");

		WebElement repasswordTextbox = driver.findElement(By.xpath("//input[@id='confirmation']"));
		sendkeyToElementByJS(repasswordTextbox, "123456");

		WebElement submitBtn = driver.findElement(By.xpath("//button[@title='Register']"));
		clickByJSForWebElement(submitBtn);

		executeJSForBrowserElement("return document.documentElement.innerText;");
		String thanksText = (String) executeJSForBrowserElement("return document.documentElement.innerText;");
		Assert.assertTrue(thanksText.contains("Thank you for registering with Main Website Store."));

	}

	public Object executeJSForBrowserElement(String javaSript) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			return js.executeScript(javaSript);
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	public Object clickByJSForWebElement(WebElement element) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			return js.executeScript("arguments[0].click();", element);
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	public void highlightElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.border='6px groove red'", element);
	}

	public Object removeAttributeInDOM(WebElement element, String attribute) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			return js.executeScript("arguments[0].removeAttribute('" + attribute + "');", element);
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	public Object scrollToBottomPage() {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			return js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	public Object sendkeyToElementByJS(WebElement element, String value) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			return js.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	public int randomNumber() {
		Random random = new Random();
		int number = random.nextInt(999999);
		return number;
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
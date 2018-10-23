package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_JavascriptExecutor {
	WebDriver driver;
	WebDriverWait wait;

	@BeforeClass
	public void beforeClass() {
		// //Firefox <47
		// driver = new FirefoxDriver();
		//
		// //Firefox >= 48
		// System.setProperty("webdriver.Firefox.driver",".\\driver\\geckodriver.exe");
		// driver = new FirefoxDriver();
		//
		// //Chrome
		System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
		driver = new ChromeDriver();

		// //IE
		// System.setProperty("webdriver.IE.driver",".\\driver\\IEDriverServer.exe");
		// driver = new InternetExplorerDriver();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
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

	@Test
	public void TC_02() throws Exception {
		driver.get("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_input_disabled");
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

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
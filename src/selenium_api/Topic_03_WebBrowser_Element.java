package selenium_api;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_WebBrowser_Element {
    WebDriver driver;
    //System.setProperty("webdriver.chrome.driver",".\\driver\\chromedriver.exe");

	@BeforeClass
	public void beforeClass() {
		//Firefox <47
		driver = new FirefoxDriver();
		
//		//Firefox >= 48
//		System.setProperty("webdriver.Firefox.driver",".\\driver\\geckodriver.exe");
//		driver = new FirefoxDriver();
		
//		//Chrome
//		System.setProperty("webdriver.chrome.driver",".\\driver\\chromedriver.exe" );
//		driver = new ChromeDriver();
		
//		//IE
//		System.setProperty("webdriver.IE.driver",".\\driver\\IEDriverServer.exe");
//		driver = new InternetExplorerDriver();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://daominhdam.890m.com/");
	}

	@Test
	public void TC_01_ElementIsDisplayedOnPage() {
		String mailelement = "//input[@id='mail']";
		if (isElementDisplayed(driver, mailelement)) {
			driver.findElement(By.xpath("//input[@id='mail']")).sendKeys("Automation Testing");
		}

		String ageElement = "//input[@id='under_18']";
		if (isElementDisplayed(driver, ageElement)) {
			driver.findElement(By.xpath("//input[@id='under_18']")).click();
		}

		String eduElement = "//textarea[@id='edu']";
		if (isElementDisplayed(driver, eduElement)) {
			driver.findElement(By.xpath("//textarea[@id='edu']")).sendKeys("Automation Testing");
		}		
	}

	@Test
	public void TC_02_CheckEnableDisableElementOnPage() {
		String mailelement = "//input[@id='mail']";
		if (isElementEnable(driver, mailelement)) {
		System.out.println("Email is enabled");
		} else {
		System.out.println("Email is disable");	
		}

		String ageElement = "//input[@id='under_18']";
		if (isElementEnable(driver, ageElement)) {
			System.out.println("Age (Under 18) is enabled");
			} else {
			System.out.println("Age (Under 18) is disable");	
			}

		String eduElement = "//textarea[@id='edu']";
		if (isElementEnable(driver, eduElement)) {
			System.out.println("Education is enabled");
			} else {
			System.out.println("Education is disable");	
			}

		String job1Element = "//select[@id='job1']";
		if (isElementEnable(driver, job1Element)) {
			System.out.println("Job Role 01 is enabled");
			} else {
			System.out.println("Job Role 01 is disable");	
			}
		String checkboxElement = "//input[@id='development']";
		if (isElementEnable(driver, checkboxElement)) {
			System.out.println("Interests (Development) is enabled");
			} else {
			System.out.println("Interests (Development) is disable");	
			}
		String slideElement = "//input[@id='slider-1']";
		if (isElementEnable(driver, slideElement)) {
			System.out.println("Slider 01 is enabled");
			} else {
			System.out.println("Slider 01 is disable");	
			}

		String buttonElement = "//button[@id='button-enabled']";
		if (isElementEnable(driver, buttonElement)) {
			System.out.println("Button is enabled is enabled");
			} else {
			System.out.println("Button is enabled is disable");	
			}
		String passElement = "//input[@id='password']";
		if (isElementEnable(driver, passElement)) {
			System.out.println("Password  is enabled");
			} else {
			System.out.println("Password  is disable");	
			}
		String age2Element = "//input[@id='radio-disabled']";
		if (isElementEnable(driver, age2Element)) {
			System.out.println("Age (Radiobutton is disabled) is enabled");
			} else {
			System.out.println("Age (Radiobutton is disabled) is disable");	
			}
		String job2Element = "//select[@id='job2']";
		if (isElementEnable(driver, job2Element)) {
			System.out.println("Job Role 02 is enabled");
			} else {
			System.out.println("Job Role 02 is disable");	
			}
		String bioElement = "//textarea[@id='bio']";
		if (isElementEnable(driver, bioElement)) {
			System.out.println("Biography is enabled");
			} else {
			System.out.println("Biography is disable");	
			}
		String job2Element1 = "//select[@id='job2']";
		if (isElementEnable(driver, job2Element1)) {
			System.out.println("Element is enabled");
			} else {
			System.out.println("Element is disable");	
			}
		String interestElement = "//*[@id='button-enabled']";
		if (isElementEnable(driver, interestElement)) {
			System.out.println("Interests (Checkbox is disabled) is enabled");
			} else {
			System.out.println("Interests (Checkbox is disabled) is disable");	
			}
		String slide2Element = "//input[@id='slider-2']";
		if (isElementEnable(driver, slide2Element)) {
			System.out.println("Slider 02 is enabled");
			} else {
			System.out.println("Slider 02 is disable");	
			}

		String disbuttonElement = "//*[@id='button-disabled']";
		if (isElementEnable(driver, disbuttonElement)) {
			System.out.println("Button is disabled is enabled");
			} else {
			System.out.println("Button is disabled is disable");	
			}
	}

	@Test
	public void TC_03_CheckSelectedElementOnPage() throws Exception {
		String ageElement = "//*[@id='under_18']";
		 driver.findElement(By.xpath(ageElement)).click();
		 Thread.sleep(2000);
		 if(isElementSelected(driver, ageElement)){
		 System.out.println("'Under 18' radio button is selected");
		 } else{
			 driver.findElement(By.xpath(ageElement)).click();
		 }
		 
		 String interestElement = "//input[@id='development']";
		 driver.findElement(By.xpath(interestElement)).click();
		 Thread.sleep(2000);
		 if(isElementSelected(driver, interestElement)){
			 System.out.println("'Interest' radio button is selected");
		 } else{
			 driver.findElement(By.xpath(interestElement)).click();
		 }
	}
	public boolean isElementDisplayed(WebDriver driver, String yourLocator) {
		 try {
		 WebElement locator;
		 locator = driver.findElement(By.xpath(yourLocator));
		 return locator.isDisplayed();
		 } catch (NoSuchElementException e) {
		 return false;
		 }
	}
	public boolean isElementEnable(WebDriver driver, String yourLocator) {
		try {
		WebElement locator;	
		locator = driver.findElement(By.xpath(yourLocator));
		return locator.isEnabled();
		} catch (NoSuchElementException e) {
		return false;
		}
	}
	 public boolean isElementSelected(WebDriver driver, String yourLocator) {
		 try {
		 WebElement locator;
		 locator = driver.findElement(By.xpath(yourLocator));
		 return locator.isSelected();
		 } catch (NoSuchElementException e) {
		 return false;
		 }
}@AfterClass
	// Close tab
	public void afterClass() {
		driver.quit();
	}
}
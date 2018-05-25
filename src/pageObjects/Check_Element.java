package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Check_Element {
//	WebDriver driver;
//	By mail = By.xpath("//input[@id='password']");
//	By age = By.xpath("//input[@id='under_18' and @type='radio']");
//	
//	public Check_Element(WebDriver driver) {
//		this.driver = driver;
//	}
//	public void mailElement(WebDriver driver) {
//		driver.findElement(mail);
//	}
//	public void ageElement(WebDriver driver) {
//		driver.findElement(age);
	private static WebElement element = null;

	public static WebElement mailElement(WebDriver driver) {
		element = driver.findElement(By.xpath("//input[@id='password']"));
		return element;
	}
	public static WebElement ageElement(WebDriver driver) {
		element = driver.findElement(By.xpath("//input[@id='password']"));
		return element;
	}

}

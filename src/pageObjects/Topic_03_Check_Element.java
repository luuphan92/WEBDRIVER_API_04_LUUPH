package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Topic_03_Check_Element {
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
	public static WebElement eduElement(WebDriver driver) {
		element = driver.findElement(By.xpath("//input[@id='development']"));
		return element;
	}
	public static WebElement slideElement(WebDriver driver) {
		element = driver.findElement(By.xpath("//input[@id='slider-1']"));
		return element;
	}
	public static WebElement checkboxElement(WebDriver driver) {
		element = driver.findElement(By.xpath("//select[@id='job1']"));
		return element;
	}
	public static WebElement buttonElement(WebDriver driver) {
		element = driver.findElement(By.xpath("//button[@id='button-enabled']"));
		return element;
	}
	public static WebElement passElement(WebDriver driver) {
		element = driver.findElement(By.xpath("//input[@id='password']"));
		return element;
	}

}

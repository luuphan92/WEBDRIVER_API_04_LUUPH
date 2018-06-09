package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class Topic_04_NewAccElement {
	private static WebDriver driver = null;
	
	public Topic_04_NewAccElement(WebDriver driver) {
		this.driver = driver;
	}
	
	public Select dropDown(WebDriver driver){
		Select dropDown = new Select(driver.findElement(By.xpath("//select[@id='job1']")));
		return dropDown;
	}
}


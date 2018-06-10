package selenium_api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_UserInteractions {
    WebDriver driver;
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
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		

	}

	@Test
	public void TC_011_MoveMouseToElement() {
		driver.get("http://daominhdam.890m.com/");
		WebElement hoverLink = driver.findElement(By.xpath("//a[text()='Hover over me']"));

		Actions action = new Actions(driver);
		//Hover mouse
		action.moveToElement(hoverLink).perform();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='tooltip-inner' and text()='Hooray!']")).isDisplayed());
		Assert.assertEquals("Hooray!", driver.findElement(By.xpath("//div[@class='tooltip-inner' and text()='Hooray!']")).getText());
		
		
		
		driver.findElement(By.xpath("//a[text()='Hover over me']")).click();
	}

	@Test
	public void TC_012_MouseToLogin() {
		driver.get("http://www.myntra.com/");
		WebElement hoverMenu = driver.findElement(By.xpath("//span[@class='myntraweb-sprite desktop-iconUser sprites-user']"));
		Actions action = new Actions(driver);
		action.moveToElement(hoverMenu).perform();
		
		driver.findElement(By.xpath("//a[text()='login']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Login to Myntra']")).isDisplayed());
		
	}
	
	@Test
	public void TC_021_ClickAndHoleElement() {
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");		
		List<WebElement> listNumbers = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		listNumbers.get(0);
		
		Actions action = new Actions(driver);
		action.clickAndHold(listNumbers.get(0)).moveToElement(listNumbers.get(3)).release().perform();
		
		List<WebElement> numberSelected = driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]"));
		int number = numberSelected.size();
		Assert.assertEquals(4, number);
		
	}	
	
	@Test
	public void TC_022_ClickAndHoleRandom() {
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");		
		List<WebElement> listNumbers = driver.findElements(By.xpath("//*[@id='selectable']/li"));
		Actions action = new Actions(driver);
		action.keyDown(Keys.CONTROL).build().perform();
		listNumbers.get(0).click();
		listNumbers.get(2).click();
		listNumbers.get(4).click();
		listNumbers.get(6).click();
		action.keyUp(Keys.CONTROL).build().perform();
		
	}	
	
	@Test
	public void TC_03_DoubleClick() {
		driver.get("http://www.seleniumlearn.com/double-click");
		WebElement doubleClickMe = driver.findElement(By.xpath("//button[text()='Double-Click Me!']"));
		Actions action = new Actions(driver);
		action.doubleClick(doubleClickMe).perform();
		
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("The Button was double-clicked.", alert.getText());
		alert.accept();

	}	
	
	@Test
	public void TC_04_RightClickToElement() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		WebElement rightClickMe = driver.findElement(By.xpath("//span[text()='right click me']"));
		Actions action = new Actions(driver);
		action.contextClick(rightClickMe).perform();
		
		WebElement quitBeforeHover = driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit')]/span[text()='Quit']"));
		action.moveToElement(quitBeforeHover).perform();
		
		WebElement quitAfterHover = driver.findElement(By.xpath("//li[contains(@class,'context-menu-visible') and contains(@class,'context-menu-hover')]/span[text()='Quit']\n" + 
				""));
		Assert.assertTrue(quitAfterHover.isDisplayed());
		action.click(quitAfterHover).perform();
		
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("clicked: quit", alert.getText());
		alert.accept();
		
	}
	
	@Test
	public void TC_05_DragDrop() {
		driver.get("http://demos.telerik.com/kendo-ui/dragdrop/angular");
		
		WebElement berforeDrag = driver.findElement(By.xpath("//div[@id='droptarget']"));
		Assert.assertTrue(berforeDrag.isDisplayed());
		
		WebElement sourceElement = driver.findElement(By.xpath("//div[@id='draggable']"));
		WebElement targetElement = driver.findElement(By.xpath("//div[@id='droptarget']"));
		Actions action = new Actions(driver);
		//Build: dong goi action, perform: thuc hien action
		action.dragAndDrop(sourceElement, targetElement).build().perform();
		//release: nhac chuot
		action.release();
		
		WebElement afterDrag = driver.findElement(By.xpath("//div[text()='You did great!']"));
//		Assert.assertFalse(berforeDrag.isDisplayed());
		Assert.assertTrue(afterDrag.isDisplayed());
	
	}	

	@Test
	public void TC_06_DragDrop() {
		driver.get("http://jqueryui.com/resources/demos/droppable/default.html");
		
		WebElement berforeDrag = driver.findElement(By.xpath("//p[text()='Drop here']"));
		Assert.assertTrue(berforeDrag.isDisplayed());
		
		WebElement sourceElement = driver.findElement(By.xpath("//div[@id='draggable']"));
		WebElement targetElement = driver.findElement(By.xpath("//div[@id='droppable']"));
		Actions action = new Actions(driver);
		//Build: dong goi action, perform: thuc hien action
		action.dragAndDrop(sourceElement, targetElement).build().perform();
		//release: nhac chuot
		action.release();
		
		WebElement afterDrag = driver.findElement(By.xpath("//p[text()='Dropped!']"));
//		Assert.assertFalse(berforeDrag.isDisplayed());
		Assert.assertTrue(afterDrag.isDisplayed());
	
	}	


	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
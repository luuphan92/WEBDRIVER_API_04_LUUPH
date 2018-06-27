package selenium_api;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_UploadFile {
    WebDriver driver;
    String workingDrectory = System.getProperty("user.dir");
    String filename = "uploadFile.png";
    String filepath = workingDrectory + "\\image\\"  + filename;
    String folderName = "AutoTest" + randomNumber();
    String email = "AutoEmail" + randomNumber() + "@gmail.com";
    String firstname = "Automation Testing";
    
    //System.setProperty("webdriver.chrome.driver",".\\driver\\chromedriver.exe");

	@BeforeClass
	public void beforeClass() {
//		//Firefox <47
//		driver = new FirefoxDriver();
//		
//		//Firefox >= 48
//		System.setProperty("webdriver.Firefox.driver",".\\driver\\geckodriver.exe");
//		driver = new FirefoxDriver();
		
		//Chrome
		System.setProperty("webdriver.chrome.driver",".\\driver\\chromedriver.exe" );
		driver = new ChromeDriver();
		
//		//IE
//		System.setProperty("webdriver.IE.driver",".\\driver\\IEDriverServer.exe");
//		driver = new InternetExplorerDriver();
	}

	
	public void TC_01_Sendkeys() {
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		WebElement element = driver.findElement(By.xpath("//input[@type='file']"));
		element.sendKeys(filepath);
		
		WebElement fileUploaded = driver.findElement(By.xpath("//p[@class='name' and text()='"+filename+"']"));
		
		Assert.assertTrue(fileUploaded.isDisplayed());
	}

	
	public void TC_02_AutoIT() throws Exception {
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		WebElement uploadChrome = driver.findElement(By.cssSelector(".fileinput-button"));
        uploadChrome.click();		
		Runtime.getRuntime().exec(new String[] { ".\\autoIT\\chrome.exe", filepath });
		
		WebElement fileUploaded = driver.findElement(By.xpath("//p[@class='name' and text()='"+filename+"']"));
		
		Assert.assertTrue(fileUploaded.isDisplayed());


	}
	
	
	public void TC_03_RobotClass() throws Exception {
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		//Specify the file location with extension
		StringSelection select = new StringSelection(filepath);

		//Copy to clipboard
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);

		//Click
		driver.findElement(By.className("fileinput-button")).click();

		Robot robot = new Robot();
		Thread.sleep(1000);

		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);

		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		Thread.sleep(1000);

		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
	}

	@Test
	public void TC_04_Upload() {
		driver.get("https://encodable.com/uploaddemo/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		WebElement uploadFile = driver.findElement(By.xpath("//input[@id='uploadname1']"));
		uploadFile.sendKeys(filepath);
		
		WebElement folderTextbox = driver.findElement(By.xpath("//input[@id='newsubdir1']"));
		folderTextbox.sendKeys(folderName);
		
		WebElement emailTextbox = driver.findElement(By.xpath("//input[@id='formfield-email_address']"));
		emailTextbox.sendKeys(email);
		
		WebElement firstnameTextbox = driver.findElement(By.xpath("//input[@id='formfield-first_name']"));
		firstnameTextbox.sendKeys(firstname);
		
		WebElement uploadBtn = driver.findElement(By.xpath("//input[@id='uploadbutton']"));
		uploadBtn.click();
		
		Assert.assertTrue(driver.findElement(By.xpath(".//*[text()='Email Address: "+email+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath(".//*[text()='First Name: "+firstname+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//dt[contains(text(),'File 1 of 1:')]/a[text()='"+filename+"']")).isDisplayed());
		
		WebElement viewUploadBtn = driver.findElement(By.xpath("//a[text()='View Uploaded Files']"));
		viewUploadBtn.click();
		
		WebElement folderNameLabel = driver.findElement(By.xpath("//a[text()='"+folderName+"']"));
		Assert.assertTrue(folderNameLabel.isDisplayed());
		folderNameLabel.click();
		
		WebElement imgUpload = driver.findElement(By.xpath("//img[contains(@src,"+filename+")]"));
		Assert.assertTrue(imgUpload.isDisplayed());
			
	}


	@AfterClass
	public void afterClass() {	
		driver.quit();
	}
	
	public int randomNumber() {
		Random random = new Random();
		int number = random.nextInt(999999);
		return number;
		
	}

}
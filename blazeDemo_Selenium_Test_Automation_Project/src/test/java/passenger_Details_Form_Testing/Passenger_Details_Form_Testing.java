package passenger_Details_Form_Testing;


import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.*;

import utils.ScreenshotUtil;

public class Passenger_Details_Form_Testing {

	WebDriver driver;
	WebDriverWait wait;

	@BeforeMethod
	public void beforeClass() {

		driver = new EdgeDriver();
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.get("https://blazedemo.com/index.php");
	}

	@AfterMethod
	public void afterClass() {
		driver.quit();
	}

	// Fill all the Necessary Details in Customer Page
	
	@Test(priority = 0)
	public void f() {
		
		driver.findElement(By.xpath("//input[@value='Find Flights']")).click();
		
		driver.findElement(By.xpath("//table[@class='table']//tbody//tr[3]//td[1]")).click();
		
		// Filling the details
		
		driver.findElement(By.id("inputName")).sendKeys("John");
		driver.findElement(By.id("address")).sendKeys("India,42/24 street");
		driver.findElement(By.id("city")).sendKeys("India");
		driver.findElement(By.id("state")).sendKeys("Tamil Nadu");
		driver.findElement(By.id("zipCode")).sendKeys("629168");
		WebElement dropdown = driver.findElement(By.xpath("//*[@id='cardType']"));
		Select select = new Select(dropdown);select.selectByIndex(0);
		driver.findElement(By.id("creditCardNumber")).sendKeys("1234567");
		driver.findElement(By.id("nameOnCard")).sendKeys("Ajanth_AA");

		WebElement PurchaseButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='btn btn-primary']")));
		
		PurchaseButton.click();

		String ExpectedResult = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='container hero-unit']//h1"))).getText();
		System.out.println(ExpectedResult);
		System.out.println("Exceuted Successfully");
	}
	
	// Submit the form with missing fields,it should show error message
	
	
	@Test(priority = 1)
	public void g() {
		
     	driver.findElement(By.xpath("//input[@value='Find Flights']")).click();
		
		driver.findElement(By.xpath("//table[@class='table']//tbody//tr[3]//td[1]")).click();
		
		driver.findElement(By.id("inputName")).sendKeys("John");
		driver.findElement(By.id("address")).sendKeys("India,42/24 street");
		driver.findElement(By.id("city")).sendKeys("India");
		driver.findElement(By.id("state")).sendKeys("Tamil Nadu");
//		driver.findElement(By.id("zipCode")).sendKeys("629168");
		WebElement dropdown = driver.findElement(By.xpath("//*[@id='cardType']"));
		Select select = new Select(dropdown);select.selectByIndex(0);
		driver.findElement(By.id("creditCardNumber")).sendKeys("1234567");
//		driver.findElement(By.id("nameOnCard")).sendKeys("Ajanth_AA");
		
		WebElement PurchaseButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='btn btn-primary']")));
		
		ScreenshotUtil.takeScreenshot(driver, "MissingValidationError_[Required_field_is_missing_in_HTML]");

		
		PurchaseButton.click();

		String ExpectedResult = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='container hero-unit']//h1"))).getText();
		System.out.println(ExpectedResult);
		System.out.println("Exceuted Successfully");
	}
	
	
	// Enter invalid credit card types
	
	@Test(priority = 2)
	public void h() {
		
		driver.navigate().to("https://blazedemo.com/purchase.php");
		
	
		driver.findElement(By.id("inputName")).sendKeys("John");
		driver.findElement(By.id("address")).sendKeys("India,42/24 street");
		driver.findElement(By.id("city")).sendKeys("India");
		driver.findElement(By.id("state")).sendKeys("Tamil Nadu");
		
		WebElement dropdown = driver.findElement(By.xpath("//*[@id='cardType']"));
	
		Select select = new Select(dropdown);
		List<WebElement> option = select.getOptions();
		
		System.out.println("Checking if all the dropdown is selected properly");
		
		for (WebElement webElement : option) {
			
			String aa = webElement.getText();
			select.selectByVisibleText(aa);
			System.out.println(aa + " is selected successfully");
			
			
		}
		
		driver.findElement(By.id("zipCode")).sendKeys("629168");
		driver.findElement(By.id("creditCardNumber")).sendKeys("857302857");
		driver.findElement(By.id("nameOnCard")).sendKeys("Ajanth_AA");

		WebElement PurchaseButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='btn btn-primary']")));
		PurchaseButton.click();
		
	}
	
	
	//Verify the "Remember Me" checkbox functionality
	
	@Test( priority = 2)
	public void i() {
		
		driver.findElement(By.xpath("//input[@value='Find Flights']")).click();
		
		driver.findElement(By.xpath("//table[@class='table']//tbody//tr[3]//td[1]")).click();
		
		// Filling the details
		
		driver.findElement(By.id("inputName")).sendKeys("John");
		driver.findElement(By.id("address")).sendKeys("India,42/24 street");
		driver.findElement(By.id("city")).sendKeys("India");
		driver.findElement(By.id("state")).sendKeys("Tamil Nadu");
		driver.findElement(By.id("zipCode")).sendKeys("629168");
		WebElement dropdown = driver.findElement(By.xpath("//*[@id='cardType']"));
		Select select = new Select(dropdown);select.selectByIndex(2);
		driver.findElement(By.id("creditCardNumber")).sendKeys("1234567");
		driver.findElement(By.id("nameOnCard")).sendKeys("Ajanth_AA");

		//Selecting and Deselecting checkbox
		
		WebElement checkbox = driver.findElement(By.id("rememberMe"));

		// Select
		if (!checkbox.isSelected()) {
		    checkbox.click();
		    System.out.println("Checkbox selected.");
		}

		// Deselect
		if (checkbox.isSelected()) {
		    checkbox.click();
		    System.out.println("Checkbox deselected.");
		}
		
		WebElement PurchaseButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='btn btn-primary']")));
		PurchaseButton.click();
		
		System.out.println("Remember me checkbox is Selected and Deselected Successfully");
		
	}
	
	
	//Check ZIP code field with non-numeric characters 
	//[System shouldn't allow non-numeric values]
	
	@Test( priority = 3)
	public void j() throws InterruptedException {
		
		driver.navigate().to("https://blazedemo.com/purchase.php");
		
		driver.findElement(By.id("inputName")).sendKeys("John");
		driver.findElement(By.id("address")).sendKeys("India,42/24 street");
		driver.findElement(By.id("city")).sendKeys("India");
		driver.findElement(By.id("state")).sendKeys("Tamil Nadu");
		WebElement dropdown = driver.findElement(By.xpath("//*[@id='cardType']"));
		Select select = new Select(dropdown);select.selectByIndex(0);
		driver.findElement(By.id("creditCardNumber")).sendKeys("1234567");
		driver.findElement(By.id("nameOnCard")).sendKeys("Ajanth_AA");
		
		driver.findElement(By.id("zipCode")).sendKeys("ab12j04");
		
		WebElement PurchaseButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='btn btn-primary']")));
		PurchaseButton.click();
		
		ScreenshotUtil.takeScreenshot(driver,"System doesn't display error when numeric is entered in zip column");
		
		 System.out.println("*************************************************");
	}	
}

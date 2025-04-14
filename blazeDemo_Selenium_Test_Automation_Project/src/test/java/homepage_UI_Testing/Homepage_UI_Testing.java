package homepage_UI_Testing;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class Homepage_UI_Testing {
	
	WebDriver driver;
	String url = "https://blazedemo.com/";
	
	
	 @BeforeMethod
	  public void beforeMethod() {
		 driver = new EdgeDriver();
		 driver.manage().window().maximize();
		 driver.get(url);
	  }

	  @AfterMethod
	  public void afterMethod() {
 	  driver.quit();
	  }

	
  @Test(priority = 0)
  public void a() {
	  
		//Homepage should load with "Welcome to the Simple Travel Agency" message
	  
	  String Title = driver.findElement(By.xpath("//div[@class='container']//h1")).getText();
	  Assert.assertTrue(Title.contains("We"), "The title isn't found");  
	  System.out.println("Test passed");

  }
  
  
  @Test(priority = 1)
  public void b() {
	  //Validate the buttons
	  
	  

	  driver.findElement(By.linkText("home")).click();
	  String home = driver.findElement(By.xpath("//label[@for='email']")).getText();
	  System.out.println(home);
	  
	  driver.navigate().to(url);
	  String Title = driver.findElement(By.xpath("//div[@class='container']//h1")).getText();
	  Assert.assertTrue(Title.contains("Welcome"), "The title isn't found");  
	  System.out.println("Test passed");
	  

	  
	  driver.navigate().to(url);
	  driver.findElement(By.xpath("//input[@value='Find Flights']")).click(); 
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	  String FindflightButton= driver.findElement(By.xpath("//div[@class='container']//h3")).getText();
	  Assert.assertTrue(FindflightButton.contains("Flights"),"The Flights from isn't displayed");
	  
  
  }
  
  @Test(priority = 2)
  public void c() {
	  
	  //Click on both dropdowns and check available cities
	  
	  WebElement DropDown1 = driver.findElement(By.xpath("//select[@name='fromPort']"));
	  Select select = new Select(DropDown1);
	  select.selectByIndex(3);
	  System.out.println("Dropdown 1 works perfectly");
	  
	  WebElement DropDown2 = driver.findElement(By.xpath("//select[@name='toPort']"));
	  Select masa = new Select(DropDown2);
	  masa.selectByValue("Rome");
	  System.out.println("Dropdown 2 works perfectly");
	  
	  System.out.println("*************************************************");
  }
}

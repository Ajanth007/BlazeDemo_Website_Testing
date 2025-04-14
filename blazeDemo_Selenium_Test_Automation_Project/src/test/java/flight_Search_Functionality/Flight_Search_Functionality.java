package flight_Search_Functionality;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class Flight_Search_Functionality {
	
	WebDriver driver;
    WebDriverWait wait;
	String url = "https://blazedemo.com/index.php";

  @BeforeMethod
  public void beforeMethod() {
	  driver = new EdgeDriver();
	  driver.manage().window().maximize();
	  driver.get(url);
	  wait = new WebDriverWait(driver, Duration.ofSeconds(10));
  }

  @AfterMethod
  public void afterMethod() {
	  driver.quit();
  }

  @Test( priority = 0)
  public void f() {
	  
	  //Select "Boston" → "London", Click "Find Flights"
	  
	  WebElement DropDown1 = wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//select[@name='fromPort']"))));  
	  Select select = new Select(DropDown1);
	  select.selectByVisibleText("Boston");
	  System.out.println("Boston is selected");
	  
	  
	  WebElement DropDown2 = driver.findElement(By.xpath("//select[@name='toPort']"));
	  Select selects = new Select(DropDown2);
	  selects.selectByValue("London");
	  System.out.println("London is selected");
	  
	  driver.findElement(By.xpath("//input[@value='Find Flights']")).click(); 
	  
	  System.out.println("Printing the Avilable flights");
	 WebElement PrintingTheHeading =  driver.findElement(By.xpath("//table[@class='table']//th[2]"));
	 System.out.println(PrintingTheHeading.getText());
	 
	 List<WebElement> Flights = driver.findElements(By.xpath("//table[@class='table']//td[2]"));
	 
	 for ( WebElement webElement : Flights) {
		System.out.println(webElement.getText());
	}
	 
	 System.out.println("The Flights from Boston to London is found Successfully");
	 
  }
  
 
  //selecting different countries flights
  
  @Test( priority = 1)
  public void testDropdownSelections() throws InterruptedException {
	  System.out.println("🔍 Starting Flight Search Tests");


      WebElement fromDropdown = driver.findElement(By.xpath("//select[@name='fromPort']"));
      WebElement toDropdown = driver.findElement(By.xpath("//select[@name='toPort']"));

  
      Select selectFrom = new Select(fromDropdown);
      Select selectTo = new Select(toDropdown);


      List<WebElement> fromOptions = selectFrom.getOptions();
      List<WebElement> toOptions = selectTo.getOptions();


      for (WebElement fromOption : fromOptions) {
          String fromCity = fromOption.getText();
          selectFrom.selectByVisibleText(fromCity); 
          System.out.println("Selected From: " + fromCity);

          for (WebElement toOption : toOptions) {
              String toCity = toOption.getText();
              selectTo.selectByVisibleText(toCity);  
              System.out.println("Selected To: " + toCity);

      
              driver.findElement(By.xpath("//input[@value='Find Flights']")).click();


              WebElement resultHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(
                      By.xpath("//div[@class='container']//h3")));
              String resultText = resultHeader.getText();
              System.out.println("Search Result: " + resultText);


              List<WebElement> flightRows = driver.findElements(By.xpath("//table/tbody/tr"));
              Assert.assertTrue(flightRows.size() > 0, "❌ No flights found for " + fromCity + " to " + toCity);


              driver.navigate().back();
          }
      }

      System.out.println("Flight Search Tests Completed!");
      System.out.println("*************************************************");
  } 
}

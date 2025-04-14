package flight_Selection_Process;

import org.testng.annotations.Test;


import org.testng.annotations.BeforeMethod;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class Flight_Selection_Process {
 
	WebDriver driver;
	WebDriverWait wait;
	String url = "https://blazedemo.com/index.php";
	
  @BeforeMethod
  public void beforeMethod() {
	  driver =  new EdgeDriver();
	  driver.manage().window().maximize();;
	  wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	  driver.get(url);
  }

  @AfterMethod
  public void afterMethod() {
	  driver.quit();
  }

  
  @Test (priority = 0)
  public void f() {
	  
	  driver.findElement(By.xpath("//input[@value='Find Flights']")).click();
	  
	 List<WebElement> NoOfFlights = driver.findElements(By.xpath( "//table[@class='table']//tbody//tr"));
	 Assert.assertTrue(NoOfFlights .size()>  0, "There is no flight for the Area");
	
		
		 System.out.println("Printing the Flights");
		 
		 System.out.println(NoOfFlights.size());

		 
		 for (WebElement webElement : NoOfFlights) {
			 
			 String FlightNO = webElement.findElement(By.xpath("./td[2]")).getText();
			 String AirlineName = webElement.findElement(By.xpath("./td[3]")).getText();
       		 String Price = webElement.findElement(By.xpath("//./td[6]")).getText();
			 
			 System.out.println(FlightNO+""+"-Flight Number"+" "+AirlineName+" "+"- Airline Name"+" "+Price+"- Price");
			 
			Assert.assertFalse(FlightNO.isEmpty(),"There is no Flight Number");
			Assert.assertFalse(AirlineName.isEmpty(),"There is no Airline Number");
			Assert.assertFalse(Price.isEmpty(),"There is no Price ");
	
	}
		 System.out.println("Exceution Completed");
  }
  
  
  // Click "Choose This Flight" button for any flight,Passenger details page should load
  
  @Test (priority = 1)
  public void g() {
	  
	  driver.findElement(By.xpath("//input[@value='Find Flights']")).click();
	  
	  driver.findElement(By.xpath("//input[@class='btn btn-small']")).click();
	  
	  WebElement title = driver.findElement(By.xpath(" //div[@class='container']//h2"));
	  System.out.println(title.getText());
	  
	  System.out.println("Exceution Completed");
  }
  
  // Sorting the price in order 
  
  @Test (priority = 2)
  public void h() {
	  
  driver.findElement(By.xpath("//input[@value='Find Flights']")).click();
	  
	  List<WebElement> rows = driver.findElements(By.xpath("//table[@class='table']//tbody//tr"));
	  Assert.assertTrue(rows.size()>0,"There is no Flights for the locations");
	  
	   List<Double> actualprice = new ArrayList<Double>();
	   
	  for (WebElement webElement : rows) {
		  
		  String price = webElement.findElement(By.xpath("./td[6]")).getText();
		  price = price.replace("$", "").trim();
		  actualprice.add(Double.parseDouble(price));	  
	}
	  
	  System.out.println("Price Before Sorting "+actualprice);
	  
	  List<Double> SortedPrices = new ArrayList<>(actualprice);
	  Collections.sort(SortedPrices);
	  
	  System.out.println("Prices After Sorting "+SortedPrices);
	  
	  Assert.assertEqualsNoOrder(actualprice, SortedPrices, "Flights are NOT sorted by price!");
	  
	  System.out.println("Exceution Completed");
	  
  }
  
  // Checking the details are in the tabel
  
  @Test (priority = 3)
  public void i() {
	  String AirlineNo = "43";
	  String AirlineName = "Virgin America";
	  double PriceDetail = 	472.56;
	  
	  driver.findElement(By.xpath("//input[@value='Find Flights']")).click();
	
	List<WebElement> rows = driver.findElements(By.xpath("//table[@class='table']//tbody//tr"));


	Boolean ass = false;

	for (WebElement row : rows) {
	    
	    String airlineNo = row.findElement(By.xpath("./td[2]")).getText();
	    String airlineName = row.findElement(By.xpath("./td[3]")).getText();
	    String priceDetail = row.findElement(By.xpath("./td[6]")).getText();

	    double price = Double.parseDouble(priceDetail.replace("$", "").trim());
	    
	    if(airlineNo.equals(AirlineNo) && airlineName.equals(AirlineName) && price==PriceDetail) {
	    	System.out.println("The flight Name "+airlineName+" with no "+airlineNo+" with price "+price+" is checked");
	    	ass=true;
	    	break;
	    }
	}
	
	if (!ass) {
		System.out.println("Checkbox is selected");
	}
	
	System.out.println("Checkbox is deselected");
	
	 System.out.println("*************************************************");
	}
  }